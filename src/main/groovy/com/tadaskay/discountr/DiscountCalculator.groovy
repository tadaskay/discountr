package com.tadaskay.discountr

import com.tadaskay.discountr.pricing.PriceTable
import com.tadaskay.discountr.rule.DecisionRule
import com.tadaskay.discountr.rule.EveryThirdLargeLpShipmentIsFree
import com.tadaskay.discountr.rule.MatchLowestSmallPackageShippingPrice
import com.tadaskay.discountr.rule.MonthlyDiscountQuota
import com.tadaskay.discountr.transaction.Transaction

import java.text.DecimalFormat

class DiscountCalculator {

    private discountRules = [
        new MatchLowestSmallPackageShippingPrice(),
        new EveryThirdLargeLpShipmentIsFree(),
    ]

    private decisionRules = [
        { tx, discounts, lastDecision -> discounts.max() } as DecisionRule,
        { tx, discounts, lastDecision -> [PriceTable.lookup(tx.provider, tx.size), lastDecision].min() } as DecisionRule,
        new MonthlyDiscountQuota(10.0),
    ]

    ShippingPrice calculate(Transaction transaction) {
        def originalPrice = PriceTable.lookup(transaction.provider, transaction.size)
        def discounts = discountRules.collect { it.discount(transaction) }
        def lastDecision = decisionRules.inject(0.0) { BigDecimal lastDecision, rule ->
            rule.decide(transaction, discounts, lastDecision)
        }
        return new ShippingPrice(
            price: originalPrice - lastDecision,
            discount: lastDecision,
        )
    }

    static class ShippingPrice {

        BigDecimal price
        BigDecimal discount

        String toString() {
            [
                decimalFormat(price),
                discount ? decimalFormat(discount) : '-'
            ].join(' ')
        }

        private static String decimalFormat(decimal) {
            new DecimalFormat('0.00').format(decimal)
        }
    }
}

package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

class PriceRules {
    def discountRules = [
        new MatchLowestSmallPackagePrice(),
        new ThirdLargeLpShipmentIsFree(),
    ]
    def decisionRules = [
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
}

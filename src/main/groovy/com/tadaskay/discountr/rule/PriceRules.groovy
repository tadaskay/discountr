package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

class PriceRules {
    def discountRules = [
        new MatchLowestSmallPackagePrice(),
        new ThirdLargeLpShipmentIsFree(),
    ]
    def decisionRules = [
        new MonthlyDiscountQuota(10.0)
    ]

    ShippingPrice calculate(Transaction transaction) {
        def originalPrice = PriceTable.priceTable[transaction.provider][transaction.size]
        def discounts = discountRules
            .collect { it.discount(transaction) }
        def discount = discounts.max().with { it > originalPrice ? originalPrice : it }

        def lastDecision = discount
        decisionRules.each {
            lastDecision = it.decide(transaction, discounts, lastDecision)
        }
        return new ShippingPrice(
            price: originalPrice - lastDecision,
            discount: lastDecision,
        )
    }
}

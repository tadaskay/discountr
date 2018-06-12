package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

class PriceRules {
    def rules = [
        new MatchLowestSmallPackagePrice(),
        new ThirdLargeLpShipmentIsFree(),
    ]

    ShippingPrice calculate(Transaction transaction) {
        def originalPrice = PriceTable.priceTable[transaction.provider][transaction.size]
        def discount = rules
            .collect { it.discount(transaction) }
            .max()
            .with { it > originalPrice ? originalPrice : it }
        return new ShippingPrice(
            price: originalPrice - discount,
            discount: discount,
        )
    }
}

package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

class PriceRules {
    def rules = [
        new MatchLowestSmallPackagePrice()
    ]

    ShippingPrice calculate(Transaction transaction) {
        def discount = rules.collect { it.discount(transaction) }.max()
        def originalPrice = PriceTable.priceTable[transaction.provider][transaction.size]
        return new ShippingPrice(
            price: originalPrice - discount,
            discount: discount,
        )
    }
}

package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

class PriceRules {
    def rules = [
    ]

    ShippingPrice calculate(Transaction transaction) {
        return new ShippingPrice(
            price: 1.00,
            discount: 0
        )
    }
}

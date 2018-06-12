package com.tadaskay.discountr.rule

import java.text.DecimalFormat

class ShippingPrice {

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

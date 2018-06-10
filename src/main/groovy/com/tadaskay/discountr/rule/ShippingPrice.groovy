package com.tadaskay.discountr.rule

class ShippingPrice {
    BigDecimal price
    BigDecimal discount

    String toString() {
        [price, discount != 0 ? discount : '-'].join(' ')
    }
}

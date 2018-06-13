package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

import static com.tadaskay.discountr.Size.S

class MatchLowestSmallPackagePrice implements DiscountRule {
    @Override
    BigDecimal discount(Transaction transaction) {
        if (transaction.size != S) {
            return 0
        }
        def price = PriceTable.priceTable[transaction.provider][transaction.size]
        def lowestPrice = PriceTable.priceTable.values()*.get(S).min()
        return [0, price - lowestPrice].max()
    }
}
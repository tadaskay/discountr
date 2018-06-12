package com.tadaskay.discountr.rule

import com.tadaskay.discountr.pricing.PriceTable
import com.tadaskay.discountr.transaction.Transaction

import static com.tadaskay.discountr.transaction.Size.S

class MatchLowestSmallPackageShippingPrice implements DiscountRule {

    @Override
    BigDecimal discount(Transaction transaction) {
        if (transaction.size != S) {
            return 0
        }
        def price = PriceTable.lookup(transaction.provider, transaction.size)
        def lowestPrice = PriceTable.lookup(S).values().min()
        return [0, price - lowestPrice].max()
    }
}

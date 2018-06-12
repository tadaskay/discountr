package com.tadaskay.discountr.rule

import com.tadaskay.discountr.pricing.PriceTable
import com.tadaskay.discountr.transaction.Transaction

import java.time.YearMonth

import static com.tadaskay.discountr.transaction.Provider.LP
import static com.tadaskay.discountr.transaction.Size.L

class ThirdLargeLpShipmentIsFree implements DiscountRule {

    private shipmentCounter = 0
    private Map<YearMonth, Boolean> shippedFree = [:].withDefault { false }

    @Override
    BigDecimal discount(Transaction transaction) {
        if (!(transaction.provider == LP && transaction.size == L)) {
            return 0
        }

        if (++shipmentCounter % 3) {
            return 0
        }

        def month = YearMonth.from(transaction.date)
        if (shippedFree[month]) {
            return 0
        }

        def price = PriceTable.lookup(transaction.provider, transaction.size)
        shippedFree[month] = true
        return price
    }
}

package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

import java.time.YearMonth

import static com.tadaskay.discountr.Provider.LP
import static com.tadaskay.discountr.Size.L

class ThirdLargeLpShipmentIsFree implements DiscountRule {

    def shipmentCounter = 0
    Map<YearMonth, Boolean> shippedFree = [:].withDefault { false }

    @Override
    BigDecimal discount(Transaction transaction) {
        if (!(transaction.provider == LP && transaction.size == L)) {
            return 0
        }

        if (++shipmentCounter % 3) {
            return 0
        }

        def yearMonth = YearMonth.from(transaction.date)
        if (shippedFree[yearMonth]) {
            return 0
        }

        def price = PriceTable.priceTable[transaction.provider][transaction.size]
        shippedFree[yearMonth] = true
        return price
    }
}

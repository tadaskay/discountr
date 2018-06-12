package com.tadaskay.discountr.pricing

import com.tadaskay.discountr.transaction.Provider
import com.tadaskay.discountr.transaction.Size

import static com.tadaskay.discountr.transaction.Provider.LP
import static com.tadaskay.discountr.transaction.Provider.MR
import static com.tadaskay.discountr.transaction.Size.*

class PriceTable {

    private static Map<Provider, Map<Size, BigDecimal>> priceTable = [
        (LP): [
            (S): 1.5,
            (M): 4.9,
            (L): 6.9,
        ],
        (MR): [
            (S): 2,
            (M): 3,
            (L): 4,
        ]
    ] as Map

    static BigDecimal lookup(Provider provider, Size size) {
        return priceTable[provider][size]
    }

    static Map<Provider, BigDecimal> lookup(Size size) {
        return priceTable.collectEntries { provider, sizeToPrice -> [provider, sizeToPrice[size]] } as Map
    }
}

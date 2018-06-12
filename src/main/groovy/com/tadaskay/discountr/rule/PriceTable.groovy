package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Provider
import com.tadaskay.discountr.Size

import static com.tadaskay.discountr.Provider.LP
import static com.tadaskay.discountr.Provider.MR
import static com.tadaskay.discountr.Size.*

class PriceTable {
    static Map<Provider, Map<Size, BigDecimal>> priceTable = [
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
    ]
}

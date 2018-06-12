package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

interface DiscountRule {
    BigDecimal discount(Transaction transaction)
}

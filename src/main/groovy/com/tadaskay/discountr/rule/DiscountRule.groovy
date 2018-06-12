package com.tadaskay.discountr.rule

import com.tadaskay.discountr.transaction.Transaction

interface DiscountRule {
    BigDecimal discount(Transaction transaction)
}

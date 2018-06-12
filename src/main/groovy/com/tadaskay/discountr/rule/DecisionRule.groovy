package com.tadaskay.discountr.rule

import com.tadaskay.discountr.transaction.Transaction

interface DecisionRule {
    BigDecimal decide(Transaction transaction, List<BigDecimal> discounts, BigDecimal lastDecision)
}

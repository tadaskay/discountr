package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

interface DecisionRule {
    BigDecimal decide(Transaction transaction, List<BigDecimal> discounts, BigDecimal lastDecision)
}

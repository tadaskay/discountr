package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction

import java.time.YearMonth

class MonthlyDiscountQuota implements DecisionRule {

    private Map<YearMonth, BigDecimal> remainingQuota

    MonthlyDiscountQuota(BigDecimal quota) {
        remainingQuota = [:].withDefault { quota }
    }

    @Override
    BigDecimal decide(Transaction transaction, List<BigDecimal> discounts, BigDecimal lastDecision) {
        if (!lastDecision) {
            return 0
        }
        def month = YearMonth.from(transaction.date)

        def discount = [remainingQuota[month], lastDecision].min()
        remainingQuota[month] -= discount

        return discount
    }
}

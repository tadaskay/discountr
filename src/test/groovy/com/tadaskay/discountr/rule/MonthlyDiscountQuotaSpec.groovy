package com.tadaskay.discountr.rule

import com.tadaskay.discountr.transaction.Transaction
import spock.lang.Shared
import spock.lang.Specification

import java.time.YearMonth

class MonthlyDiscountQuotaSpec extends Specification {

    @Shared
    def rule = new MonthlyDiscountQuota(10.0)

    def 'monthly discounts cannot exceed quota'() {
        def anyTransaction = new Transaction(date: YearMonth.parse(month).atDay(1)),
            anyDiscounts = []
        expect:
            rule.decide(anyTransaction, anyDiscounts, previousDecision as BigDecimal) == decision
        where:
            month     | previousDecision || decision
            '2017-01' | 1                || 1
            '2017-01' | 6                || 6
            '2017-01' | 7                || 3
            '2017-01' | 1                || 0
            '2017-02' | 12               || 10
    }
}

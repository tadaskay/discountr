package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate
import java.time.YearMonth

class MonthlyDiscountQuotaSpec extends Specification {
    @Shared
    def rule = new MonthlyDiscountQuota(10.0)

    def 'monthly discounts cannot exceed quota'() {
        def anyTransaction = new Transaction(date: YearMonth.parse(yearMonth).atDay(1))
        def anyDiscounts = []
        expect:
            rule.decide(anyTransaction, anyDiscounts, previousDecision as BigDecimal) == decision
        where:
            yearMonth | previousDecision || decision
            '2017-01' | 1                || 1
            '2017-01' | 6                || 6
            '2017-01' | 7                || 3
            '2017-01' | 1                || 0
            '2017-02' | 12               || 10
    }
}
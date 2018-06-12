package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction
import spock.lang.Shared
import spock.lang.Specification

import java.time.YearMonth

import static com.tadaskay.discountr.Provider.LP
import static com.tadaskay.discountr.Provider.MR
import static com.tadaskay.discountr.Size.L

class ThirdLargeLpShipmentIsFreeSpec extends Specification {

    @Shared
    def rule = new ThirdLargeLpShipmentIsFree()

    def 'every third large LP package is free, maximum one per month'() {
        def transaction = new Transaction(
            date: YearMonth.parse(yearMonth).atDay(1),
            provider: provider,
            size: size,
        )
        expect:
            rule.discount(transaction) == discount
        where:
            yearMonth | size | provider || discount
            '2017-01' | L    | LP       || 0
            '2017-01' | L    | LP       || 0
            '2017-01' | L    | MR       || 0
            '2017-01' | L    | LP       || 6.9
            '2017-01' | L    | LP       || 0
            '2017-01' | L    | LP       || 0
            '2017-01' | L    | LP       || 0
            '2017-01' | L    | LP       || 0
            '2017-02' | L    | LP       || 0
            '2017-02' | L    | LP       || 6.9
            '2017-02' | L    | LP       || 0
    }
}

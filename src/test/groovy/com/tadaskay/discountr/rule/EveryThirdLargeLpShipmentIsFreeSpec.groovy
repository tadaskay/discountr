package com.tadaskay.discountr.rule

import com.tadaskay.discountr.transaction.Transaction
import spock.lang.Shared
import spock.lang.Specification

import java.time.YearMonth

import static com.tadaskay.discountr.transaction.Provider.LP
import static com.tadaskay.discountr.transaction.Provider.MR
import static com.tadaskay.discountr.transaction.Size.L

class EveryThirdLargeLpShipmentIsFreeSpec extends Specification {

    @Shared
    def rule = new EveryThirdLargeLpShipmentIsFree()

    def 'every third large LP package is shipped free, once per month'() {
        def transaction = new Transaction(date: YearMonth.parse(month).atDay(1), provider: provider, size: size)

        expect:
            rule.discount(transaction) == discount
        where:
            month     | size | provider || discount
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

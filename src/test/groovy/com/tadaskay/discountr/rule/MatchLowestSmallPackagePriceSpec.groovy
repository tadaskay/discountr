package com.tadaskay.discountr.rule

import com.tadaskay.discountr.Transaction
import spock.lang.Specification
import spock.lang.Unroll

import static com.tadaskay.discountr.Provider.LP
import static com.tadaskay.discountr.Provider.MR
import static com.tadaskay.discountr.Size.*
import static java.time.LocalDate.now

class MatchLowestSmallPackagePriceSpec extends Specification {

    def rule = new MatchLowestSmallPackagePrice()

    @Unroll
    def 'matches smallest shipping cost of S package: #size #provider -> #discount'() {
        def transaction = new Transaction(
            date: now(),
            provider: provider,
            size: size,
        )
        expect:
            rule.discount(transaction) == discount
        where:
            size | provider || discount
            S    | MR       || 0.5
            S    | LP       || 0
            M    | MR       || 0
            M    | LP       || 0
            L    | MR       || 0
            L    | LP       || 0
    }
}

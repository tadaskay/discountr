package com.tadaskay.discountr.rule

import com.tadaskay.discountr.transaction.Transaction
import spock.lang.Specification
import spock.lang.Unroll

import static com.tadaskay.discountr.transaction.Provider.LP
import static com.tadaskay.discountr.transaction.Provider.MR
import static com.tadaskay.discountr.transaction.Size.*
import static java.time.LocalDate.now

class MatchLowestSmallPackageShippingPriceSpec extends Specification {

    def rule = new MatchLowestSmallPackageShippingPrice()

    @Unroll
    def 'matches the lowest shipping cost of small package: #size #provider -> #discount'() {
        def transaction = new Transaction(date: now(), provider: provider, size: size)
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

package com.tadaskay.discountr

import spock.lang.Specification

class DiscountrSpec extends Specification {

    def 'calculates discounts'() {
        def captureStdout = new ByteArrayOutputStream()
        System.out = new PrintStream(captureStdout)

        given:
            def transactions =
                ''' |2015-02-01 S MR
                    |2015-02-02 S MR
                    |2015-02-03 L LP
                    |2015-02-05 S LP
                    |2015-02-06 S MR
                    |2015-02-06 L LP
                    |2015-02-07 L MR
                    |2015-02-08 M MR
                    |2015-02-09 L LP
                    |2015-02-10 L LP
                    |2015-02-10 S MR
                    |2015-02-10 S MR
                    |2015-02-11 L LP
                    |2015-02-12 M MR
                    |2015-02-13 M LP
                    |2015-02-15 S MR
                    |2015-02-17 L LP
                    |2015-02-17 S MR
                    |2015-02-24 L LP
                    |2015-02-29 CUSPS
                    |2015-03-01 S MR
                    |'''
                    .stripMargin()
        when:
            Discountr.process(transactions)
        then:
            captureStdout.toString() ==
                ''' |2015-02-01 S MR 1.50 0.50
                    |2015-02-02 S MR 1.50 0.50
                    |2015-02-03 L LP 6.90 -
                    |2015-02-05 S LP 1.50 -
                    |2015-02-06 S MR 1.50 0.50
                    |2015-02-06 L LP 6.90 -
                    |2015-02-07 L MR 4.00 -
                    |2015-02-08 M MR 3.00 -
                    |2015-02-09 L LP 0.00 6.90
                    |2015-02-10 L LP 6.90 -
                    |2015-02-10 S MR 1.50 0.50
                    |2015-02-10 S MR 1.50 0.50
                    |2015-02-11 L LP 6.90 -
                    |2015-02-12 M MR 3.00 -
                    |2015-02-13 M LP 4.90 -
                    |2015-02-15 S MR 1.50 0.50
                    |2015-02-17 L LP 6.90 -
                    |2015-02-17 S MR 1.90 0.10
                    |2015-02-24 L LP 6.90 -
                    |2015-02-29 CUSPS Ignored
                    |2015-03-01 S MR 1.50 0.50
                    |'''
                    .stripMargin()
    }
}

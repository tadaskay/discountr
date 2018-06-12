package com.tadaskay.discountr.transaction

import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Transaction {
    LocalDate date
    Size size
    Provider provider
}

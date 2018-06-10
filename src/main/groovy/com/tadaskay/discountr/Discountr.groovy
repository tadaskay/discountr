package com.tadaskay.discountr

import com.tadaskay.discountr.rule.PriceRules

import java.time.LocalDate

class Discountr {
    static void main(String[] args) {
        def priceRules = new PriceRules()

        existingInputFile(args).withReader { reader ->
            def line
            while (line = reader.readLine()) {
                def transaction = parseTransaction(line)
                def priceLine = transaction?.with(priceRules.&calculate) ?: 'Ignored'
                println([line, priceLine].join(' '))
            }
        }
    }

    private static File existingInputFile(String[] args) {
        def file = args.find()?.with { new File(it) }
        if (!file || !file.exists()) {
            println 'FATAL! Please provide an input file.'
            System.exit(-1)
        }
        return file
    }

    private static Transaction parseTransaction(String line) {
        def parts = line.split(' ')
        if (parts.size() != 3) {
            return null
        }
        try {
            return new Transaction(
                date: LocalDate.parse(parts[0]),
                size: Size.valueOf(parts[1]),
                provider: Provider.valueOf(parts[2])
            )
        } catch (ignored) {
        }
        return null
    }

    private

    static String calculate(String transactions) {
    }

}

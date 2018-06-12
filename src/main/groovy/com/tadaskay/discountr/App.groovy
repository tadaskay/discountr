package com.tadaskay.discountr

import com.tadaskay.discountr.rule.PriceRules

import java.time.LocalDate

class App {

    static void main(String[] args) {
        process(inputFile(args.find() ?: 'input.txt'))
    }

    protected static void process(content) {
        def priceRules = new PriceRules()
        content.eachLine { line ->
            def transaction = parseTransaction(line)
            def priceLine = transaction?.with(priceRules.&calculate) ?: 'Ignored'
            println([line, priceLine].join(' '))
        }
    }

    private static File inputFile(String filename) {
        return new File(filename).with {
            if (!it.exists()) {
                println "Input file '${filename}' does not exist"
                System.exit(-1)
            }
            return it
        }
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
}

ruleset {
    description 'Discountr groovy code style'

    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/imports.xml') {
        MisorderedStaticImports(enabled: false)
        NoWildcardImports(enabled: false)
    }
    ruleset('rulesets/logging.xml') {
        Println(enabled: false)
        PrintStackTrace(enabled: false)
    }
    ruleset('rulesets/naming.xml')
    ruleset('rulesets/unnecessary.xml') {
        UnnecessaryReturnKeyword(enabled: false)
    }
    ruleset('rulesets/unused.xml')
}

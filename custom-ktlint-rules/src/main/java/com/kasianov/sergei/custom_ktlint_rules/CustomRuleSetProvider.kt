package com.kasianov.sergei.custom_ktlint_rules

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {
    override fun get()
            = RuleSet("custom-ktlint-rules", NoInternalImportRule())
}
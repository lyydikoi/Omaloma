package com.kasianov.sergei.custom_ktlint_rules

import com.pinterest.ktlint.core.RuleSet
import com.pinterest.ktlint.core.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet(
        "custom-ktlint-rules",
        NoInternalImportRule(),
        LongFunNameRule()
    )
}
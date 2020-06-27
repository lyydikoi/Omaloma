package com.kasianov.sergei.custom_ktlint_rules

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.test.lint
import org.assertj.core.api.Assertions
import org.junit.Test

class LongFunNameRuleTest {
    @Test
    fun noWildcardImportsRule() {
        // whenever KTLINT_DEBUG env variable is set to "ast" or -DktlintDebug=ast is used
        // com.pinterest.ktlint.test.(lint|format) will print AST (along with other debug info) to the stderr.
        // this can be extremely helpful while writing and testing rules.
        //System.setProperty("ktlintDebug", "ast")

        Assertions.assertThat(
            LongFunNameRule().lint(
                """
                fun tooLongFunctionNameOver25Chars() {}
                """.trimIndent())
        ).containsExactly(
            LintError(
                1,
                1,
                "long-fun-name",
                "Function name tooLongFunctionNameOver25Chars is longer than allowed value 25!"
            )
        )
    }
}
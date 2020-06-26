package com.kasianov.sergei.custom_ktlint_rules

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoInternalImportRuleTest {
    @Test
    fun noWildcardImportsRule() {
        assertThat(NoInternalImportRule().lint("""
        import a.b.c
        import a.internal.foo
        """.trimIndent()
        )).containsExactly(
            LintError(2, 1, "no-internal-import",
                "Importing from an internal package")
        )
    }
}
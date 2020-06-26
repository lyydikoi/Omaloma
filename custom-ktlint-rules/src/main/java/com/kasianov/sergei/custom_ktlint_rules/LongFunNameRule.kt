package com.kasianov.sergei.custom_ktlint_rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.children
import org.jetbrains.kotlin.KtNodeTypes.FUN
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens.IDENTIFIER

class LongFunNameRule : Rule("long-fun-name") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected:
        Boolean) -> Unit
    ) {
        if (node.elementType == FUN) {
            node.children().forEach {
                if (it.elementType == IDENTIFIER && it.chars.length > 30) {
                    emit(
                        node.startOffset,
                        "Too long function name ${it.chars}!",
                        false
                    )
                }
            }
        }
    }
}

package com.kasianov.sergei.custom_ktlint_rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.children
import org.jetbrains.kotlin.KtNodeTypes.FUN
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens.IDENTIFIER

private const val MAX_FUN_NAME_LENGHT = 25
class LongFunNameRule : Rule("long-fun-name") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected:
        Boolean) -> Unit
    ) {
        if (node.elementType == FUN) {
            node.children().forEach {
                if (it.chars ==  "@Test") return

                if (it.elementType == IDENTIFIER &&
                    it.chars.length > MAX_FUN_NAME_LENGHT
                ) {
                    emit(
                        node.startOffset,
                        "Function name ${it.chars} is longer " +
                                "than allowed value $MAX_FUN_NAME_LENGHT!",
                        false
                    )
                }
            }
        }
    }
}

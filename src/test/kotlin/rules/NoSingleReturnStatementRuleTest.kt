package rules

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it


class NoSingleReturnStatementRuleTest : Spek({

    describe("no-single-return-statement rule") {
        val rule = NoSingleReturnStatementRule()

        it("should prohibit single return statements in method body") {
            assertThat(rule.lint("""fun fn() : String { return "" }"""))
                .isEqualTo(
                    listOf(
                        LintError(
                            1,
                            20,
                            "no-single-return-statement",
                            "Single return statement, use = instead"
                        )
                    )
                )
        }

        it("should allow single return statement if comment in method body exists") {
            assertThat(
                rule.lint(
                    """
                fun f1() : String {
                    // single line comment
                    return ""
                }

                fun f2() : String {
                    /*
                    * multiline comment
                    */
                    return ""
                }

                fun f3() : String {
                    /**
                    * javadoc comment
                    */
                    return ""
                }
            """
                )
            ).isEmpty()
        }
    }
})

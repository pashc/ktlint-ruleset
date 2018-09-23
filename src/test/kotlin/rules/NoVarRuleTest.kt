package rules

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class NoVarRuleTest : Spek({

    describe("no-var rule") {
        val rule = NoVarRule()

        it("should prohibit usage of var") {
            assertThat(rule.lint("""fun fn() { var v = "var" }"""))
                .isEqualTo(listOf(LintError(1, 12, "no-var", "Unexpected var, use val instead")))
        }
    }
})
package io.github.skriptinsight.utils.dsl.textmate.model

interface PatternContainer {
    val patterns: MutableList<TextMateRule>
    val captures: MutableMap<String, TextMateRule>

    fun capture(groupName: String, builder: TextMateRule.() -> Unit): TextMateRule {
        val rule = TextMateRule("capture_$groupName")
        rule.builder()
        this.captures[groupName] = rule
        return rule
    }

    fun capture(groupName: String, rule: TextMateRule): TextMateRule {
        this@PatternContainer.captures[groupName] = rule
        return rule
    }

    operator fun String.invoke() {
        addIncludeForName(this)
    }

    operator fun TextMateRule.invoke(): TextMateRule {
        val patternContainer = this@PatternContainer
        patternContainer.addIncludeForName(this.internalName)
        return this
    }

    fun addIncludeForName(name: String) {
        val patternContainer = this@PatternContainer
        patternContainer.patterns.add(TextMateRule("include_$name").apply {
            include = "#$name"
        })
    }

    operator fun TextMateRule.unaryPlus(): TextMateRule {
        this@PatternContainer.patterns.add(this)
        return this
    }

}
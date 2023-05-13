package br.com.tntnp.utils

fun String.removePrefix() = this.replace("TShirt_", "")

fun String.getTeamNameWithSpacesAndParentheses() =
        this.removePrefix()
                .replace("_", "")
                .addSpaceBeforeUpperCaseLetters()
                .replace("Time", "(Time)")

fun String.getTeamName() = this.removePrefix().replace("_", "")

private fun String.addSpaceBeforeUpperCaseLetters(): String {
    val stringBuilder = StringBuilder()

    var lastCharUpperCase = false
    for (i in this.indices) {
        lastCharUpperCase =
                if (this[i].isUpperCase() && i > 0 && lastCharUpperCase.not()) {
                    stringBuilder.append(" ")
                    true
                } else {
                    false
                }
        stringBuilder.append(this[i])
    }

    return stringBuilder.toString()
}
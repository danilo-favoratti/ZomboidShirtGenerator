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

    for (i in this.indices) {
        if (this[i].isUpperCase() && i > 0) {
            stringBuilder.append(" ")
        }
        stringBuilder.append(this[i])
    }

    return stringBuilder.toString()
}
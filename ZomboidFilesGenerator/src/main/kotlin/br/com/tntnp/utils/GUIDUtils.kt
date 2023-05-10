package br.com.tntnp.utils

object GUIDUtils {

    private const val GUID_DEFAULT = "1ef39252-ab14-4975-8312-25e190ddb4ba"

    fun generateNewGUID(count: Int): String =
        count.toString().run {
            GUID_DEFAULT.substring(0, GUID_DEFAULT.length - this.length) + this
        }
}
package br.com.tntnp.file

import Constants
import br.com.tntnp.data.GuidFile
import br.com.tntnp.utils.FileUtils
import java.io.File

class GuidTableFile(rootFolder: File) {

    var file: File? = null
        private set

    var resourceFile: File
        private set

    init {
        val guidTableFile = rootFolder.resolve(Constants.GUID_TABLE_FILE)
        if (FileUtils.createOrLoadFile(guidTableFile)) {
            file = guidTableFile
        }

        resourceFile = File(this::class.java.classLoader.getResource(Constants.GUID_TABLE_FILE)!!.toURI())
    }

    fun getGuidFile(clothes: Pair<File, String>) = GuidFile(
        path = Constants.GUID_TABLE_PATH_PREFIX + clothes.first.nameWithoutExtension + "." + Constants.EXTENSION_XML,
        guid = clothes.second
    )
}
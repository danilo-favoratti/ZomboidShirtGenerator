package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import java.io.File

class ClothingFile(rootFolder: File) {

    var file: File? = null
        private set

    var resourceFile: File
        private set

    init {
        val clothingFolder = rootFolder.resolve(Constants.CLOTHING_FOLDER)
        if (FileUtils.checkFolderOrCreate(clothingFolder)) {
            val clothingFile = clothingFolder.resolve(Constants.CLOTHING_FILE)
            if (FileUtils.createOrLoadFile(clothingFile)) {
                file = clothingFile
            }
        }

        resourceFile = File(this::class.java.classLoader.getResource(Constants.CLOTHING_FILE)!!.toURI())
    }
}
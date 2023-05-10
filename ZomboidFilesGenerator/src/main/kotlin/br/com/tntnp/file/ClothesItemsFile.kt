package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import java.io.File

class ClothesItemsFile(rootFolder: File, textureFile: File) {

    var file: File? = null
        private set

    var resourceFile: File
        private set

    init {
        val clothingItems = rootFolder.resolve(Constants.CLOTHING_FOLDER).resolve(Constants.CLOTHING_ITEMS_FOLDER)
        if (FileUtils.checkFolderOrCreate(clothingItems)) {
            val clothingFile = clothingItems.resolve(
                "${textureFile.nameWithoutExtension}.${Constants.EXTENSION_XML}"
            )
            if (FileUtils.createOrLoadFile(clothingFile)) {
                file = clothingFile
            }
        }

        resourceFile = File(this::class.java.classLoader.getResource(Constants.CLOTHING_ITEMS_FILE)!!.toURI())
    }
}
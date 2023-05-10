package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import java.io.File

class ClothesItemsFolder(rootFolder: File) {

    var folder: File? = null
        private set

    init {
        val clothesFolder = rootFolder
            .resolve(Constants.TEXTURES_FOLDER)
            .resolve(Constants.CLOTHES_FOLDER)
            .resolve(Constants.CLOTHES_TNT_FOLDER)
        if (FileUtils.checkFolderOrCreate(clothesFolder)) {
            folder = clothesFolder
        }
    }
}
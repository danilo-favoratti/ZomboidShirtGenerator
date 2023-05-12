package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import br.com.tntnp.utils.removePrefix
import java.io.File

class ModelsFile(rootFolder: File) {

    var file: File? = null
        private set

    init {
        if (FileUtils.checkFolderOrCreate(rootFolder)) {
            val scriptsFolder = rootFolder
                .resolve(Constants.SCRIPTS_FOLDER)
                .resolve(Constants.SCRIPTS_CLOTHING_FOLDER)
            if (FileUtils.checkFolderOrCreate(scriptsFolder)) {
                val modelFile = scriptsFolder.resolve(Constants.SCRIPTS_MODEL_FILE)
                if (FileUtils.createOrLoadFile(modelFile)) {
                    file = modelFile
                }
            }
        }
    }

    fun getModelFileHeader() = """
        module Base
        {
        
                
    """.trimIndent()

    fun getModelShirtSection(tshirtName: String) = """
        model TNT_Shirt_Ground_${tshirtName.removePrefix()}
	    {
       		mesh = WorldItems/Clothing/Shirt_Ground_TNT,
            texture = Clothes/TNT/${tshirtName},
            scale = 0.3000,
	    }
        
        
    """.trimIndent()

    fun getModelsFooter() = "}"
}
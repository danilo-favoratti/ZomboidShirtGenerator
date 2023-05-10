package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import java.io.File

class ModelsClothingFile(rootFolder: File) {

    var file: File? = null
        private set

    init {
        if (FileUtils.checkFolderOrCreate(rootFolder)) {
            val scriptsFolder = rootFolder
                .resolve(Constants.SCRIPTS_FOLDER)
                .resolve(Constants.SCRIPTS_CLOTHING_FOLDER)
            if (FileUtils.checkFolderOrCreate(scriptsFolder)) {
                val modelsClothingFile = scriptsFolder.resolve(Constants.SCRIPTS_CLOTHING_FILE)
                if (FileUtils.createOrLoadFile(modelsClothingFile)) {
                    file = modelsClothingFile
                }
            }
        }
    }

    fun getModelsClothingFileHeader() = """
        module TNT 
        {
            imports
	        {
		        Base,
	        }
        
        
    """.trimIndent()

    fun getModelsClothingShirtSection(tshirtName: String) = """
        item $tshirtName
        {
            DisplayCategory      = Clothing,
            Type                 = Clothing,
            DisplayName          = Camiseta do ${tshirtName.getTeamNameParentheses()},
            ClothingItem         = $tshirtName,
            BodyLocation         = Tshirt,
            Icon	             = TNTCamiseta${tshirtName.getTeamName()},
            BloodLocation        = Shirt,
            Insulation           = 0.2,
            WindResistance       = 0.05,
            FabricType           = Cotton,
            WorldStaticModel     = TNT_Shirt_Ground_${tshirtName.removePrefix()},
        }
        
        
    """.trimIndent()

    fun getModelsClothingFooter() = "}"

    private fun String.getTeamNameParentheses() = this.removePrefix().replace("_Time", " (Time)")

    private fun String.getTeamName() = this.removePrefix().replace("_", "")

    private fun String.removePrefix() = this.replace("TShirt_", "")

}
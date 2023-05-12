package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import br.com.tntnp.utils.getTeamNameWithSpacesAndParentheses
import java.io.File

class ReadMeFile(rootFolder: File) {

    var file: File? = null
        private set

    init {
        if (FileUtils.checkFolderOrCreate(rootFolder)) {
            val luaFile = rootFolder.resolve(Constants.README_FILE)
            if (FileUtils.createOrLoadFile(luaFile)) {
                file = luaFile
            }
        }
    }

    fun getReadMeHeader() = """
<h3 align="center">Project Zomboid - Camisetas de Times</h3>

---

<p align="center"> Adiciona ao jogo Project Zomboid camisetas dos times brasileiros de futebol.
    <br> 
</p>

## 📝 Times atuais


    """.trimIndent()

    fun getReadMeShirtSection(tshirtName: String) = """
        ${tshirtName.getTeamNameWithSpacesAndParentheses()}
        
        
    """.trimIndent()
}
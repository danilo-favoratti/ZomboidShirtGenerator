package br.com.tntnp.file

import Constants
import br.com.tntnp.utils.FileUtils
import java.io.File

class LuaFile(rootFolder: File) {

    var file: File? = null
        private set

    init {
        if (FileUtils.checkFolderOrCreate(rootFolder)) {
            val luaFolder = rootFolder
                .resolve(Constants.LUA_FOLDER)
                .resolve(Constants.LUA_SERVER_FOLDER)
                .resolve(Constants.LUA_ITEMS_FOLDER)
            if (FileUtils.checkFolderOrCreate(luaFolder)) {
                val luaFile = luaFolder.resolve(Constants.LUA_FILE)
                if (FileUtils.createOrLoadFile(luaFile)) {
                    file = luaFile
                }
            }
        }
    }

    fun getLuaFileHeader() = """
        require 'Items/SuburbsDistributions'
        require "Items/ProceduralDistributions"
        require "Vehicles/VehicleDistributions"
        require "Items/ItemPicker"
        
        
    """.trimIndent()

    fun getLuaShirtSection(tshirtName: String) = """
        -- TShirt Spawns $tshirtName --
        table.insert(ProceduralDistributions.list["ClothingStoresShirts"].items, "TNT.$tshirtName");
        table.insert(ProceduralDistributions.list["ClothingStoresShirts"].items, 5);

        table.insert(ProceduralDistributions.list["GymLockers"].items, "TNT.$tshirtName");
        table.insert(ProceduralDistributions.list["GymLockers"].items, 5);
        
        
    """.trimIndent()

}
package br.com.tntnp

import Constants
import br.com.tntnp.data.*
import br.com.tntnp.file.*
import br.com.tntnp.utils.GUIDUtils
import br.com.tntnp.utils.XMLUtils
import java.io.File

class GenerateFiles(private val rootFolder: File) {

    private lateinit var clothesWithUUID: ArrayList<Pair<File, String>>

    fun generateFiles(): Boolean {
        if (readClothes().not()) {
            println("Impossible to read the clothes folder!")
            return false
        }
        if (createGuidTableFile().not()) {
            println("Impossible to create ${Constants.GUID_TABLE_FILE}!")
            return false
        }
        if (createClothingXml().not()) {
            println("Impossible to create ${Constants.CLOTHING_FOLDER}/${Constants.CLOTHING_FILE}!")
            return false
        }
        if (createClothingItemsXml().not()) {
            println("Impossible to create ${Constants.CLOTHING_ITEMS_FOLDER}/*.xml!")
            return false
        }
        if (createLuaFile().not()) {
            println("Impossible to create ${Constants.LUA_FILE}!")
            return false
        }
        if (createModelsFile().not()) {
            println("Impossible to create ${Constants.SCRIPTS_MODEL_FILE}!")
            return false
        }
        if (createModelsClothingFile().not()) {
            println("Impossible to create ${Constants.SCRIPTS_CLOTHING_FILE}!")
            return false
        }
        return true
    }

    private fun readClothes(): Boolean {
        ClothesItemsFolder(rootFolder).folder?.let { clothesFolder ->
            clothesFolder.listFiles()?.let {
                clothesWithUUID = generateUUIDs(it)
            } ?: run {
                println("Add your clothes textures to the folder: ${rootFolder.absolutePath}")
                return false
            }
        }
        return true
    }

    private fun generateUUIDs(textures: Array<File>): ArrayList<Pair<File, String>> {
        val result = arrayListOf<Pair<File, String>>()
        for (i in textures.indices) {
            val file = textures[i]
            if (file.isDirectory.not()) {
                result.add(Pair(file, GUIDUtils.generateNewGUID(i)))
            }
        }
        return result
    }

    private fun createGuidTableFile(): Boolean {
        GuidTableFile(rootFolder).run {
            file?.let { guidTableFile ->
                val xmlMapper = XMLUtils.getXmlMapper()
                val guidTable: GuidTable = xmlMapper.readValue(resourceFile, GuidTable::class.java)

                val guidFiles = arrayListOf<GuidFile>()
                for (cloth in clothesWithUUID) {
                    guidFiles.add(getGuidFile(cloth))
                }
                guidTable.files = guidFiles

                val xmlString =
                        XMLUtils.getHeader() + xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guidTable)

                guidTableFile.writeText(xmlString)
            } ?: return false
        }
        return true
    }

    private fun createClothingXml(): Boolean {
        ClothingFile(rootFolder).run {
            file?.let { clothingFile ->
                val xmlMapper = XMLUtils.getXmlMapper()
                val clothing: Clothing = xmlMapper.readValue(resourceFile, Clothing::class.java)

                val items = createItems()
                clothing.maleOutfits.items = items
                clothing.femaleOutfits.items = items

                val xmlString =
                        XMLUtils.getHeader() + xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(clothing)

                clothingFile.writeText(xmlString)
            } ?: return false
        }
        return true
    }

    private fun createItems(): ArrayList<Item> {
        val items = arrayListOf<Item>()
        for (texture in clothesWithUUID) {
            items.add(Item(texture.second))
        }
        return items
    }

    private fun createClothingItemsXml(): Boolean {
        for (cloth in clothesWithUUID) {
            ClothesItemsFile(rootFolder, cloth.first).run {
                file?.let { clothingItemsFile ->
                    val xmlMapper = XMLUtils.getXmlMapper()
                    val clothingItem: ClothingItem = xmlMapper.readValue(resourceFile, ClothingItem::class.java)

                    clothingItem.guid = cloth.second
                    clothingItem.baseTextures += cloth.first.nameWithoutExtension

                    val xmlString =
                            XMLUtils.getHeader() + xmlMapper.writerWithDefaultPrettyPrinter()
                                    .writeValueAsString(clothingItem)

                    clothingItemsFile.writeText(xmlString)
                }
            } ?: return false
        }
        return true
    }

    private fun createLuaFile(): Boolean {
        LuaFile(rootFolder).run {
            file?.let { luaFile ->
                var luaFileContent = getLuaFileHeader()
                for (cloth in clothesWithUUID) {
                    luaFileContent += getLuaShirtSection(cloth.first.nameWithoutExtension)
                }
                luaFile.writeText(luaFileContent)
            } ?: return false
        }
        return true
    }

    private fun createModelsFile(): Boolean {
        ModelsFile(rootFolder).run {
            file?.let { luaFile ->
                var modelsFileContent = getModelFileHeader()
                for (cloth in clothesWithUUID) {
                    modelsFileContent += getModelShirtSection(cloth.first.nameWithoutExtension)
                }
                modelsFileContent += getModelsFooter()
                luaFile.writeText(modelsFileContent)
            } ?: return false
        }
        return true
    }

    private fun createModelsClothingFile(): Boolean {
        ModelsClothingFile(rootFolder).run {
            file?.let { luaFile ->
                var modelsClothingFileContent = getModelsClothingFileHeader()
                for (cloth in clothesWithUUID) {
                    modelsClothingFileContent += getModelsClothingShirtSection(cloth.first.nameWithoutExtension)
                }
                modelsClothingFileContent += getModelsClothingFooter()
                luaFile.writeText(modelsClothingFileContent)
            } ?: return false
        }
        return true
    }

}
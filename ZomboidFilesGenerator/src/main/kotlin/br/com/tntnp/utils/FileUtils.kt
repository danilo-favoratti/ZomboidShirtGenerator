package br.com.tntnp.utils

import Constants
import java.io.File

object FileUtils {

    fun getMediaDirectory(rootFolder: File) = rootFolder.resolve(Constants.FIRST_LEVEL_FOLDER).resolve(Constants.MEDIA_FOLDER)

    fun getRootDirectory(args: Array<String>) = File(getStringRootDirectory(args))

    private fun getStringRootDirectory(args: Array<String>) = if (args.isEmpty()) {
        Constants.MAIN_FOLDER_DEFAULT
    } else {
        args[0]
    }

    fun checkFolderOrCreate(folder: File): Boolean {
        if (folder.exists() && !folder.isDirectory) {
            println("This path is not a directory: ${folder.absolutePath}")
            return false
        }
        if (!folder.exists()) {
            try {
                folder.mkdirs()
            } catch (e: Exception) {
                println("It was impossible to create the non-existent folder: ${folder.absolutePath}")
                return false
            }
        }
        return true
    }

    fun createOrLoadFile(file: File): Boolean {
        if (file.isDirectory) {
            println("This path is not a file: ${file.absolutePath}")
            return false
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: Exception) {
                println("It was impossible to create the non-existent file: ${file.absolutePath}")
                return false
            }
        }
        return true
    }

}
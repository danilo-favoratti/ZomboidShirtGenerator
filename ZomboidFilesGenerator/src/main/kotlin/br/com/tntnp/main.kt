package br.com.tntnp

import br.com.tntnp.utils.FileUtils

fun main(args: Array<String>) {
    val success = GenerateFiles(FileUtils.getRootDirectory(args)).generateFiles()

    if (success) {
        println("Project creation SUCCEEDED!")
    } else {
        println("Project creation FAILED!")
    }
}
package br.com.tntnp.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "fileGuidTable")
data class GuidTable(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "files")
    var files: List<GuidFile>? = null
)

data class GuidFile(
    val path: String,
    val guid: String
)
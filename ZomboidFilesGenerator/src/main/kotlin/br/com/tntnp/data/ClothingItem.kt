package br.com.tntnp.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "clothingItem")
data class ClothingItem(
        @field:JacksonXmlProperty(localName = "m_MaleModel")
        var maleModel: String? = null,

        @field:JacksonXmlProperty(localName = "m_FemaleModel")
        var femaleModel: String? = null,

        @field:JacksonXmlProperty(localName = "m_GUID")
        var guid: String? = null,

        @field:JacksonXmlProperty(localName = "m_Static")
        var isStatic: Boolean? = null,

        @field:JacksonXmlProperty(localName = "m_AttachBone")
        var attachBone: String? = null,

        @field:JacksonXmlProperty(localName = "m_BaseTextures")
        var baseTextures: String? = null
)
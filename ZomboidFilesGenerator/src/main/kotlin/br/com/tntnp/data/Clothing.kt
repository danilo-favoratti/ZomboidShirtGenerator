package br.com.tntnp.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "outfitManager")
data class Clothing(
        @JacksonXmlProperty(localName = "m_MaleOutfits")
        val maleOutfits: MaleOutfits,

        @JacksonXmlProperty(localName = "m_FemaleOutfits")
        val femaleOutfits: FemaleOutfits
)

data class MaleOutfits(
        @JacksonXmlProperty(localName = "m_Name")
        val name: String,

        @JacksonXmlProperty(localName = "m_Guid")
        val guid: String,

        @JacksonXmlProperty(localName = "m_Top")
        val top: Boolean,

        @JacksonXmlProperty(localName = "m_Pants")
        val pants: Boolean,

        @JacksonXmlProperty(localName = "m_AllowPantsHue")
        val allowPantsHue: Boolean,

        @JacksonXmlProperty(localName = "m_AllowTopTint")
        val allowTopTint: Boolean,

        @JacksonXmlProperty(localName = "m_AllowTShirtDecal")
        val allowTShirtDecal: Boolean,

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "m_items")
        var items: List<Item>? = null
)

data class FemaleOutfits(
        @JacksonXmlProperty(localName = "m_Name")
        val name: String,

        @JacksonXmlProperty(localName = "m_Guid")
        val guid: String,

        @JacksonXmlProperty(localName = "m_Top")
        val top: Boolean,

        @JacksonXmlProperty(localName = "m_Pants")
        val pants: Boolean,

        @JacksonXmlProperty(localName = "m_AllowPantsHue")
        val allowPantsHue: Boolean,

        @JacksonXmlProperty(localName = "m_AllowTopTint")
        val allowTopTint: Boolean,

        @JacksonXmlProperty(localName = "m_AllowTShirtDecal")
        val allowTShirtDecal: Boolean,

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "m_items")
        var items: List<Item>? = null
)

data class Item(
        @JacksonXmlProperty(localName = "itemGUID")
        val itemGUID: String
)

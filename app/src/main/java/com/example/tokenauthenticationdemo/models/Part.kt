package com.example.tokenauthenticationdemo.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Part(
    @SerializedName("code")
    val code: String,
    @SerializedName("country_of_origin")
    val countryOfOrigin: String,
    @SerializedName("cylinder_type")
    val cylinderType: String,
    @SerializedName("dealer_price")
    val dealerPrice: Int,
    @SerializedName("dealer_price_per_meter")
    val dealerPricePerMeter: Int,
    @SerializedName("dealer_price_percentage")
    val dealerPricePercentage: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("drivers")
    val drivers: String,
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("guide_link")
    val guideLink: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("length")
    val length: Int,
    @SerializedName("material")
    val material: String,
    @SerializedName("meta_description")
    val metaDescription: String,
    @SerializedName("meta_keywords")
    val metaKeywords: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("oem_compatible")
    val oemCompatible: String,
    @SerializedName("oem_number")
    val oemNumber: String,
    @SerializedName("part_diagram_number")
    val partDiagramNumber: Int,
    @SerializedName("part_number")
    val partNumber: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per_meter")
    val pricePerMeter: Int,
    @SerializedName("quantity_on_hand")
    val quantityOnHand: Int,
    @SerializedName("quantity_on_order")
    val quantityOnOrder: Int,
    @SerializedName("quantity_on_sale_order")
    val quantityOnSaleOrder: Int,
    @SerializedName("quantity_recommended")
    val quantityRecommended: Int,
    @SerializedName("sale_price")
    val salePrice: Int,
    @SerializedName("sale_price_per_meter")
    val salePricePerMeter: Int,
    @SerializedName("seo")
    val seo: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("where_used")
    val whereUsed: String,
    @SerializedName("width")
    val width: Int
): Parcelable
package com.szareckii.searchinthebasefssp.model.data.result

import com.google.gson.annotations.SerializedName

class ResultDetail (
        @field:SerializedName("name") val name: String?,
        @field:SerializedName("exe_production") val exe_production: String?,
        @field:SerializedName("details") val details: String?,
        @field:SerializedName("subject") val subject: String?,
        @field:SerializedName("bailiff") val bailiff: String?
)
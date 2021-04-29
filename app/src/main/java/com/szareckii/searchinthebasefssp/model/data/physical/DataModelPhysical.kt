package com.szareckii.searchinthebasefssp.model.data.physical

import com.google.gson.annotations.SerializedName

class DataModelPhysical(
    @field:SerializedName("status") val status: String?,
    @field:SerializedName("code") val code: String?,
    @field:SerializedName("response") val responsePhysical: ResponsePhysical?
)

package com.szareckii.searchinthebasefssp.model.data.result

import com.google.gson.annotations.SerializedName
import com.szareckii.searchinthebasefssp.model.data.physical.ResponsePhysical

class DataModelResult (
        @field:SerializedName("status") val status: String?,
        @field:SerializedName("response") val responseResult: ResponseResult?
)
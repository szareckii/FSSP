package com.szareckii.searchinthebasefssp.model.data.status

import com.google.gson.annotations.SerializedName
import com.szareckii.searchinthebasefssp.model.data.physical.ResponsePhysical

class DataModelStatus(
    @field:SerializedName("status") val status: String?,
    @field:SerializedName("response") val responseStatus: ResponseStatus?
)

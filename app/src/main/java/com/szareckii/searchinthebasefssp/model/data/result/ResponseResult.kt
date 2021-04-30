package com.szareckii.searchinthebasefssp.model.data.result

import com.google.gson.annotations.SerializedName

class ResponseResult (
        @field:SerializedName("result") val resultList: List<Result>?
)
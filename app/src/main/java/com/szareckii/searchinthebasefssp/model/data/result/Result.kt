package com.szareckii.searchinthebasefssp.model.data.result

import com.google.gson.annotations.SerializedName

class Result (
        @field:SerializedName("result") val resultDetailList: List<ResultDetail>?
)
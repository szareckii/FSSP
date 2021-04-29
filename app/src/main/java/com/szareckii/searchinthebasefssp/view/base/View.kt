package com.szareckii.searchinthebasefssp.view.base

import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical

interface View {
    fun renderData(appStatePhysical: AppStatePhysical)
}
package com.szareckii.searchinthebasefssp.view.base

import com.szareckii.searchinthebasefssp.model.data.result.AppState

interface View {
    fun renderData(appState: AppState)
}
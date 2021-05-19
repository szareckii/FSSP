package com.szareckii.searchinthebasefssp.di

import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.datasource.RetrofitImplementation
import com.szareckii.searchinthebasefssp.model.datasource.RoomDataBaseImplementation
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryImplementation
import com.szareckii.searchinthebasefssp.view.main.MainInteractor
import com.szareckii.searchinthebasefssp.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<DataModelResult>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<Repository<DataModelResult>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()
    ) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    viewModel { MainViewModel(get()) }
}


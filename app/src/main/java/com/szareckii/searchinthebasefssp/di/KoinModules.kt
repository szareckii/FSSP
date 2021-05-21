package com.szareckii.searchinthebasefssp.di

import androidx.room.Room
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.datasource.RetrofitImplementation
import com.szareckii.searchinthebasefssp.model.datasource.RoomDataBaseImplementation
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryImplementation
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryImplementationLocal
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryLocal
import com.szareckii.searchinthebasefssp.room.HistoryDataBase
import com.szareckii.searchinthebasefssp.view.main.MainInteractor
import com.szareckii.searchinthebasefssp.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single<Repository<DataModelResult>> {
        RepositoryImplementation(RetrofitImplementation())
    }

    single<RepositoryLocal<DataModelResult>> {
        RepositoryImplementationLocal( RoomDataBaseImplementation(get()))
    }

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }

    single { get<HistoryDataBase>().historyDao() }

}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}


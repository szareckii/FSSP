package com.szareckii.searchinthebasefssp.view.history

import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryLocal
import com.szareckii.searchinthebasefssp.room.StateHistoryEntity

class HistoryInteractor(private val localRepository: RepositoryLocal) {

    //Прочитали из БД информацию о запросах
    suspend fun getHistoryEntityData(): StateHistoryEntity {
        return StateHistoryEntity.Success(localRepository.getHistoryEntityData())
    }
}

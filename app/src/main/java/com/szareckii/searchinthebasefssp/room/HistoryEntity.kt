package com.szareckii.searchinthebasefssp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = arrayOf("id"), unique = true)))
class HistoryEntity(
    @field:PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @field:ColumnInfo(name = "region")
    val region: String,
    @field:ColumnInfo(name = "lastname")
    val lastname: String,
    @field:ColumnInfo(name = "firstname")
    val firstname: String,
    @field:ColumnInfo(name = "secondname")
    val secondname: String?,
    @field:ColumnInfo(name = "birthdate")
    val birthdate: String?
)
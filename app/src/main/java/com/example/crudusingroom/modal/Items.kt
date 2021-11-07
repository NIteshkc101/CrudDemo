package com.example.crudusingroom.modal

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "items")
@Parcelize
data class Items(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "item") var item: String ="",
    @ColumnInfo(name = "friends") var friends: String="",
    @ColumnInfo(name = "quantity") var qty:String="",
    @ColumnInfo(name = "get") var get:String=""
): Parcelable
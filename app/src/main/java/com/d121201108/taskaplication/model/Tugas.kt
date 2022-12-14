package com.d121201108.taskaplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tugas")
data class Tugas (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val judul_tugas:String,
    val isi_tugas:String,
    val kategori_tugas:String,
    val status_tugas:String
    ): Parcelable
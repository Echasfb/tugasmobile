package com.d121201108.taskaplication.repository

import androidx.lifecycle.LiveData
import com.d121201108.taskaplication.data.TugasDao
import com.d121201108.taskaplication.model.Tugas

class TugasRepository (private val tugasDao:TugasDao) {
    val readAllData: LiveData<List<Tugas>> = tugasDao.readAllDataHome()
    val readAllDataHistory: LiveData<List<Tugas>> = tugasDao.readAllDataRiwayat()
    suspend fun addTugas(tugas: Tugas){
        tugasDao.addTugas(tugas)
    }
    suspend fun updateTugas(tugas: Tugas){
        tugasDao.updateTugas(tugas)
    }
    suspend fun deleteTugas(tugas: Tugas){
        tugasDao.deleteTugas(tugas)
    }
}
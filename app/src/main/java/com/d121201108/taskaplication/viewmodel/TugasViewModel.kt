package com.d121201108.taskaplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.d121201108.taskaplication.data.TugasDatabase
import com.d121201108.taskaplication.model.Tugas
import com.d121201108.taskaplication.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel(application: Application): AndroidViewModel(application) {
    val readAllDataHome: LiveData<List<Tugas>>
    val readAllDataRiwayat: LiveData<List<Tugas>>

    private val repository:TugasRepository
    init {

        val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        readAllDataHome = repository.readAllData
        readAllDataRiwayat = repository.readAllDataHistory
    }
    fun addTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTugas(tugas)
        }
    }
    fun updateTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTugas(tugas)
        }
    }

    fun deleteTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTugas(tugas)
        }
    }
}
package com.nazwakhayla.sekaiprofileviewer.viewModel

import androidx.lifecycle.ViewModel
import com.nazwakhayla.sekaiprofileviewer.entity.SekaiUnit
import com.nazwakhayla.sekaiprofileviewer.repository.UnitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UnitsViewModel : ViewModel() {
    private val unitRepository : UnitRepository = UnitRepository()
    private val _units = MutableStateFlow<List<SekaiUnit>>(emptyList())
    val units = _units.asStateFlow()

    init {
        loadUnits()
    }

    fun loadUnits(){
        unitRepository.getAllUnits { result ->
            _units.value = result
        }
    }
}
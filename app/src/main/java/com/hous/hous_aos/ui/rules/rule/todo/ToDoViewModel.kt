package com.hous.hous_aos.ui.rules.rule.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    private val _isSelectedMyToDo = MutableLiveData(false)
    val isSelectedMyToDo: LiveData<Boolean> get() = _isSelectedMyToDo

    fun setIsSelectedMyToDo(isSelected: Boolean) {
        _isSelectedMyToDo.value = isSelected
    }
}

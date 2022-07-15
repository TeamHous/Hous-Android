package com.hous.hous_aos.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TendencyViewModel : ViewModel() {
    private var _index = MutableLiveData<Int>()
    val index get() = _index
    private var _title = MutableLiveData<String>()
    val title get() = _title
    private val _img = MutableLiveData<String>()
    val img get() = _img
    private val _answer = MutableLiveData<List<String>>()
    val answer get() = _answer
}
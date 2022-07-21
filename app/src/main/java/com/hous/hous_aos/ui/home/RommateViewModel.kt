package com.hous.hous_aos.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RommateViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

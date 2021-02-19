package com.sprout.viewmodel.oasis.issue.submit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.tag.ThemeData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class ThemeViewModel: BaseViewModel(Injection.repository) {
    var list: MutableLiveData<List<ThemeData>> = MutableLiveData()

    fun getTheme(){
        viewModelScope.launch {
            list.postValue(repository.getTheme().data)
        }
    }
}
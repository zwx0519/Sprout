package com.sprout.viewmodel.oasis.home.attention

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.tag.TrendsData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class CityViewModel : BaseViewModel(Injection.repository) {
    var list: MutableLiveData<List<TrendsData>> = MutableLiveData()
    var page: Int = 1
    var size: Int = 2

    //获取主题数据
    fun getTrends(command: Int) {
        viewModelScope.launch {
            var result = repository.trendsList(command, 0, page, size)
            if (result.errno == 0) {
                list.postValue(result.data)
            }
        }
    }
}
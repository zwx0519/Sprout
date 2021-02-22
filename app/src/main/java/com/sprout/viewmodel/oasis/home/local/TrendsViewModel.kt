package com.sprout.viewmodel.oasis.home.local

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.tag.TrendsData
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TrendsViewModel:BaseViewModel(Injection.repository) {

    var list:MutableLiveData<List<TrendsData>> = MutableLiveData()
    var page:Int = 1
    var size:Int = 10

    /**
     *获取动态数据列表
     */
    fun getTrendsList(command:Int,channelid:Int){
        viewModelScope.launch {
            var result = repository.trendsList(command,channelid,page,size)
            if(result.errno == 0){
                list.postValue(result.data)
            }
        }
    }

}
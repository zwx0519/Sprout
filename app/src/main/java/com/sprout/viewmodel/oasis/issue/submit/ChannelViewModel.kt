package com.sprout.viewmodel.oasis.issue.submit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import com.sprout.bean.home.local.Local_ChannelBean
import kotlinx.coroutines.launch

class ChannelViewModel: BaseViewModel(Injection.repository) {
    var channels: MutableLiveData<List<Local_ChannelBean>> = MutableLiveData()

    //TODO 获取频道数据
    fun getChannel(){
        viewModelScope.launch {
            channels.postValue(repository.getLocal_Channel().data)
        }
    }
}
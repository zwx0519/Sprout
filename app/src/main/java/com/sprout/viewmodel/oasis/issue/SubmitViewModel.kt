package com.sprout.viewmodel.oasis.issue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

class SubmitViewModel : BaseViewModel(Injection.repository){
    var state: MutableLiveData<Int> = MutableLiveData()

    //TODO 提交动态数据
    fun submitTrends(json:String){
        var body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json)
        viewModelScope.launch {
            var result = repository.submitTrends(body)
            if(result.errno == 0){
                state.postValue(200)
            }else if(result.errno == 603){
                state.postValue(-2)  //token无效
            }else{
                state.postValue(-1)
            }
        }
    }
}
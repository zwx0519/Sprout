package com.sprout.viewmodel.oasis.issue.tags

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class GoodsViewModel: BaseViewModel(Injection.repository) {
    var gList: MutableLiveData<Issue_GoodBean> = MutableLiveData()
    var bpage = 0
    var gpage = 0
    var size = 10

    //商品数据
    fun getGood(){
        viewModelScope.launch {
            var result = repository.getGood(gpage,size)
            if(result.errno == 0){
                gList.postValue(result.data)
            }
        }
    }
}
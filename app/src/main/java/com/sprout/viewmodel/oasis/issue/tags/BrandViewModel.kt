package com.sprout.viewmodel.oasis.issue.tags

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class BrandViewModel: BaseViewModel(Injection.repository) {
    var bList: MutableLiveData<Issue_BrandBean> = MutableLiveData()

    var bpage = 0
    var gpage = 0
    var size = 10

    //品牌数据
    fun getBrand(){
        viewModelScope.launch {
            var result = repository.getBrand(bpage,size)
            if(result.errno == 0){
                bList.postValue(result.data)
            }
        }
    }
}
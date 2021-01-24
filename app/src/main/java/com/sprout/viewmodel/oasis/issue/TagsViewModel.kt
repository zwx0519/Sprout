package com.sprout.viewmodel.oasis.issue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TagsViewModel: BaseViewModel(Injection.repository){

    var bList: MutableLiveData<Issue_BrandBean> = MutableLiveData()
    var gList: MutableLiveData<Issue_GoodBean> = MutableLiveData()

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
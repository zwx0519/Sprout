package com.sprout.viewmodel.oasis.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbase.bean.login.RegisterBean
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel(Injection.repository) {
    var registerInfo: MutableLiveData<RegisterBean> = MutableLiveData()

    fun getRegister(userName: String, userPsw: String){
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getLogin(userName, userPsw)
            if(result.errno == 0){
                registerInfo.postValue(result.data)
                Log.e("TAG",result.data.toString())
            }else{
                Log.e("TAG", "MeUserInfo: "+result.errmsg )
            }
        }
    }
}
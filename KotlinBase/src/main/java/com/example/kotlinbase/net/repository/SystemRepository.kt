package com.shop.net.repository

import com.shop.api.ServiceApi
import com.shop.net.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //TODO 初始化的方法
    init {
        //在这里调用create 创建ServiceApi进行实例化
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)
    }

    //TODO 希望在协程里面进行 所以使用suspend修饰

    //TODO 获取主页推荐Tab页数据
    suspend fun getLocal_Channel() = withContext(Dispatchers.IO) {
        serviceApi.getLocal_Channel()
    }

    //TODO 获取品牌数据
    suspend fun getBrand(page:Int,size:Int) = withContext(Dispatchers.IO){
        serviceApi.getBrand(page,size)
    }

    //TODO 获取商品数据
    suspend fun getGood(page:Int,size:Int) = withContext(Dispatchers.IO){
        serviceApi.getGood(page,size)
    }

    //TODO 注册
    suspend fun getRegister(userName: String, userPsw: String, imei: String, lng: String, lat: String) = withContext(Dispatchers.IO){
        serviceApi.register(userName, userPsw, imei, lng, lat)
    }

    //TODO 登录
    suspend fun getLogin(userName: String, userPsw: String) = withContext(Dispatchers.IO){
        serviceApi.login(userName, userPsw)
    }
}
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

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)
    }

//    /**
//     * 商品购买详情下面
//     */
//    suspend fun getDetailInfoBottom(id:Int) = withContext(Dispatchers.IO){
//        serviceApi.getDetailInfoBottom(id)
//    }
//
//    /**
//     * 加入购物车
//     */
//    suspend fun AddCar(map: HashMap<String, String>) = withContext(Dispatchers.IO){
//        serviceApi.AddCar(map)
//    }
//
//    /**
//     * 获取购物车列表
//     */
//    suspend fun getCar() = withContext(Dispatchers.IO){
//        serviceApi.getCar()
//    }

}
package com.shop.api

import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.shop.net.BaseResp
import com.sprout.bean.home.local.Local_ChannelBean
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {
    //刷新token
    @POST("auth/refreshToken")
    suspend fun refreshToken(): BaseResp<String>

    //频道分类数据
    @GET("channel/channel")
    suspend fun getLocal_Channel() :BaseResp<List<Local_ChannelBean>>

    @GET("tag/brand")
    suspend fun getBrand(@Query("page") page:Int, @Query("size") size:Int):BaseResp<Issue_BrandBean>

    @GET("tag/good")
    suspend fun getGood(@Query("page") page:Int, @Query("size") size:Int):BaseResp<Issue_GoodBean>

}
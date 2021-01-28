package com.shop.api

import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.example.kotlinbase.bean.login.RegisterBean
import com.shop.net.BaseResp
import com.sprout.bean.home.local.Local_ChannelBean
import retrofit2.http.*

interface ServiceApi {
    //刷新token
    @POST("auth/refreshToken")
    suspend fun refreshToken(): BaseResp<String>

    //频道分类数据
    @GET("channel/channel")
    suspend fun getLocal_Channel() :BaseResp<List<Local_ChannelBean>>

    //获取品牌标签
    @GET("tag/brand")
    suspend fun getBrand(@Query("page") page:Int, @Query("size") size:Int):BaseResp<Issue_BrandBean>

    //获取商品标签
    @GET("tag/goods")
    suspend fun getGood(@Query("page") page:Int, @Query("size") size:Int):BaseResp<Issue_GoodBean>

    //注册
    @POST("api/auth/register")
    suspend fun register(@Field("userName")userName :String,
                         @Field("userPsw")userPsw:String,
                         @Field("imei")imei:String,
                         @Field("lng")lng:String,
                         @Field("lat")lat:String):
            BaseResp<RegisterBean>

    //登录
    @POST("api/auth/login")
    @FormUrlEncoded
    suspend fun login(@Field("username")userName :String, @Field("password")userPsw:String)
            :BaseResp<RegisterBean>
}
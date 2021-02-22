package com.sprout.ui.oasis.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentRecommendBinding
import com.sprout.ui.oasis.home.HomeFragment
import com.sprout.viewmodel.oasis.home.recommend.EnjoyViewModel

//TODO 首页推荐页面
class RecommendFragment(var command:Int)   :
    BaseFragment<EnjoyViewModel, FragmentRecommendBinding>(R.layout.fragment_recommend,EnjoyViewModel::class.java) {

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { RecommendFragment(2) }
    }

    override fun initView() {

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }


}
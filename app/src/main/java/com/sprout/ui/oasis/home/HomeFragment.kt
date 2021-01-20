package com.sprout.ui.oasis.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentHomeBinding
import com.sprout.viewmodel.oasis.home.HomeViewModel

//TODO 首页页面
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>
    (R.layout.fragment_home, HomeViewModel::class.java) {
    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { HomeFragment() }
    }

    override fun initView() {
        mDataBinding.mTabHome
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }


}
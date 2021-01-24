package com.sprout.ui.oasis.me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentHomeBinding
import com.sprout.databinding.FragmentMeBinding
import com.sprout.ui.oasis.home.HomeFragment
import com.sprout.viewmodel.oasis.home.HomeViewModel
import com.sprout.viewmodel.oasis.me.MeViewModel

//TODO 我的页面
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>
    (R.layout.fragment_me, MeViewModel::class.java){

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { MeFragment() }
    }

    override fun initView() {
        mDataBinding.mTabMe.getTabAt(0)?.setIcon(R.drawable.selector_status)
        mDataBinding.mTabMe.getTabAt(1)?.setIcon(R.drawable.selector_like)
        mDataBinding.mTabMe.getTabAt(2)?.setIcon(R.drawable.selector_favorite)
        mDataBinding.mTabMe.getTabAt(3)?.setIcon(R.drawable.selector_at)
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }


}
package com.sprout.ui.oasis.home.local

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.adapter.oasis.home.local.LocalVpAdapter
import com.sprout.bean.home.local.Local_ChannelBean
import com.sprout.databinding.FragmentHomeBinding
import com.sprout.databinding.FragmentLocalBinding
import com.sprout.ui.oasis.home.recommend.RecommendFragment
import com.sprout.viewmodel.oasis.home.HomeViewModel
import com.sprout.viewmodel.oasis.home.local.LocalViewModel
import kotlinx.android.synthetic.main.fragment_local.*

//TODO 首页关注页面
class LocalFragment : BaseFragment<LocalViewModel, FragmentLocalBinding>
    (R.layout.fragment_local, LocalViewModel::class.java) {

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { LocalFragment() }
    }

    override fun initView() {
        //禁止滑动
        mVp_home_local!!.setScanScroll(false)

    }

    override fun initVM() {
        val fragments = ArrayList<LocalInfoFragment>()
        mViewModel.Local_Channel.observe(this, Observer {LocalList->
            for (i  in LocalList.indices){
                var localInfoFragment = LocalInfoFragment()
                fragments.add(localInfoFragment)
            }

            var localAdapter= LocalVpAdapter(childFragmentManager)
            mVp_home_local.adapter = localAdapter
            localAdapter.addList(fragments, LocalList as ArrayList<Local_ChannelBean>)
            mTab_home_local.setupWithViewPager(mVp_home_local)
        })

    }

    override fun initData() {
        mViewModel.getLocal_Channel()
    }

    override fun initVariable() {

    }

}
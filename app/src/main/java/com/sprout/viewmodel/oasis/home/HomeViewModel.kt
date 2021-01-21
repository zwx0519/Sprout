package com.sprout.viewmodel.oasis.home

import androidx.fragment.app.Fragment
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import com.sprout.ui.oasis.home.HomeFragment
import com.sprout.ui.oasis.home.attention.AttentionFragment
import com.sprout.ui.oasis.home.local.LocalFragment
import com.sprout.ui.oasis.home.recommend.RecommendFragment
import com.sprout.ui.oasis.info.InfoFragment
import com.sprout.ui.oasis.me.MeFragment
import com.sprout.ui.oasis.search.SearchFragment

class HomeViewModel : BaseViewModel(Injection.repository){
    var fragments:MutableList<Fragment> = mutableListOf()

    //添加进入集合
    init {
        fragments.add(AttentionFragment.instance)
        fragments.add(RecommendFragment.instance)
        fragments.add(LocalFragment.instance)
    }

}
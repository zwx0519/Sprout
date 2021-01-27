package com.sprout.viewmodel.oasis.me

import androidx.fragment.app.Fragment
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import com.sprout.ui.oasis.me.tab.AtFragment
import com.sprout.ui.oasis.me.tab.FavoriteFragment
import com.sprout.ui.oasis.me.tab.LikeFragment
import com.sprout.ui.oasis.me.tab.StatusFragment

class MeViewModel : BaseViewModel(Injection.repository){
    var fragments:MutableList<Fragment> = mutableListOf()

    //添加进入集合
    init {
        fragments.add(StatusFragment.instance)
        fragments.add(LikeFragment.instance)
        fragments.add(FavoriteFragment.instance)
        fragments.add(AtFragment.instance)
    }
}
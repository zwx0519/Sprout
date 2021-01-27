package com.sprout.ui.oasis.me.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentFavoriteBinding
import com.sprout.databinding.FragmentLikeBinding
import com.sprout.viewmodel.oasis.me.MeViewModel


class FavoriteFragment : BaseFragment<MeViewModel, FragmentFavoriteBinding>
    (R.layout.fragment_favorite, MeViewModel::class.java) {

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { FavoriteFragment() }
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
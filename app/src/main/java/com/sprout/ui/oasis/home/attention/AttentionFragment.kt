package com.sprout.ui.oasis.home.attention

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sprout.R
import com.sprout.ui.oasis.home.local.LocalFragment

//TODO 首页同城页面
class AttentionFragment : Fragment() {

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { AttentionFragment() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attention, container, false)
    }

}
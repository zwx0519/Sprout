package com.sprout.ui.oasis.issue.submit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinbase.bean.issue.tag.ThemeData
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseActivity
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.submit.ThemeAdapter
import com.sprout.databinding.ActivityThemeBinding
import com.sprout.viewmodel.oasis.issue.submit.ThemeViewModel
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : BaseActivity<ThemeViewModel,ActivityThemeBinding>
    (R.layout.activity_theme,ThemeViewModel::class.java) {

    var list:MutableList<ThemeData> = mutableListOf()
    lateinit var adapter: ThemeAdapter
    lateinit var mIntent: Intent

    override fun initView() {
        var arr =  SparseArray<Int>()
        arr.put(R.layout.layout_theme_item, BR.theme)
        adapter = ThemeAdapter(mContext,list,arr,ClickEvt())
        recy_theme.layoutManager = LinearLayoutManager(mContext)
        recy_theme.adapter = adapter
    }

    override fun initVM() {
        mViewModel.list.observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mIntent = intent
        mViewModel.getTheme()
    }

    override fun initVariable() {

    }

    //点击事件
    inner class ClickEvt: IItemClick<ThemeData> {
        override fun itemClick(data: ThemeData) {
            mIntent.putExtra("themeId",data.id)
            mIntent.putExtra("themeName",data.name)
            setResult(102,mIntent)
            finish()
        }
    }

}
package com.sprout.adapter.oasis.issue

import android.content.Context
import android.util.SparseArray
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R

//TODO  商品的adapter
class Issue_GoodAdapter(
    context: Context,
    list:List<Issue_GoodBean.Data>,
    layouts: SparseArray<Int>,
    click: IItemClick<Issue_GoodBean.Data>
): BaseAdapter<Issue_GoodBean.Data>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_good_item
    }

    override fun bindData(binding: ViewDataBinding, data: Issue_GoodBean.Data, layId: Int) {
        binding.setVariable(BR.goodItemClick,itemClick)
        var txt = binding.root.findViewById<TextView>(R.id.tv_good_item_name)
        txt.setText(data.name)
    }

}
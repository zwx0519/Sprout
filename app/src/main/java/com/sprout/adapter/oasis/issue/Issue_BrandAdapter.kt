package com.sprout.adapter.oasis.issue

import android.content.Context
import android.util.SparseArray
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R

class Issue_BrandAdapter(
    context: Context,
    list: List<Issue_BrandBean.Data>,
    layouts: SparseArray<Int>,
    click: IItemClick<Issue_BrandBean.Data>
) : BaseAdapter<Issue_BrandBean.Data>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_brand_item
    }

    override fun bindData(binding: ViewDataBinding, data: Issue_BrandBean.Data, layId: Int) {
        binding.setVariable(BR.brandItemClick,itemClick)
        var txt = binding.root.findViewById<TextView>(R.id.tv_brand_item_name)
        txt.setText(data.name)
    }

}
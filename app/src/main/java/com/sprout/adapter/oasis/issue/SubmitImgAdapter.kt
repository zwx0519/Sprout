package com.sprout.adapter.oasis.issue

import android.content.Context
import android.net.Uri
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.example.kotlinbase.bean.issue.ImgData
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R
import com.sprout.ui.oasis.issue.SubmitMoreActivity

class SubmitImgAdapter(
    context: Context,
    list:MutableList<ImgData>,
    layouts: SparseArray<Int>,
    click: IItemClick<ImgData>
):BaseAdapter<ImgData>(context,list,layouts,click) {
    lateinit var clickEvt: SubmitMoreActivity.SubmitClickEvt

    override fun layoutId(position: Int): Int {
        return R.layout.layout_submit_imgitem
    }

    override fun bindData(binding: ViewDataBinding, data: ImgData, layId: Int) {
        binding.setVariable(BR.submitImgClick,itemClick)
        var img = binding.root.findViewById<ImageView>(R.id.iv_submit_imgitem_img)
        var imgDelete = binding.root.findViewById<ImageView>(R.id.iv_submit_imgitem_delete)

        //如果为空
        if(data.path.isNullOrEmpty()){
            img.setImageResource(R.mipmap.ic_addimg)
            imgDelete.visibility = View.GONE
        }else{
            img.setImageURI(Uri.parse(data.path))
        }

        imgDelete.tag = data
        img.setOnClickListener {
            if(data.path.isNullOrEmpty()) return@setOnClickListener
            imgDelete.visibility = if (imgDelete.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        //点击×的时候
        imgDelete.setOnClickListener {
            if(clickEvt!=null){
                imgDelete.visibility = View.GONE
                clickEvt.clickDelete(imgDelete.tag as ImgData)
            }
        }
    }
}
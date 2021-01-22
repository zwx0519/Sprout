package com.sprout.ui.oasis.issue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.shop.base.BaseActivity
import com.sprout.R
import com.sprout.databinding.ActivityIssueBinding
import com.sprout.utils.GlideEngine
import com.sprout.viewmodel.oasis.issue.IssueViewModel
import kotlinx.android.synthetic.main.activity_issue.*

//TODO 动态页面图片的编辑
class IssueActivity: BaseActivity<IssueViewModel, ActivityIssueBinding>
    (R.layout.activity_issue, IssueViewModel::class.java){

    val CODE_TAG = 99
    var imgList:MutableList<String> = mutableListOf()
    var fragments:MutableList<ImageFragment> = mutableListOf()
    lateinit var fAdapter:FAdapter

    override fun initView() {

    }

    //TODO 初始化界面
    override fun initVM() {
        initListener()//点击事件

        imgList = mutableListOf()

        //设置适配器
        fAdapter = FAdapter(supportFragmentManager)
        mVp_issue.adapter = fAdapter
        //打开相册选取图片
        openPhoto()
    }

    //TODO 点击事件
    private fun initListener() {
        //添加标签
        tv_issue_tag.setOnClickListener {
            var intent = Intent(this,TagsActivity::class.java)
            startActivityForResult(intent,CODE_TAG)
        }

        //点击返回
        tv_issue_return.setOnClickListener{
            finishAndRemoveTask()
        }
    }

    //TODO 打开相册选取图片
    private fun openPhoto(){
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(9)
            .imageSpanCount(3)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun initData() {

    }

    override fun initVariable() {

    }
    //TODO 打开activity后回传
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST -> {
                // onResult Callback
                val selectList = PictureSelector.obtainMultipleResult(data)
                if (selectList.size == 0) return
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for(i in 0 until selectList.size){
                    imgList.add(selectList.get(i).path) //保留图片的绝对路径
                    var fragment = ImageFragment.instance(i,selectList.get(i).path)
                    fragments.add(fragment)
                }
                fAdapter.notifyDataSetChanged()
            }
            //处理TAG设置返回
            CODE_TAG -> {
                if(resultCode == 1){
                    var id = data!!.getIntExtra("id",0)
                    var name = data!!.getStringExtra("name")//获得名字
                    var pos = mVp_issue.currentItem//切换到某一页
                    fragments.get(pos).addTagsToView(1,id,name!!)//传参
                }else if(resultCode == 2){
                    var id = data!!.getIntExtra("id",0)
                    var name = data!!.getStringExtra("name")
                    fragments.get(mVp_issue.currentItem).addTagsToView(2,id,name!!)
                }
            }
            else -> {
            }
        }
    }

    //TODO 内部适配器
    inner class FAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }
    }
}
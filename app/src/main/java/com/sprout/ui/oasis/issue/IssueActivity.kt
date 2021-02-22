package com.sprout.ui.oasis.issue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlinbase.app.Global
import com.example.kotlinbase.bean.issue.ImgData
import com.iknow.android.features.select.VideoSelectActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.shop.base.BaseActivity
import com.sprout.R
import com.sprout.databinding.ActivityIssueBinding
import com.sprout.ui.oasis.issue.SubmitMoreActivity
import com.sprout.utils.GlideEngine
import com.sprout.viewmodel.oasis.issue.IssueViewModel
import kotlinx.android.synthetic.main.activity_issue.*
import org.jetbrains.anko.alert
import org.json.JSONArray
import org.json.JSONObject

//TODO 动态页面图片的编辑
class IssueActivity: BaseActivity<IssueViewModel, ActivityIssueBinding>
    (R.layout.activity_issue, IssueViewModel::class.java){

    val CODE_TAG = 99
    val CODE_VIDEO = 100
    var imgList:MutableList<String> = mutableListOf()
    var fragments:MutableList<ImageFragment> = mutableListOf()
    lateinit var fAdapter:FAdapter

    var PAGE_TYPE:Int = Global.TYPE_IMG//区分视频还是图片

    //当前界面tag相关数据
    var imgArray:MutableList<ImgData> = mutableListOf()

    override fun initView() {

    }

    //TODO 初始化界面
    override fun initVM() {
        initListener()//点击事件

        imgList = mutableListOf()//集合

        //设置适配器
        fAdapter = FAdapter(supportFragmentManager)
        mVp_issue.adapter = fAdapter

        //打开相册选择图片还是视频
        openChangeAlert()
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

        //点击下一步
        tv_issue_next.setOnClickListener {
            var intent = Intent(this, SubmitMoreActivity::class.java)
            intent.putExtra("img_data",decodeImgs())
            startActivity(intent)
        }
    }

    //TODO 打开一个选中图片或者视频的弹框
    private fun openChangeAlert(){
        alert("打开本地图片或者视频"){
            positiveButton("打开本地图库"){
                PAGE_TYPE = Global.TYPE_IMG
                openPhoto()//打开相册
            }
            negativeButton("打开本地视频"){
                PAGE_TYPE = Global.TYPE_VIDEO
                openVideo()//打开视频
            }
        }.show()
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

    //TODO 打开本地视频
    private fun openVideo(){
        //跳转到之前添加的视频剪切库中的activity
        var intent = Intent(this, VideoSelectActivity::class.java)
        startActivityForResult(intent,CODE_VIDEO)
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
                when(PAGE_TYPE){
                    //处理图片
                    Global.TYPE_IMG -> {
                        //获取本地图片的选择地址，上传到服务器
                        //头像的压缩和二次采样
                        //把选中的图片插入到列表
                        for(i in 0 until selectList.size){
                            imgList.add(selectList.get(i).path) //保留图片的绝对路径
                            //图片数据的初始化
                            var imgData = ImgData(selectList.get(i).path, mutableListOf())
                            imgArray.add(imgData)
                            //添加fragment
                            var fragment = ImageFragment.instance(i,selectList.get(i).path,imgData.tags)
                            fragments.add(fragment)//对实例的引用
                        }
                        fAdapter.notifyDataSetChanged()
                    }
                    //处理视频
                    Global.TYPE_VIDEO -> {

                    }
                }

            }
            //处理TAG设置返回  标签数据通过回传值来传入
            CODE_TAG -> {
                if(resultCode == 1){
                    var id = data!!.getIntExtra("id",0)
                    var name = data!!.getStringExtra("name")//获得名字
                    var pos = mVp_issue.currentItem//切换到某一页
                    //调用fragment的方法
                    fragments.get(pos).addTagsToView(1,id,name!!)//传参
                }else if(resultCode == 2){
                    var id = data!!.getIntExtra("id",0)
                    var name = data!!.getStringExtra("name")
                    fragments.get(mVp_issue.currentItem).addTagsToView(2,id,name!!)
                }
            }
            //处理视频返回
            CODE_VIDEO -> {  //处理视频返回
                if(data != null && data.hasExtra("newVideoPath")){
                    var intent = Intent(this,SubmitMoreActivity::class.java)
                    intent.putExtra("video_data",data.getStringExtra("newVideoPath"))
                    startActivity(intent)
                }else{
                    //没有接收到视频压缩处理的数据
                }
            }
            else -> {
            }
        }
    }

    //TODO json结构原生的封装
    private fun decodeImgs():String{
        var imgs = JSONArray()//创建一个json数组
        for(i in 0 until imgArray.size){// imgArray当前界面tag相关数据 类型是ImgData
            var item = imgArray[i]
            var imgJson = JSONObject()  //图片的json结构
            imgJson.put("path",item.path) //获取地址
            var tags = JSONArray()//创建一个json数组
            for(j in 0 until item.tags.size){
                var tag = item.tags[j]
                var tagJson = JSONObject()
                tagJson.put("x",tag.x)
                tagJson.put("y",tag.y)
                tagJson.put("type",tag.type)
                tagJson.put("name",tag.name)
                tagJson.put("lng",tag.lng)
                tagJson.put("lat",tag.lat)
                tags.put(tagJson)
            }
            imgJson.put("tags",tags)
            imgs.put(imgJson)
        }
        return imgs.toString()

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
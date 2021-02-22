package com.sprout.ui.oasis.issue

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.sdk.android.oss.ClientConfiguration
import com.alibaba.sdk.android.oss.ClientException
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.ServiceException
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.example.basemvvm.utils.MyMmkv
import com.example.kotlinbase.app.Global
import com.example.kotlinbase.bean.issue.ImgData
import com.example.kotlinbase.model.myitem.IItemClick
import com.google.gson.Gson
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.shop.base.BaseActivity
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.SubmitImgAdapter
import com.sprout.adapter.oasis.issue.submit.AddressActivity
import com.sprout.databinding.ActivitySubmitMoreBinding
import com.sprout.utils.GlideEngine
import com.sprout.viewmodel.oasis.issue.SubmitViewModel
import com.sprout.ui.oasis.issue.submit.ChannelActivity
import com.sprout.ui.oasis.issue.submit.ThemeActivity
import com.sprout.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_submit_more.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject

//TODO 动态数据的提交
class SubmitMoreActivity : BaseActivity<SubmitViewModel, ActivitySubmitMoreBinding>
    (R.layout.activity_submit_more, SubmitViewModel::class.java) {

    final var CODE_CHANNEL = 101  //选择频道
    final var CODE_THEME = 102 //选择主题
    final var CODE_ADDRESS = 103 //选择地址

    var imgs:MutableList<ImgData> = mutableListOf()
    var max_img = 9//一次处理数据的最大值
    lateinit var imgAdapter: SubmitImgAdapter

    var channelId:Int = 0 //频道id
    lateinit var channelName:String
    var themeId:Int = 0 //主题id
    lateinit var themeName:String
    lateinit var address:String  //地址
    var lat:Double = 0.0  //经度
    var lng:Double = 0.0  //纬度
    var type:Int = 1

    lateinit var ossClient:OSSClient //ossclient对象
    var ossPoint = "http://oss-cn-beijing.aliyuncs.com"
    var bucketName = "2002a"
    var key = "LTAI4G1JvHB2FsXvDYMfY56i" //appkey
    var secret = "gIwhFC9Sk4JEkfFR2mkcOz2Uwr6Vid" //密码

    override fun initView() {
        var layouts = SparseArray<Int>()
        layouts.put(R.layout.layout_submit_imgitem, BR.submitData)
        imgAdapter = SubmitImgAdapter(this,imgs,layouts,ItemClick())
        mRlv_submit_more.layoutManager = GridLayoutManager(this,3)
        mRlv_submit_more.addItemDecoration(ImgItemDecoration())
        mRlv_submit_more.adapter = imgAdapter
        imgAdapter.clickEvt = SubmitClickEvt()
        initOss()
    }

    //TODO 初始化oss
    private fun initOss() {
        val credentialProvider: OSSCredentialProvider = OSSStsTokenCredentialProvider(key, secret, "")
        // 配置类如果不设置，会有默认配置。
        val conf = ClientConfiguration()
        conf.connectionTimeout = 15 * 1000 // 连接超时，默认15秒。
        conf.socketTimeout = 15 * 1000 // socket超时，默认15秒。
        conf.maxConcurrentRequest = 5 // 最大并发请求数，默认5个。
        conf.maxErrorRetry = 2 // 失败后最大重试次数，默认2次。
        ossClient = OSSClient(applicationContext, ossPoint, credentialProvider)
    }

    override fun initVM() {
        mViewModel.state.observe(this, Observer {
            when (it) {
                200 -> {
                    finish()
                }
                -1 -> {

                }
            }
        })
    }

    override fun initData() {
        var from = intent.getIntExtra("from", 0)

        //默认的提交流程
        if(from == 0) {
            //进行JSON 解析
            var json = intent.getStringExtra("data")
            if (json!!.isNotEmpty()) {
                //上一个页面传过来的json字符串数据进行转换
                var jsonArr = JSONArray(json)
                for(i in 0 until jsonArr.length()){
                    var imgData = Gson().fromJson<ImgData>(
                        jsonArr.getString(i),
                        ImgData::class.java
                    )
                    imgs.add(imgData)
                }
                //处理加号
                if (imgs.size < max_img) {
                    var imgData = ImgData(null, mutableListOf())
                    imgs.add(imgData)
                }
            }else{ //草稿再次编辑
                //去本地数据库取值

            }
        }
    }

    override fun initVariable() {
        mDataBinding.submitClick = SubmitClickEvt()
    }

    //TODO 打开相册
    private fun openPhoto(){
        //当前还能插入的图片数量
        var num = max_img-imgs.size+1
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(num)
            .imageSpanCount(3)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
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
                    var imgData = ImgData(selectList.get(i).path, mutableListOf())
                    var index = imgs.size-1//把当前选择的本地相册的图片添加到当前图片数组的加号前面
                    imgs.add(index,imgData)
                }
                //如果当前的图片列表汇总的数据  数量大于了最大图片数量 （9）应该把加号的item删除
                if(imgs.size > max_img){
                    imgs.removeAt(imgs.size-1)
                }
                imgAdapter.notifyDataSetChanged()
            }
            CODE_CHANNEL -> {//选择频道
                if (data != null) {
                    channelId = data!!.getIntExtra("channelId", 0)
                    channelName = data!!.getStringExtra("channelName")!!
                    tv_submit_more_channel.setText(channelName)
                }
            }
            CODE_THEME -> {//选择主题
                if (data != null) {
                    themeId = data!!.getIntExtra("themeId", 0)
                    themeName = data!!.getStringExtra("themeName")!!
                    tv_submit_more_theme.setText(themeName)
                }
            }
            CODE_ADDRESS -> {//选择地址
                if (data != null) {
                    address = data!!.getStringExtra("address")!!
                    lat = data!!.getDoubleExtra("lat", 0.0)
                    lng = data!!.getDoubleExtra("lng", 0.0)
                    tv_submit_more_address.setText(address)
                }
            }
            else -> {
            }
        }
    }

    //TODO 组装提交的内容
    fun getSubmitJson(arr: List<String>):String{
        var title = et_submit_more_title.toString()
        var mood =et_submit_more_mood.toString()
        var json: JSONObject = JSONObject()
        json.put("type", type)
        json.put("title", title)
        json.put("mood", mood)
        json.put("address", address)
        json.put("themeid", themeId)
        json.put("channelid", channelId)
        json.put("lng", lng)
        json.put("lat", lat)
        var res = JSONArray()
        when(type){
            1 -> { //图片
                for (i in 0 until imgs.size) {
                    if(imgs.get(i).path.isNullOrEmpty()) continue
                    var img = JSONObject()
                    img.put("url", arr[i])
                    var tags = JSONArray()
                    img.put("tags", tags)
                    for (j in 0 until imgs[i].tags.size) {
                        var tagItem = imgs[i].tags[j]
                        var tag = JSONObject()
                        tag.put("type", tagItem.type)
                        tag.put("id", tagItem.id)
                        tag.put("name", tagItem.name)
                        tag.put("x", tagItem.x)
                        tag.put("y", tagItem.y)
                        tag.put("lng", tagItem.lng)
                        tag.put("lat", tagItem.lat)
                        tags.put(tag)
                    }
                    res.put(img)
                }
                json.put("res", res)
            }
            2 -> {

            }
        }
        return json.toString()
    }

    //TODO 分割线
    inner class ImgItemDecoration: RecyclerView.ItemDecoration(){
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view).toInt()%3 == 0){
                outRect.set(10, 10, 0, 10)
            }else if(parent.getChildAdapterPosition(view).toInt()%3 == 2){
                outRect.set(0, 10, 10, 10)
            }else{
                outRect.set(10, 10, 10, 10)
            }
        }
    }

    //TODO item条目的点击
    inner class ItemClick: IItemClick<ImgData> {
        override fun itemClick(data: ImgData) {
            //当前点击的是加号
            if(data.path.isNullOrEmpty()){
                openPhoto()
            }
        }
    }

    //TODO 点击事件
    inner class SubmitClickEvt{
        //TODO 删除点击
        fun clickDelete(data:ImgData){
            imgs.remove(data)
            imgAdapter.notifyDataSetChanged()
        }

        //TODO 频道选择
        fun clickChannel(){
            var intent =Intent(mContext, ChannelActivity::class.java)
            startActivityForResult(intent, CODE_CHANNEL)//返回值
        }

        //TODO 主题选择
        fun clickTheme(){
            var intent = Intent(mContext, ThemeActivity::class.java)
            startActivityForResult(intent, CODE_THEME)//返回值
        }

        //TODO 地址选择
        fun clickAddress(){
            var intent = Intent(mContext, AddressActivity::class.java)
            startActivityForResult(intent, CODE_ADDRESS)//返回值
        }

        //当前显示的本地图片的数据
        var imgArr:MutableList<String> = mutableListOf()
        //上传成功的图片路径数据
        var urlArr:MutableList<String> = mutableListOf()

        //TODO 提交发布数据
        fun submit(){
            if(!checkSubmitValue()) return
            for(i in 0 until imgs.size){
                if(!imgs.get(i).path.isNullOrEmpty()){
                    imgArr.add(imgs.get(i).path!!)
                }
            }
            urlArr.clear()
            //第一步先上传图片资源到资源服务器
            if(imgs.size > 0){
                GlobalScope.launch(Dispatchers.Unconfined) {
                    //rxjava zip 操作解决多个异步任务完成的监听
                    for(i in 0 until imgs.size){
                        if(!imgs.get(i).path.isNullOrEmpty()){
                            //图片i - 上传
                            uploadImg(imgs.get(i).path!!)
                        }
                    }
                }
                //创建协程
                runBlocking {
                    // 自定义线程池
                    /*val coroutineDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
                    launch {

                    }.join() //等待协程执行*/

                    //coroutineDispatcher.close() //关闭自定义线程池
                }
            }
        }

        //上传招聘协程
        suspend fun uploadImg(path: String){
            val scaleBitmp: Bitmap = BitmapUtils.getScaleBitmap(path, Global.IMG_WIDTH, Global.IMG_HEIGHT)

            // 上传图片
            val bytes: ByteArray = BitmapUtils.getBytesByBitmap(scaleBitmp)
            //获取Uid
            val uid: String = MyMmkv.getString("uid")!!
            //获取文件名字
            val fileName = uid + "/" + System.currentTimeMillis() + Math.random() * 10000 + ".png"
            val put = PutObjectRequest(bucketName, fileName, bytes)
            put.setProgressCallback(object : OSSProgressCallback<PutObjectRequest> {
                override fun onProgress(
                    request: PutObjectRequest?,
                    currentSize: Long,
                    totalSize: Long
                ) {
                    //上传进度
                    Log.i("oss_upload", "$currentSize/$totalSize")
                }
            })

            val task: OSSAsyncTask<*> = ossClient.asyncPutObject(
                put,
                object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
                    override fun onSuccess(request: PutObjectRequest, result: PutObjectResult) {
                        Log.d("PutObject", "UploadSuccess")
                        Log.d("ETag", result.eTag)
                        Log.d("RequestId", result.requestId)
                        //成功的回调中读取相关的上传文件的信息  生成一个url地址
                        val url = ossClient.presignPublicObjectURL(
                            request.bucketName,
                            request.objectKey
                        )
                        checkUpload(path, url)
                    }

                    override fun onFailure(
                        request: PutObjectRequest,
                        clientExcepion: ClientException,
                        serviceException: ServiceException
                    ) {
                        // 请求异常。
                        if (clientExcepion != null) {
                            // 本地异常，如网络异常等。
                            clientExcepion.printStackTrace()
                        }
                        if (serviceException != null) {
                            // 服务异常。
                            Log.e("ErrorCode", serviceException.errorCode)
                            Log.e("RequestId", serviceException.requestId)
                            Log.e("HostId", serviceException.hostId)
                            Log.e("RawMessage", serviceException.rawMessage)
                        }
                    }
                })
        }

        //TODO 检查图片舒服上传完
        fun checkUpload(path: String, url: String){
            urlArr.add(url)
            for(i in 0 until imgArr.size){
                if(imgArr.get(i).equals(path)){
                    imgArr.removeAt(i)
                    break
                }
            }
            if(imgArr.size == 0){
                var content = getSubmitJson(urlArr)
                mViewModel.submitTrends(content)
            }
        }

        //TODO 检查提交的数据是否已经准备就绪
        fun checkSubmitValue():Boolean{
            var bool = true
            if(imgs.size <= 1){  //当前是否有资源文件
                Toast.makeText(mContext,"没有准备好资源文件", Toast.LENGTH_SHORT).show()
                return false
            }
            //判断当前是否选择频道
            if(channelId <= 0){
                Toast.makeText(mContext,"请选择对应的频道", Toast.LENGTH_SHORT).show()
                return false
            }
            //判断当前是否选择主题
            if(themeId <= 0){
                Toast.makeText(mContext,"请选择对应的主题", Toast.LENGTH_SHORT).show()
                return false
            }
            var titleStr = et_submit_more_title.text.toString()
            if(titleStr.isNullOrEmpty()){
                Toast.makeText(mContext,"请输入对应的标题", Toast.LENGTH_SHORT).show()
                return false
            }
            var mood = et_submit_more_mood.text.toString()
            if(mood.isNullOrEmpty()){
                Toast.makeText(mContext,"请输入心情", Toast.LENGTH_SHORT).show()
                return false
            }
            return bool
        }

    }
}
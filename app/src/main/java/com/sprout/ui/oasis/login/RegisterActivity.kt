package com.sprout.ui.oasis.login

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.View
import com.example.basemvvm.utils.ToastUtils
import com.sprout.MainActivity
import com.sprout.R
import com.sprout.ui.oasis.OasisActivity
import com.sprout.utils.CountDownTimerUtils
import com.sprout.utils.CustomVideoView
import com.sprout.utils.VerifyCodeView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    //创建播放视频的控件对象
    private var videoview: CustomVideoView? = null

    //验证码
    var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //加载视频
        initView()
        //富文本
        Spannable()
        //点击
        initClick()
    }

    //TODO 加载视频
    private fun initView() {
        //加载视频资源控件
        videoview = findViewById(R.id.videoview_register) as CustomVideoView

        //设置播放加载路径

        videoview!!.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.login_bg))
        //模拟器地址
        //videoview!!.setVideoPath(Environment.getExternalStorageDirectory().toString() + "/Pictures/boy.mp4")
        //网络地址
//      videoview!!.setVideoPath( "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4" )

        //播放
        videoview!!.start()
        //循环播放
        videoview!!.setOnCompletionListener { videoview!!.start() }

    }

    //TODO 富文本
    fun Spannable(){
        val string = tv_register_text.text.toString()
        //起始位置
        var startPos = string.indexOf( " " )
        //结束位置
        var endPos = string.indexOf( "|" )
        val spannableString = SpannableString(string)
        //富文本颜色
        spannableString.setSpan( ForegroundColorSpan( Color.parseColor( "#FF9800" ) ), startPos+1, endPos, Spanned.SPAN_INCLUSIVE_INCLUSIVE );//设置EZ的背景色
        spannableString.setSpan( ForegroundColorSpan( Color.parseColor( "#FF9800" ) ), endPos+1,spannableString.length , Spanned.SPAN_INCLUSIVE_INCLUSIVE );//设置EZ前景色，也就是字体颜色
        tv_register_text.setText( spannableString )
    }


    //TODO 点击监听
    private fun initClick() {
        //手机登录注册
        btn_register_phone_login.setOnClickListener(this)
        //其他号码登录
        tv_rehister_else.setOnClickListener(this)
        //微博
        iv_register_wb.setOnClickListener(this)
        //微信
        iv_register_wx.setOnClickListener(this)
        //qq
        iv_register_qq.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //手机登录注册
            R.id.btn_register_phone_login -> {
                initPhone_Login()
            }
            //其他号码登录
            R.id.tv_rehister_else -> {
                initElse()
            }
            //微博
            R.id.iv_register_wb -> {
                ToastUtils.s(this,getString(R.string.wb))
            }
            //微信
            R.id.iv_register_wx -> {
                ToastUtils.s(this,getString(R.string.wc))
            }
            //qq
            R.id.iv_register_qq -> {
                ToastUtils.s(this,getString(R.string.qq))
            }
        }
    }

    //TODO 其他号码登录
    private fun initElse() {
        btn_register_phone_login.setText("获取短信验证码")
        //显示手机号
        ll_register_phone.visibility = View.VISIBLE
        //隐藏本机和其他
        tv_rehister_phone_local.visibility = View.GONE
        tv_rehister_else.visibility = View.GONE
    }

    //TODO 登录
    private fun initPhone_Login() {
        var btn_phone = btn_register_phone_login.text.toString()
        if(btn_phone.equals("本机号码一键登录")){
            //跳转
            startActivity(Intent(this,OasisActivity ::class.java))
        }
        if(btn_phone.equals("获取短信验证码")) {
            var et_phone = et_register_phone.text.toString()
            if (!TextUtils.isEmpty(et_phone)) {
                if (et_phone.length == 11) {
                    //发送验证码
                    initCode()
                } else {
                    ToastUtils.s(this, getString(R.string.register_phone_code))
                }
            } else {
                ToastUtils.s(this, getString(R.string.register_phone))
            }
        }
    }

    //TODO 发送验证码
    private fun initCode() {
        tv_rehister_else.visibility = View.VISIBLE
        verify_code_view_register.visibility = View.VISIBLE
        tv_rehister_else.setTextColor(Color.WHITE)
        tv_rehister_else.setText("输入验证码")
        CountDownTimerUtils(btn_register_phone_login, 60000, 1000).start()
        //监听验证码
        initClickCode()
    }

    //TODO 监听验证码
    private fun initClickCode() {
        verify_code_view_register.setInputCompleteListener(object :
            VerifyCodeView.InputCompleteListener {
            override fun inputComplete() {
                var editContent = verify_code_view_register.editContent
                if(editContent!!.length == 6){
                    //跳转
                    startActivity(Intent(this@RegisterActivity,OasisActivity::class.java))
                }
            }

            override fun invalidContent() {}
        })
    }

    //TODO 返回重启加载
    override fun onRestart() {
        super.onRestart()
        initView()
    }

    //TODO 防止锁屏或者切出的时候，音乐在播放
    override fun onStop() {
        videoview!!.stopPlayback()
        super.onStop()
    }

}
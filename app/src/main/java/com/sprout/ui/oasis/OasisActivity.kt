package com.sprout.ui.oasis

import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.basemvvm.utils.MyMmkv
import com.shop.app.Constants
import com.shop.base.BaseActivity
import com.sprout.R
import com.sprout.databinding.ActivityOasisBinding
import com.sprout.ui.oasis.issue.IssueActivity
import com.sprout.viewmodel.oasis.OasisViewModel

//TODO 绿洲搭建页面
class OasisActivity : BaseActivity<OasisViewModel, ActivityOasisBinding>
    (R.layout.activity_oasis, OasisViewModel::class.java), View.OnClickListener {

    private lateinit var mFm: FragmentManager

    override fun initView() {
        initListener()
        initFragment()
    }

    //TODO fragment管理器
    private fun initFragment() {
        mFm = supportFragmentManager

        //放入布局管理器
        mFm.beginTransaction()
            .add(R.id.mRl_oasis, mViewModel.fragments.get(0))
            .add(R.id.mRl_oasis, mViewModel.fragments.get(1))
            .add(R.id.mRl_oasis, mViewModel.fragments.get(2))
            .add(R.id.mRl_oasis, mViewModel.fragments.get(3))
            .hide(mViewModel.fragments.get(1))
            .hide(mViewModel.fragments.get(2))
            .hide(mViewModel.fragments.get(3))
            .commit();//提交事物
    }

    //TODO 点击事件
    private fun initListener() {
        mDataBinding.layoutHome.setOnClickListener(this)
        mDataBinding.layoutSearch.setOnClickListener(this)
        mDataBinding.layoutIssue.setOnClickListener(this)
        mDataBinding.layoutInfo.setOnClickListener(this)
        mDataBinding.layoutMe.setOnClickListener(this)
    }

    override fun initVM() {

    }

    override fun initData() {
        //模拟已经登录
        MyMmkv.setValue("uid","7fc32459-bcd6-4ab6-95f7-0c43728b2b0b")
        MyMmkv.setValue(Constants.token_key,"sprout-token")
        MyMmkv.setValue(
            Constants.token,
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2ZjMzI0NTktYmNkNi00YWI2LTk1ZjctMGM0MzcyOGIyYjBiIiwicmFuZG9tIjoibzd4enMxb2oxMyIsImlhdCI6MTYxNDA0MjgwN30.4RSkIAKGAFUfqOFPYRJ4A7OVkxcoVk6VXoKUdzvft0U")

    }

    override fun initVariable() {

    }

    //TODO 监听
    override fun onClick(v: View?) {
        //开启事物
        val t1 = mFm.beginTransaction()
        resetImg()//重置所有属性
        when (v!!.id) {
            R.id.layout_home -> {
                mDataBinding.ivOasisHomeImg.setImageResource(R.mipmap.main_nav_home_hl)
                t1.show(mViewModel.fragments.get(0))
                    .hide(mViewModel.fragments.get(1))
                    .hide(mViewModel.fragments.get(2))
                    .hide(mViewModel.fragments.get(3))
            }
            R.id.layout_search -> {
                mDataBinding.ivOasisSearchImg.setImageResource(R.mipmap.main_nav_discover_hl)
                t1.show(mViewModel.fragments.get(1))
                    .hide(mViewModel.fragments.get(0))
                    .hide(mViewModel.fragments.get(2))
                    .hide(mViewModel.fragments.get(3))
            }
            R.id.layout_issue -> {//点击加号跳转
                var intent = Intent(this, IssueActivity::class.java)
                startActivity(intent)
            }
            R.id.layout_info -> {
                mDataBinding.ivOasisInfoImg.setImageResource(R.mipmap.main_nav_message_hl)
                t1.show(mViewModel.fragments.get(2))
                    .hide(mViewModel.fragments.get(1))
                    .hide(mViewModel.fragments.get(0))
                    .hide(mViewModel.fragments.get(3))

            }
            R.id.layout_me -> {
                mDataBinding.ivOasisMeImg.setImageResource(R.mipmap.main_nav_mine_hl)
                t1.show(mViewModel.fragments.get(3))
                    .hide(mViewModel.fragments.get(1))
                    .hide(mViewModel.fragments.get(2))
                    .hide(mViewModel.fragments.get(0))
            }
        }
        t1.commit()
    }

    //TODO 重置所有属性
    private fun resetImg() {
        mDataBinding.ivOasisHomeImg.setImageResource(R.mipmap.main_nav_home_normal)
        mDataBinding.ivOasisSearchImg.setImageResource(R.mipmap.main_nav_discover_normal)
        mDataBinding.ivOasisInfoImg.setImageResource(R.mipmap.main_nav_message_normal)
        mDataBinding.ivOasisMeImg.setImageResource(R.mipmap.main_nav_mine_normal)
    }

}
package com.sprout.adapter.oasis.issue.submit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shop.base.BaseActivity
import com.sprout.R
import com.sprout.databinding.ActivityAddressBinding
import com.sprout.viewmodel.oasis.issue.tags.AdressViewModel

//TODO 地址
class AddressActivity : BaseActivity<AdressViewModel, ActivityAddressBinding>
    (R.layout.activity_address, AdressViewModel::class.java){

    lateinit var mIntent: Intent

    override fun initData() {
        mIntent = getIntent()
    }

    override fun initView() {

    }

    override fun initVM() {

    }

    override fun initVariable() {

    }

    inner class ClickEvt{
        //不显示地址
        fun clickNoAddress(){
            if(mIntent != null){
                setResult(100)
                finish()
            }
        }
    }
}
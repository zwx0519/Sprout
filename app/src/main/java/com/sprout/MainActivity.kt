package com.sprout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sprout.ui.oasis.guidance.IntroductionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_oasis.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_oasis ->{
                var intent=Intent(this,
                    IntroductionActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
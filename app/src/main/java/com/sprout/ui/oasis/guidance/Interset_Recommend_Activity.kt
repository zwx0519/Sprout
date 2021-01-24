package com.sprout.ui.oasis.guidance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.basemvvm.utils.SpUtils
import com.sprout.R
import com.sprout.ui.oasis.OasisActivity
import kotlinx.android.synthetic.main.activity_interset__recommend_.*

class Interset_Recommend_Activity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interset__recommend_)
        initClick()
    }
    private fun initClick() {
        btn_interest_recommend_next_step.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //头像
            R.id.btn_interest_recommend_next_step -> {
                val interset_recommend = "选择兴趣分类的人"
                SpUtils.instance!!.setValue("interset_recommend",interset_recommend)
                startActivity(Intent(this, OasisActivity::class.java))
                finish()
            }
        }
    }
}
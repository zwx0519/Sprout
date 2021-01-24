package com.sprout.ui.oasis.guidance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.example.basemvvm.utils.SpUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.sprout.R
import com.sprout.utils.GlideEngine
import kotlinx.android.synthetic.main.activity_sex.*

//TODO 选择性别引导页
class SexActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)

        initClick()
    }

    private fun initClick() {
        iv_sex_head.setOnClickListener(this)
        iv_sex_et_delete.setOnClickListener(this)
        btn_sex_next_step.setOnClickListener(this)

        mRg_sex.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.mRb_sex_girl -> {
                    //显示 填写好友邀请码（选填） "  下一步
                    et_sex_invitation.visibility = View.VISIBLE
                    btn_sex_next_step.visibility = View.VISIBLE
                    //隐藏 选择性别后继续
                    btn_sex_check_sex.visibility = View.GONE
                }
                R.id.mRb_sex_boy -> {
                    //显示 填写好友邀请码（选填） "  下一步
                    et_sex_invitation.visibility = View.VISIBLE
                    btn_sex_next_step.visibility = View.VISIBLE
                    //隐藏 选择性别后继续
                    btn_sex_check_sex.visibility = View.GONE
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //头像
            R.id.iv_sex_head -> {
                openPhoto()
            }
            //清空
            R.id.iv_sex_et_delete -> {
                et_sex_username.setText("")
            }
            //下一步
            R.id.btn_sex_next_step -> {
                val sex = "选择性别完成"
                SpUtils.instance!!.setValue("sex",sex)
                startActivity(Intent(this,InterestActivity::class.java))
                finish()
            }
        }
    }

    //TODO 打开相册
    private fun openPhoto() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(1)
            .imageSpanCount(4)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

}
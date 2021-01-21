package com.sprout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sprout.ui.oasis.OasisActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_introduction.*
import java.util.concurrent.TimeUnit

//TODO 引导页
class IntroductionActivity : AppCompatActivity() ,View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        btn_intro.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_intro->{
                val subscribe = Observable.intervalRange(0, 2, 0, 1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { aLong ->
                        val l = 1 - aLong
                        if (l == 0L) {
                            startActivity(Intent(this, OasisActivity::class.java))
                            finish()
                        }
                    }
            }
        }

    }
}
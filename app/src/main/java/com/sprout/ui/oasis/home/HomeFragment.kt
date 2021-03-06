package com.sprout.ui.oasis.home

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.basemvvm.utils.MyMmkv
import com.google.android.material.tabs.TabLayout
import com.shop.app.Constants
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentHomeBinding
import com.sprout.viewmodel.oasis.home.HomeViewModel

//TODO 首页页面
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>
    (R.layout.fragment_home, HomeViewModel::class.java) {

   // private val mNames = arrayOf("同城", "关注", "推荐") //tab的导航名字

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { HomeFragment() }
    }

    var pagerAdapter:ViewPage? = null

    override fun initView() {

        mDataBinding.mTabHome.getTabAt(0)?.setIcon(R.drawable.menu_attention_select)
        mDataBinding.mTabHome.getTabAt(1)?.setIcon(R.drawable.menu_recommend_select)
        mDataBinding.mTabHome.getTabAt(2)?.setIcon(R.drawable.menu_local_select)

        //设置适配器
        pagerAdapter = ViewPage(childFragmentManager,mViewModel.fragments)
        //绑定适配器
        mDataBinding.mVpHome.adapter = pagerAdapter
        //设置ViewPager和Tab联动
        mDataBinding.mTabHome.setupWithViewPager(mDataBinding.mVpHome)
        initClick()
    }

    override fun initVM() {

    }

    override fun initData() {

    }


    override fun initVariable() {

    }

    override fun onResume() {
        super.onResume()
        mDataBinding.mTabHome.getTabAt(0)!!.setIcon(R.drawable.menu_attention_select);
        mDataBinding.mTabHome.getTabAt(1)?.setIcon(R.drawable.menu_recommend_select)
        mDataBinding.mTabHome.getTabAt(2)?.setIcon(R.drawable.menu_local_select)
    }
    fun initClick() {
        //在这里插入代码片
        mDataBinding.mTabHome.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {
//                if (tab.customView!=null){
//                    val view  = tab.customView as TextView
//                    // 改变 tab 未选择状态下的字体大小
//                    view.textSize = 18F
//                    // 改变 tab 未选择状态下的字体颜色
//                    context?.let { ContextCompat.getColor(it, R.color.home_tab_unselected) }?.let {
//                        view.setTextColor(
//                            it
//                        )
//                    }
//                }

            }
            override fun onTabSelected(tab: TabLayout.Tab) {
//                if (tab.customView!=null){
//                    // 获取 tab 组件
//                    val view = tab.customView as TextView
//                    // 改变 tab 选择状态下的字体大小
//                    view.textSize = 22F
//                    // 改变 tab 选择状态下的字体颜色
//                    context?.let { ContextCompat.getColor(it, R.color.home_tab_selected) }?.let {
//                        view.setTextColor(
//                            it
//                        )
//                    }
//                }
                if(tab.position==0){
                    mDataBinding.mTabHome.getTabAt(0)?.setIcon(R.drawable.menu_attention_select)
                }else if(tab.position==1){
                    mDataBinding.mTabHome.getTabAt(1)?.setIcon(R.drawable.menu_recommend_select)
                }else{
                    mDataBinding.mTabHome.getTabAt(2)?.setIcon(R.drawable.menu_local_select)
                }

            }
        })
    }


    //TODO  viewpager适配器
    class ViewPage(supportFragmentManager: FragmentManager, val list: List<Fragment>):
        FragmentStatePagerAdapter(supportFragmentManager) {
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

        //, var mNames: Array<String>
//        override fun getPageTitle(position: Int): CharSequence? {
//            return mNames[position]
//        }
    }

}
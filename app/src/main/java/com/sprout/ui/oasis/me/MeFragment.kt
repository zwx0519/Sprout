package com.sprout.ui.oasis.me

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.databinding.FragmentMeBinding
import com.sprout.ui.oasis.home.HomeFragment
import com.sprout.viewmodel.oasis.me.MeViewModel

//TODO 我的页面
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>
    (R.layout.fragment_me, MeViewModel::class.java) {

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { MeFragment() }
    }

    var pagerAdapter: HomeFragment.ViewPage? = null

    override fun initView() {
        mDataBinding.mTabMe.getTabAt(0)?.setIcon(R.drawable.selector_status)
        mDataBinding.mTabMe.getTabAt(1)?.setIcon(R.drawable.selector_like)
        mDataBinding.mTabMe.getTabAt(2)?.setIcon(R.drawable.selector_favorite)
        mDataBinding.mTabMe.getTabAt(3)?.setIcon(R.drawable.selector_at)

        //设置适配器
        pagerAdapter = HomeFragment.ViewPage(childFragmentManager, mViewModel.fragments)
        //绑定适配器
        mDataBinding.mVpMe.adapter = pagerAdapter
        //设置ViewPager和Tab联动
        mDataBinding.mTabMe.setupWithViewPager(mDataBinding.mVpMe)
    }

    override fun onResume() {
        super.onResume()
        mDataBinding.mTabMe.getTabAt(0)?.setIcon(R.drawable.selector_status)
        mDataBinding.mTabMe.getTabAt(1)?.setIcon(R.drawable.selector_like)
        mDataBinding.mTabMe.getTabAt(2)?.setIcon(R.drawable.selector_favorite)
        mDataBinding.mTabMe.getTabAt(3)?.setIcon(R.drawable.selector_at)
    }

    override fun initVM() {

    }

    override fun initData() {
        mDataBinding.mTabMe.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0!!.position == 0) {
                    mDataBinding.mTabMe.getTabAt(0)?.setIcon(R.drawable.selector_status)
                } else if (p0!!.position == 1) {
                    mDataBinding.mTabMe.getTabAt(1)?.setIcon(R.drawable.selector_like)
                } else if (p0!!.position == 2) {
                    mDataBinding.mTabMe.getTabAt(2)?.setIcon(R.drawable.selector_favorite)
                } else {
                    mDataBinding.mTabMe.getTabAt(3)?.setIcon(R.drawable.selector_at)
                }

            }

        })
    }

    override fun initVariable() {

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
    }

}
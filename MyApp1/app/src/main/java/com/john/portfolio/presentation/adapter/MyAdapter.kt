package com.john.portfolio.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.john.portfolio.presentation.CommentsFragment
import com.john.portfolio.presentation.EpisodeFragment
import com.john.portfolio.presentation.MainFragment

class MyAdapter (
    fm: FragmentManager
): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentList = listOf(
        MainFragment(),
        EpisodeFragment(),
        CommentsFragment()
    )

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]    }
}
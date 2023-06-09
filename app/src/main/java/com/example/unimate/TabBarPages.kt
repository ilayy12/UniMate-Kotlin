package com.example.unimate

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabBarPages(fragmentManager: FragmentManager,lifecycle:Lifecycle ) : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> MapFragment()
            3 -> ProfileFragment()
            else -> ProfileFragment()
        }
    }
}
package com.example.unimate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: TabBarPages
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_email)

        setContentView(R.layout.activity_main)
        setTabBar()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, RequestsActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTabBar() {
        tabLayout = findViewById(R.id.tabBarLayout)
        viewPager2 = findViewById(R.id.viewPager)
        adapter = TabBarPages(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter

        val tab1: TabLayout.Tab = tabLayout.newTab().setText("Ana Sayfa")
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab().setText("Arama")
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab().setText("Harita")
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab().setText("Profil")
        tabLayout.addTab(tab4)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}
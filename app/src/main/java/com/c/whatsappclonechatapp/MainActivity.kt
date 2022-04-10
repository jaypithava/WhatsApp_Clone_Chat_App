package com.c.whatsappclonechatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.c.whatsappclonechatapp.adapter.ViewPagerAdapter
import com.c.whatsappclonechatapp.databinding.ActivityMainBinding
import com.c.whatsappclonechatapp.ui.CallFragment
import com.c.whatsappclonechatapp.ui.ChatFragment
import com.c.whatsappclonechatapp.ui.StatusFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        binding!!.viewpager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewpager)
    }
}
package com.c.whatsappclonechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.c.whatsappclonechatapp.activity.PhoneNumberActivity
import com.c.whatsappclonechatapp.adapter.ViewPagerAdapter
import com.c.whatsappclonechatapp.databinding.ActivityMainBinding
import com.c.whatsappclonechatapp.ui.CallFragment
import com.c.whatsappclonechatapp.ui.ChatFragment
import com.c.whatsappclonechatapp.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        auth= FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this,PhoneNumberActivity::class.java))
            finish()
        }

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        binding!!.viewpager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewpager)
    }
}
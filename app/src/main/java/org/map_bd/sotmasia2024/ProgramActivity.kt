package org.map_bd.sotmasia2024

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProgramActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var button: Button
    private lateinit var adapter: pageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)

        button = findViewById(R.id.nextDayId)
        tabLayout = findViewById(R.id.tabdayOneid)
        viewPager2 = findViewById(R.id.viewpageDayOneId)
        adapter = pageAdapter(supportFragmentManager,lifecycle)

        button.setOnClickListener{
            val nextpage = Intent(this,ProgramActivity2::class.java);
            startActivity(nextpage);
        }

//        viewPager2.adapter = adapter
//
//        TabLayoutMediator(tabLayout,viewPager2){tab, position ->
//             when(position){
//                 0 -> {
//                     tab.text = "Room 1"
//                 }
//                 1 -> {
//                     tab.text = "Room 2"
//                 }
//                 2 -> {
//                     tab.text = "Room 3"
//                 }
//                 3 -> {
//                     tab.text = "Room 4"
//                 }
//             }
//
//        }.attach()

        tabLayout.addTab(tabLayout.newTab().setText("Room 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Room 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Room 3"))
        tabLayout.addTab(tabLayout.newTab().setText("Room 4"))

        viewPager2.adapter =adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }
}
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

class ProgramActivity2 : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var adapter: pageAdapter2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program2)

    button = findViewById(R.id.preDayId)
    button2 = findViewById(R.id.homepageId)
    tabLayout = findViewById(R.id.tabdayTowid)
    viewPager2 = findViewById(R.id.viewpageDayTwoId)
    adapter = pageAdapter2(supportFragmentManager,lifecycle)

        button.setOnClickListener{
            val nextpage = Intent(this,ProgramActivity::class.java);
            startActivity(nextpage);
        }
        button2.setOnClickListener{
                val nextpage = Intent(this,MainActivity::class.java);
                startActivity(nextpage);
        }

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
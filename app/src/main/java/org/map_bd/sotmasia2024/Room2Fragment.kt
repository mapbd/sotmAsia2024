package org.map_bd.sotmasia2024

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class Room2Fragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: pageAdapter2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_room2, container, false)

        tabLayout = rootView.findViewById(R.id.tabdaytowid)
        viewPager2 = rootView.findViewById(R.id.viewpagedaytwoId)
        adapter = pageAdapter2(childFragmentManager,lifecycle)


        tabLayout.addTab(tabLayout.newTab().setText("Main Hall"))
        tabLayout.addTab(tabLayout.newTab().setText("Hall 01"))
        tabLayout.addTab(tabLayout.newTab().setText("Hall 02 (Virtuall)"))
        tabLayout.addTab(tabLayout.newTab().setText("Meeting Room"))

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

        return rootView
    }


}
package org.map_bd.sotmasia2024

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


class RoomFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
//    private lateinit var button: Button
    private lateinit var adapter: pageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_room, container, false)


        tabLayout = rootView.findViewById(R.id.tabdayOneid)
        viewPager2 = rootView.findViewById(R.id.viewpageDayOneId)
//        button = rootView.findViewById(R.id.nextDayId)
        adapter = pageAdapter(childFragmentManager,lifecycle)


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

        return rootView



    }


}
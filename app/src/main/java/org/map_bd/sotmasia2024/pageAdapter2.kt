package org.map_bd.sotmasia2024

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.map_bd.sotmasia2024.home.EightFragment
import org.map_bd.sotmasia2024.home.FirstFragment
import org.map_bd.sotmasia2024.home.FiveFragment
import org.map_bd.sotmasia2024.home.SecondFragment
import org.map_bd.sotmasia2024.home.SevenFragment
import org.map_bd.sotmasia2024.home.SixFragment
import org.map_bd.sotmasia2024.home.ThirdFragment

class pageAdapter2(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }


    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                FiveFragment()
            }
            1 ->{
                SixFragment()
            }
            2 ->{
                SevenFragment()
            }
            3 ->{
                EightFragment()
            }
            else ->{
                Fragment()
            }
        }
    }

}
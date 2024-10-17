package org.map_bd.sotmasia2024

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.map_bd.sotmasia2024.home.FirstFragment
import org.map_bd.sotmasia2024.home.SecondFragment
import org.map_bd.sotmasia2024.home.ThirdFragment

class pageAdapter2(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }


    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                FirstFragment()
            }
            1 ->{
                SecondFragment()
            }
            2 ->{
                ThirdFragment()
            }
            3 ->{
                MapFragment()
            }
            else ->{
                Fragment()
            }
        }
    }

}
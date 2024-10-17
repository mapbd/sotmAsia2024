package org.map_bd.sotmasia2024

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.map_bd.sotmasia2024.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), View.OnClickListener {

//    private var _binding : FragmentHomeBinding? = null
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val btn: Button = view.findViewById(R.id.programIds)
        btn.setOnClickListener(this)
        return view

//        _binding = FragmentHomeBinding.inflate(inflater, container,false)
//        return _binding!!.root




    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.programIds -> {
                val intent = Intent(activity,ProgramActivity::class.java)
                startActivity(intent)
            }

            else -> {
            }
        }
    }





}
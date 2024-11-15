package org.map_bd.sotmasia2024.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.map_bd.sotmasia2024.NoteActivity
import org.map_bd.sotmasia2024.ProgramActivity
import org.map_bd.sotmasia2024.R
import org.map_bd.sotmasia2024.databinding.FragmentHomeBinding
import org.map_bd.sotmasia2024.ui.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        val btn: Button = view.findViewById(R.id.programIds)
        btn.setOnClickListener{
            val intent = Intent(activity,ProgramActivity::class.java)
            startActivity(intent)
            val model = Build.MODEL.toString()
            val brand = Build.BRAND.toString()
            val ids = Build.ID.toString()
           viewModel.postData(model,brand,ids)
        }

        viewModel.getStatus().observe(this,{
            Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
        })

        val fab: FloatingActionButton =view.findViewById(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(activity,NoteActivity::class.java)
            startActivity(intent)
        }

        return view

    }



}
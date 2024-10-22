package org.map_bd.sotmasia2024

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.map_bd.sotmasia2024.databinding.ActivityEmergencyBinding


class EmergencyActivity : AppCompatActivity() {


private lateinit var binding: ActivityEmergencyBinding
private lateinit var userArrayList: ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmergencyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageId = intArrayOf(
            R.drawable.sawan, R.drawable.ribin, R.drawable.atik
        )
        val name = arrayOf(
            "Sawan Shariar",
            "Tasauf A Baki Billah",
            "Atikur Rahman"
        )

        val phone = arrayOf(

            "+8801822844848",
            "+8801741589653",
            "+8801723466199"

        )



        userArrayList = ArrayList()

        for (i in name.indices){

            val user = User(name[i], phone[i], imageId[i])
            userArrayList.add(user)
        }

        binding.listId.isClickable = true
        binding.listId.adapter = EmergencyAdapter(this,userArrayList)
        binding.listId.setOnItemClickListener { parent, view, position, id ->

            val name = name[position]
            val phone = phone[position]
            val imageId = imageId[position]

            val i = Intent(this,UserActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("phone", phone)
            i.putExtra("imageId", imageId)
            startActivity(i)


        }

    }


}
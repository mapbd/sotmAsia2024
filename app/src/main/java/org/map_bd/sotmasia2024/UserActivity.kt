package org.map_bd.sotmasia2024

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.map_bd.sotmasia2024.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("name")
        var phone = intent.getStringExtra("phone")
        var imageId = intent.getIntExtra("imageId",R.drawable.unnamed)

        binding.profileName.text = name
        binding.profilePhone.text = phone
        binding.profileImage.setImageResource(imageId)



        binding.callId.setOnClickListener {

            var number = binding.profilePhone.text.toString()

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
            if (ActivityCompat.checkSelfPermission(this@UserActivity,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this@UserActivity, arrayOf(android.Manifest.permission.CALL_PHONE),1)
            }else
            {
                startActivity(intent)
            }


        }





    }
}
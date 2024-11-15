package org.map_bd.sotmasia2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.map_bd.sotmasia2024.databinding.ActivityCopBinding

class CopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.sotmAsia.setOnClickListener{
            openUrl("https://sotm-asia.github.io/sotm-asia-2024-website/")
        }
        binding.sotmBd.setOnClickListener{
            openUrl("https://sotmbd.org/")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }

        }
        return true
    }

    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }
}
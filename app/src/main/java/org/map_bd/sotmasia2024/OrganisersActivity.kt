package org.map_bd.sotmasia2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.map_bd.sotmasia2024.databinding.ActivityOrganisersBinding

class OrganisersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrganisersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrganisersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.boilId.setOnClickListener{
            openUrl("https://boiledbhoot.org/")
        }
        binding.osmbdId.setOnClickListener{
            openUrl("https://osmbd.org/")
        }
        binding.ymId.setOnClickListener{
            openUrl("https://www.facebook.com/groups/2923232317707849")
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
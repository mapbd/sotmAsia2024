package org.map_bd.sotmasia2024

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ui.onNavDestinationSelected
import org.map_bd.sotmasia2024.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    //private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.versionText.text = getCurrentVersion(packageManager, packageName)



        binding.versionsId.setOnClickListener{
            openUrl("https://mapbd.github.io/sotmAsia2024")

        }





        setSupportActionBar(binding.appBarHome.toolbar)

        binding.appBarHome.fab.setOnClickListener {
            val nextpages = Intent(this,NoteActivity::class.java)
            startActivity(nextpages)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.versionsId, R.id.coc, R.id.cop, R.id.organisers, R.id.sponsors
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getCurrentVersion(packageManager: PackageManager, packageName: String, flags: Int = 0): String{
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong())).versionName
            }else{
                packageManager.getPackageInfo(packageName, flags).versionName
            }

        }catch (ex: Exception){
            return ""
        }
    }


    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }


}
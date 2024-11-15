package org.map_bd.sotmasia2024

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import org.map_bd.sotmasia2024.databinding.ActivityMainBinding
import org.map_bd.sotmasia2024.ui.help.HelpActivity
import org.map_bd.sotmasia2024.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.open_nav,R.string.close_nav)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
//                R.id.home -> openFragment(HomeFragment())
                R.id.help -> {
                    val nextpage = Intent(this,HelpActivity::class.java);
                    startActivity(nextpage);
                }
//                R.id.coc -> openFragment(CocFragment())
 //               R.id.map -> openUrl("https://www.google.com/maps/dir//Shimanto+Shommelon+Kendra+Urme,+Laboni+Beach+Rd,+Cox'sBazar+4700/@21.4260547,91.9739479,18z/data=!4m18!1m8!3m7!1s0x30adc900317b8587:0x2bcba06cf2c23b16!2sShimanto+Shommelon+Kendra+Urme!8m2!3d21.4260625!4d91.9739375!15sCh5TaGltYW50byBTb21tZWxvbiBLZW5kcmEgVXJtZWWSAQVob3RlbOABAA!16s%2Fg%2F11w7kwv6nt!4m8!1m0!1m5!1m1!1s0x30adc900317b8587:0x2bcba06cf2c23b16!2m2!1d91.9739375!2d21.4260625!3e2?entry=ttu&g_ep=EgoyMDI0MTAxNS4wIKXMDSoASAFQAw%3D%3D")
                R.id.map -> {
                    val nextpage = Intent(this,HomeActivity::class.java);
                    startActivity(nextpage);
                }
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fab.setOnClickListener{
            val nextpages = Intent(this,NoteActivity::class.java)
            startActivity(nextpages)
        }

        binding.versionText.text = getCurrentVersion(packageManager, packageName)


// Replace with your desired color code
        changeStatusBarColor("#46449B")
    }

    private fun changeStatusBarColor(color: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = android.graphics.Color.parseColor(color)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.x -> Toast.makeText(this,"Social Media",Toast.LENGTH_LONG).show()
            R.id.x -> openUrl("https://x.com/sotmasia")
            R.id.wiki -> openUrl("https://wiki.openstreetmap.org/wiki/State_of_the_Map_Asia")
//            R.id.sponsors -> openFragment(SponsorsFragment())
//            R.id.coprograms -> openFragment(CopFragment())
//            R.id.organisers -> openFragment(OrganisersFragment())
//            R.id.aboutId -> openFragment(AboutFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openUrl(link: String) {
            val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
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


}


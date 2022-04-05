package com.cooper.android.easybet

import android.app.Application
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.cooper.android.easybet.databinding.ActivityMainBinding
import java.util.*

object BetList{


    var idList = mutableListOf<UUID>()
    var titleList = mutableListOf<String>()
    var potList = mutableListOf<Int>()

    fun newBet(title: String, pot: Int) {
        val id: UUID = UUID.randomUUID()
        idList.add(id)
        titleList.add(title)
        potList.add(pot)
    }

    fun getPot(id: UUID): Int{
        val i = idList.indexOf(id)
        return potList[i]
    }
    fun getTitle(id: UUID): String {
        val i = idList.indexOf(id)
        return titleList[i]
    }

}

object Wallet{
    private var money = 50
    fun withdraw(amount: Int){
        money -= amount
    }
    fun credit(amount: Int){
        money += amount
    }
    fun balance(): Int {
        return money
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Notifications", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_wallet, R.id.nav_room, R.id.bet_list
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
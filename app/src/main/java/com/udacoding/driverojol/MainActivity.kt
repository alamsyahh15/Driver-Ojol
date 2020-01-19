package com.udacoding.driverojol

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.udacoding.driverojol.ui.profile.ProfileFragment
import com.udacoding.driverojol.ui.request.RequestFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationListerner = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.navigation_request -> {
                setFragment(RequestFragment())
                return@OnNavigationItemSelectedListener  true
            }

            R.id.navigation_profile -> {
                setFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener  true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(RequestFragment())
        nav_view.setOnNavigationItemSelectedListener (navigationListerner)
    }

    fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().
            replace(R.id.container,fragment).commit()
    }
}

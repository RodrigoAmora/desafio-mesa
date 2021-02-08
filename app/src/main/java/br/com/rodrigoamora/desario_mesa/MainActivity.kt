package br.com.rodrigoamora.desario_mesa

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.desario_mesa.dao.TokenDao
import br.com.rodrigoamora.desario_mesa.ui.login.LoginActivity
import br.com.rodrigoamora.desario_mesa.ui.news.NewsFragment
import br.com.rodrigoamora.desario_mesa.util.FragmentUtil
import br.com.rodrigoamora.desario_mesa.util.ShareUtil
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        createToolbarAndNavigationView()
        changeFragment(NewsFragment(), null)
        //checkPermission()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_exit) {
            val tokenDao = TokenDao()
            tokenDao.deleteAccessToken(this)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_news) {

        }
        if (item.itemId == R.id.nav_share) {
            ShareUtil.directShare(this,
                                    getString(R.string.share_title),
                                    getString(R.string.share_text))
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun createToolbarAndNavigationView() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
                    drawer, toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.itemIconTintList = null
    }

    private fun changeFragment(fragment: Fragment, bundle: Bundle?) {
        FragmentUtil.changeFragment(R.id.container, fragment, getSupportFragmentManager(), true, bundle);
    }

    private fun checkPermission(): Boolean {
        val resultAccessFineLocation =
            ContextCompat.checkSelfPermission(applicationContext, ACCESS_FINE_LOCATION)

        val resultAccessCoarseLocation =
            ContextCompat.checkSelfPermission(applicationContext, ACCESS_COARSE_LOCATION)

        return resultAccessFineLocation == PackageManager.PERMISSION_GRANTED &&
                resultAccessCoarseLocation == PackageManager.PERMISSION_GRANTED
    }
}

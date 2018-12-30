package tuppersoft.com.practica2.usescases.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*
import tuppersoft.com.data.Repository
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.EXTRA_TUTORIAL
import tuppersoft.com.practica2.usescases.global.GlobalActivity
import tuppersoft.com.practica2.usescases.global.THEME
import tuppersoft.com.practica2.usescases.splash.SplashActivity


class MainActivity : GlobalActivity(), ActionsMenu {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar()
        setAdapter()
        MainPlaceHolderFragment.replaceFragment(this, getString(R.string.tittle_post))
    }

    private fun setActionBar() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }


    private fun setAdapter() {
        val viewAdapter = MainDrawerListAdapter(createMenu(), this)
        idMenu.layoutManager = LinearLayoutManager(this)
        idMenu.adapter = viewAdapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun createMenu(): ArrayList<ItemModel> {
        val menu = ArrayList<ItemModel>()
        menu.add(ItemHeader())
        menu.add(ItemSection(getString(R.string.comunity)))
        menu.add(ItemMenu(getString(R.string.tittle_post), R.drawable.ic_post_menu))
        menu.add(ItemMenu(getString(R.string.tittle_albums), R.drawable.ic_albums_menu))
        menu.add(ItemMenu(getString(R.string.tittle_users), R.drawable.ic_users_menu))
        menu.add(ItemSection(getString(R.string.miscellaneous)))
        menu.add(
            ItemCheck(
                getString(R.string.dark_theme),
                R.drawable.ic_theme,
                Repository.loadPreference(this, THEME, R.style.AppTheme) as Int
            )
        )
        menu.add(ItemMenu(getString(R.string.tutorial), R.drawable.ic_tutorial))
        menu.add(ItemMenu(getString(R.string.logout), R.drawable.ic_logout))
        return menu
    }

    override fun onClickItemMenu(id: String) {
        when (id) {
            getString(R.string.tutorial) -> startTutorial()
            getString(R.string.logout) -> finish()
            getString(R.string.tittle_post) -> MainPlaceHolderFragment.replaceFragment(this, id)
            getString(R.string.tittle_albums) -> MainPlaceHolderFragment.replaceFragment(this, id)
            getString(R.string.tittle_users) -> MainPlaceHolderFragment.replaceFragment(this, id)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun onSwichChange(isChecked: Boolean) {
        if (isChecked) {
            Repository.savePreference(this, THEME, R.style.AppThemeDark)

        } else {
            Repository.savePreference(this, THEME, R.style.AppTheme)
        }
        this.recreate()
    }

    private fun startTutorial() {
        val i = Intent(baseContext, SplashActivity::class.java)
        i.putExtra(EXTRA_TUTORIAL, true)
        startActivity(i)
    }

    fun onSectionAttached(section: String) {
        supportActionBar?.title = section
    }
}

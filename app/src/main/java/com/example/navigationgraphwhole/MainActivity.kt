package com.example.navigationgraphwhole

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationgraphwhole.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        /**
         *
         * Step 1 create new resource file -> navigation
         *
         * Step 2 create new navigation file -> main_nav
         *
         * Step 3 Makes fragments
         *
         * Step 4 Make changes in Nav graph
         *
         */

        /**
         *
         *
         *
         * New TOPIC after handling PopUpTo And PopUpToInclusive in Send cash Fragment and ChooseReceiverFragment
         *
         *
         * Setting UP action bar and tool bar in Navigation Graph
         *
         */

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        /**
         *
         * Action bar setting up
         *
         */
//        setupActionBarWithNavController(navController)
        /**
         *
         * After setting action bar change theme (remove NoActionBar from theme of project
         *
         *
         * Handling of back button of action bar
         * onSupportNavigateUp()
         *
         */


        /**
         *
         * Now setting up tool bar.
         *
         * Go activity main xml
         *
         *
         * And now write this line tool bar setup done
         */
        // setSupportActionBar(binding.toolBar)

        /**
         *
         * To change the fragment title when switching the fragments can be done through label in navgraph
         *
         */

        /**
         *
         * Creating menu file
         *
         * As we have to navigate to Settings from menu, so after creating Settings fragment
         *
         * Place it in navgraph and from their copy it's id  give that same id to the menu item.
         *
         * Write these two functions onCreateOptionsMenu and onOptionsItemSelected and write onNavDestinationSelected
         * to set up navcontroller
         *
         */

        /**
         *
         * After doing this backstack not maintained, on backpress it was navigating to main fragment
         *  <item
         *         android:id="@+id/settingsFragment"
         *         android:title="Settings"
         *         app:showAsAction="never"
         *         />
         *
         *         We need to add category = secondary to maintain backstack
         *
         *
         */

        /**
         *
         * Now working on Global actions
         *
         * Create About App fragment
         *
         * Now go to navgraph, add it and then right click on it Actions -> add global action.
         *
         * Check usage in Settings fragment (About this app button)
         *
         *
         * Made another use of it made another menu item.
         */


        /**
         *
         * Now integration bottom navigation (Bottom nav) using
         * Navigation Graph
         *
         * Created Notification fragment
         *
         * Create bottom navigation menu
         *
         *
         * Make changes in activity  main file.
         *
         *
         */
        binding.bottomNav.setupWithNavController(navController)

        /**
         *
         * As back button would be available at the top of bottom nav too so for that...->
         *
         */
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.settingsFragment, R.id.notificationsFragment),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        /**
         *
         * Setting up drawer layout now in activity_main
         *
         * Create a new menu file for Drawer nav menu
         *
         *
         *    We need to write this line binding.drawerLayout in AppBarConfiguration for Hamburger icon for  drawer layout
         *
         *    and copy appBarConfiguration and paste it in navController.navigateUp()
         */
        binding.navDrawer.setupWithNavController(navController)


        /**
         *
         * Setting up deeplink
         * Go to navgraph check send cash fragment
         * Click on deeplink (it will show arguement too which is required to launch it.)
         *
         *
         * Now go to Android Manifest file
         * And write nav-graph tag only....
         * Prior to nav graph we need to specify all the deeplinks
         *
         */

        /**
         *
         * Checking Implicit deeplink through notifications
         *
         * Made a new file App
         *
         * Give it declaration in Manifest file
         *
         */

        /**
         *
         * We are creating a notification from ChooseReceiverFragment if user fills his name
         * then we on clicking "Next" btn we are firing notification for the user
         * in case user exits the app
         *
         * So user can click on notification and open SendCashFragment directly.
         *
         * CHECK ChooseReceiverFragment
         *
         */

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_about_app -> {
                val action = MainNavDirections.actionGlobalAboutAppFragment()
                navController.navigate(action)
                true
            }

            else -> {
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
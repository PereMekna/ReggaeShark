package fr.epsi.louisdupont.projet.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import fr.epsi.louisdupont.projet.Adapter.MatchAdapter;
import fr.epsi.louisdupont.projet.DAO.MatchDAO;
import fr.epsi.louisdupont.projet.Fragment.GameFragment;
import fr.epsi.louisdupont.projet.Fragment.HistoryFragment;
import fr.epsi.louisdupont.projet.Game.GameController;
import fr.epsi.louisdupont.projet.R;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    private MatchDAO matchDAO;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private GameController gc;
    private GameFragment gameFragment;
    private HistoryFragment historyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_container);

        historyFragment = new HistoryFragment();
        gameFragment = new GameFragment();



        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Historique");
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        setupDrawerContent(nvDrawer);

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        Menu menu = nvDrawer.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_switch);
        View actionView = MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFragment.setCheat(!gameFragment.isCheat());
            }
        });

        matchDAO = new MatchDAO(getApplicationContext());
        startHistoryFragment();

    }

    private void startHistoryFragment() {
        mListView = (ListView) findViewById(android.R.id.list);
        matchDAO.open();

        MatchAdapter adapter = new MatchAdapter(MainActivity.this, matchDAO.getAllMatch());
        mListView.setAdapter(adapter);
    }

    public void onCarClick(View view) {
        gameFragment.onCarClick(view);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        matchDAO.close();
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(menuItem.getItemId()) {
            case R.id.play:
                toolbar.setTitle("Jeu");
                fragmentManager.beginTransaction().replace(R.id.flContent, gameFragment).commit();
                menuItem.setChecked(true);
                break;
            case R.id.history:
                toolbar.setTitle("Historique");
                fragmentManager.beginTransaction().replace(R.id.flContent, historyFragment).commit();
                menuItem.setChecked(true);
                break;
            case R.id.delete:
                matchDAO.drop();
                historyFragment.updateListView();
                break;
            default:
                break;
        }


        // Highlight the selected item has been done by NavigationView

        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}

package fr.epsi.louisdupont.projet.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import fr.epsi.louisdupont.projet.Game.GameController;
import fr.epsi.louisdupont.projet.R;

public class GameActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private GameController gc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView scorePlayer = (TextView) findViewById(R.id.scorePlayer);
        TextView scoreComputer = (TextView) findViewById(R.id.scoreComputer);
        gc = new GameController(getApplicationContext());
        gc.newRandomCar();

        scorePlayer.setText("Joueur : " + gc.getScorePlayer());
        scoreComputer.setText("Ordi : " + gc.getScoreComputer());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Jeu");
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.delete:
                return true;
            case R.id.history:
                Intent i = new Intent(GameActivity.this, MainActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

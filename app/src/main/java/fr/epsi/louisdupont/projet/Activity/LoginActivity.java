package fr.epsi.louisdupont.projet.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.epsi.louisdupont.projet.Bean.User;
import fr.epsi.louisdupont.projet.R;

public class LoginActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.but_sign_in).setVisibility(View.VISIBLE);
    }

    public void login(View view) {
        if (isNetworkAvailable()) {
            findViewById(R.id.loading).setVisibility(View.VISIBLE);
            findViewById(R.id.but_sign_in).setVisibility(View.GONE);
            new WebService().execute(new User(((EditText) findViewById(R.id.et_name)).getText().toString(), ((EditText) findViewById(R.id.et_first_name)).getText().toString()));
        } else {
            Toast.makeText(getApplicationContext(), "Problème réseau", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class WebService extends AsyncTask<User, Void, String> {
        private URL url;

        public WebService() {
        }

        @Override
        protected String doInBackground(User... users) {
            InputStream in = null;
            try {
                url = new URL("http://solebain.fr/epsi/tourolf/Connexion/?nom=" + users[0].getName() + "&prenom=" + users[0].getFirstName());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.but_sign_in).setVisibility(View.VISIBLE);
            if (s.equals("\"1\"")) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                super.onPostExecute(s);
            }
            else {
                Toast.makeText(getApplicationContext(), "Identifiant(s) incorrect(s)", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

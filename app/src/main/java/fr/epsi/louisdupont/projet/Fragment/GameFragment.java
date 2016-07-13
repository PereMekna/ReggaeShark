package fr.epsi.louisdupont.projet.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import fr.epsi.louisdupont.projet.Bean.Car;
import fr.epsi.louisdupont.projet.DAO.MatchDAO;
import fr.epsi.louisdupont.projet.Game.GameController;
import fr.epsi.louisdupont.projet.R;
import fr.epsi.louisdupont.projet.Utils.CarColor;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    ListView mListView;
    private MatchDAO matchDAO;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private GameController gc;
    private boolean cheat;

    public boolean isCheat() {
        return cheat;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;

        ImageView imgComput = (ImageView) getView().findViewById(R.id.imgComput);
        if (cheat) {
            switch (gc.getComputChoice().getCarColor()) {
                case Blue:
                    imgComput.setImageResource(R.drawable.blue_car);
                    break;
                case Green:
                    imgComput.setImageResource(R.drawable.green_car);
                    break;
                case Red:
                    imgComput.setImageResource(R.drawable.red_car);
                    break;
            }
            TextView cheatText = (TextView) getView().findViewById(R.id.cheat);
            cheatText.setText("cheat");
        }
    }


    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gc = new GameController(getActivity());
        gc.newRandomCar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView scorePlayer = (TextView) getView().findViewById(R.id.scorePlayer);
        TextView scoreComputer = (TextView) getView().findViewById(R.id.scoreComputer);

        scorePlayer.setText("Joueur : " + gc.getScorePlayer());
        scoreComputer.setText("Ordi : " + gc.getScoreComputer());
    }

    public void onCarClick(View view) {
        ImageView imgComput = (ImageView) getView().findViewById(R.id.imgComput);
        TextView scorePlayer = (TextView) getView().findViewById(R.id.scorePlayer);
        TextView scoreComputer = (TextView) getView().findViewById(R.id.scoreComputer);
        Car c = new Car();
        if (!cheat) {
            switch (gc.getComputChoice().getCarColor()) {
                case Blue: imgComput.setImageResource(R.drawable.blue_car); break;
                case Green: imgComput.setImageResource(R.drawable.green_car); break;
                case Red: imgComput.setImageResource(R.drawable.red_car); break;
            }
        }

        switch (view.getId()) {
            case R.id.imgBlue: c.setCarColor(CarColor.Blue); break;
            case R.id.imgGreen: c.setCarColor(CarColor.Green); break;
            case R.id.imgRed: c.setCarColor(CarColor.Red); break;
        }

        gc.nextTour(c);
        scorePlayer.setText("Joueur : " + gc.getScorePlayer());
        scoreComputer.setText("Ordi : " + gc.getScoreComputer());
        gc.newRandomCar();

        if (cheat) {
            switch (gc.getComputChoice().getCarColor()) {
                case Blue: imgComput.setImageResource(R.drawable.blue_car); break;
                case Green: imgComput.setImageResource(R.drawable.green_car); break;
                case Red: imgComput.setImageResource(R.drawable.red_car); break;
            }
        }

        final ImageView imageView = (ImageView) view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imageView.setBackground(getResources().getDrawable(R.drawable.border_red));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageView.setBackground(getResources().getDrawable(R.drawable.border_grey));
                    }
                }
            }, 500);
        }
    }
}

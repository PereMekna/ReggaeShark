package fr.epsi.louisdupont.projet.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.epsi.louisdupont.projet.Adapter.MatchAdapter;
import fr.epsi.louisdupont.projet.Bean.Match;
import fr.epsi.louisdupont.projet.DAO.MatchDAO;
import fr.epsi.louisdupont.projet.Game.GameController;
import fr.epsi.louisdupont.projet.R;

public class HistoryFragment extends Fragment {
    ListView mListView;
    private MatchDAO matchDAO;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private GameController gc;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        matchDAO = new MatchDAO(getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mListView = (ListView) getView().findViewById(android.R.id.list);
        matchDAO.open();
        List<Match> matchList = matchDAO.getAllMatch();
        MatchAdapter adapter = new MatchAdapter(getActivity(), matchList);
        mListView.setAdapter(adapter);
        TextView emptyText = (TextView)getActivity().findViewById(android.R.id.empty);
        mListView.setEmptyView(emptyText);
    }

    public void updateListView() {
        if (matchDAO != null && getActivity() != null) {
            matchDAO.open();
            MatchAdapter adapter = new MatchAdapter(getActivity(), matchDAO.getAllMatch());
            mListView.setAdapter(adapter);
            TextView emptyText = (TextView)getActivity().findViewById(android.R.id.empty);
            mListView.setEmptyView(emptyText);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        matchDAO.close();
        super.onStop();
    }
}

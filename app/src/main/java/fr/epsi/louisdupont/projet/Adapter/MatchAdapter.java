package fr.epsi.louisdupont.projet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.epsi.louisdupont.projet.Bean.Match;
import fr.epsi.louisdupont.projet.R;

/**
 * Created by Loulou on 07/06/2016.
 */
public class MatchAdapter extends ArrayAdapter<Match> {

    //tweets est la liste des models à afficher
    public MatchAdapter(Context context, List<Match> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_match,parent, false);
        }

        MatchViewHolder viewHolder = (MatchViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MatchViewHolder();
            viewHolder.namePlayer = (TextView) convertView.findViewById(R.id.namePlayer);
            viewHolder.resPlayer = (ImageView) convertView.findViewById(R.id.resPlayer);
            viewHolder.resComput = (ImageView) convertView.findViewById(R.id.resComput);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Match match = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.namePlayer.setText((match.getNamePlayer()));
        switch (match.getResPlayer()) {
            case Blue: viewHolder.resPlayer.setImageResource(R.drawable.blue_car); break;
            case Green: viewHolder.resPlayer.setImageResource(R.drawable.green_car); break;
            case Red: viewHolder.resPlayer.setImageResource(R.drawable.red_car); break;
        }
        switch (match.getResComput()) {
            case Blue: viewHolder.resComput.setImageResource(R.drawable.blue_car); break;
            case Green: viewHolder.resComput.setImageResource(R.drawable.green_car); break;
            case Red: viewHolder.resComput.setImageResource(R.drawable.red_car); break;
        }

        return convertView;
    }

    private class MatchViewHolder {
        public TextView namePlayer;
        public ImageView resPlayer, resComput;
    }
}
package fr.epsi.louisdupont.projet.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.louisdupont.projet.Bean.Match;
import fr.epsi.louisdupont.projet.Utils.CarColor;

/**
 * Created by Loulou on 20/05/2016.
 */
public class MatchDAO extends DAO {
    // table match
    private static final String TABLE_MATCH = "match";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME_PLAYER = "name_player";
    private static final String COLUMN_RES_PLAYER = "res_player";
    private static final String COLUMN_RES_COMPUT = "res_comput";


    private static final String DATABASE_NAME = "beers.db";
    private static final int DATABASE_VERSION = 1;
    //créons la table match
    public static final String DATABASE_CREATE_MATCH = "create table "
            + TABLE_MATCH + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME_PLAYER + " text not null, "
            + COLUMN_RES_PLAYER + " text not null, "
            + COLUMN_RES_COMPUT + " text not null";

    public MatchDAO(Context pContext) {
        super(pContext);
    }

    public void drop() {
        mDb.delete(TABLE_MATCH, null, null);
    }

    public void add(Match m) {
        ContentValues value = new ContentValues();
        value.put(MatchDAO.COLUMN_NAME_PLAYER, m.getNamePlayer());
        value.put(MatchDAO.COLUMN_RES_PLAYER, m.getResPlayer().toString());
        value.put(MatchDAO.COLUMN_RES_COMPUT, m.getResComput().toString());
        mDb.insert(MatchDAO.TABLE_MATCH, null, value);
    }
    

    public List<Match> getAllMatch() {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_MATCH, null);
        List<Match> matchList = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                Match m = new Match();
                m.setNamePlayer(c.getString(c.getColumnIndex(MatchDAO.COLUMN_NAME_PLAYER)));
                switch (c.getString(c.getColumnIndex(MatchDAO.COLUMN_RES_PLAYER))) {
                    case "blue": m.setResPlayer(CarColor.Blue); break;
                    case "green": m.setResPlayer(CarColor.Green); break;
                    case "red": m.setResPlayer(CarColor.Red); break;
                }
                switch (c.getString(c.getColumnIndex(MatchDAO.COLUMN_RES_COMPUT))) {
                    case "blue": m.setResComput(CarColor.Blue); break;
                    case "green": m.setResComput(CarColor.Green); break;
                    case "red": m.setResComput(CarColor.Red); break;
                }

                matchList.add(m);
            }while(c.moveToNext());
        }
        c.close();
        return matchList;
    }
}

package fr.epsi.louisdupont.projet.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Loulou on 20/05/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
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
            + COLUMN_RES_COMPUT + " text not null)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_MATCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXIST" + TABLE_MATCH);
        onCreate(db);
    }
}

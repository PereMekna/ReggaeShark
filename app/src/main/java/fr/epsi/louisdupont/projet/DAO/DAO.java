package fr.epsi.louisdupont.projet.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.epsi.louisdupont.projet.DataBase.DataBaseHelper;

/**
 * Created by Loulou on 20/05/2016.
 */
public abstract class DAO {

    protected final static int VERSION = 1;
    protected final static String NOM = "match.db";

    protected SQLiteDatabase mDb = null;
    protected DataBaseHelper mHandler = null;

    public DAO(Context pContext) {
        this.mHandler = new DataBaseHelper(pContext);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

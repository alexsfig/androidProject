package net.gr33nme3dia.alex.pokedex;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import net.gr33nme3dia.alex.pokedex.data.PokedexDbHelper;

/**
 * Created by alex on 12/11/14.
 */
public class PokedexDatabaseTest extends AndroidTestCase {
    public void testCreateDB() throws Throwable{
        mContext.deleteDatabase(PokedexDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new PokedexDbHelper(this.mContext).getReadableDatabase();
        assertEquals(true, db.isOpen());
    }
}

package net.gr33nme3dia.alex.pokedex;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import net.gr33nme3dia.alex.pokedex.data.PokedexContract.*;

import net.gr33nme3dia.alex.pokedex.data.*;

/**
 * Created by alex on 12/11/14.
 */
public class PokedexDatabaseTest extends AndroidTestCase {
    public void testCreateDB() throws Throwable{
        mContext.deleteDatabase(PokedexDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new PokedexDbHelper(this.mContext).getReadableDatabase();
        assertEquals(true, db.isOpen());
    }
    private void dropDatabase() {
        mContext.deleteDatabase(PokedexDbHelper.DATABASE_NAME);
    }
    public void TestCreateDb() throws  Throwable {
        dropDatabase();
        SQLiteDatabase db = new PokedexDbHelper(mContext).getWritableDatabase();
        assertTrue(db.isOpen());
    }

    public void TestInsertPokemonInDb() throws Throwable {
        dropDatabase();
        Pokemon pokemon = new Pokemon("Charizard", "http://img4.wikia.nocookie.net/__cb20120719191130/pokecube/images/9/95/Charizard.png", "1.7", "90.5");
        PokedexDbHelper pokemonSQLHelper = new PokedexDbHelper(mContext);
        Integer pokemon_id = 1;
        Long pokemon_id_insert = pokemonSQLHelper.getWritableDatabase().insert(PokemonEntry.TABLE_NAME, null, insertPokemonValues(pokemon_id, pokemon));
        assertEquals(pokemon_id, pokemon_id_insert);
    }

    private ContentValues insertPokemonValues(Integer id, Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put(PokemonEntry._ID, id);
        values.put(PokemonEntry.COLUMN_NOMBRE, pokemon.getNombre());
        values.put(PokemonEntry.COLUMN_AVATAR, pokemon.getAvatar());
        values.put(PokemonEntry.COLUM_ALTURA, pokemon.getAltura());
        values.put(PokemonEntry.COLUM_PESO, pokemon.getPeso());
        return values;
    }
}

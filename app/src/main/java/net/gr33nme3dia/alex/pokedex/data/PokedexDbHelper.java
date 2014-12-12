package net.gr33nme3dia.alex.pokedex.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.gr33nme3dia.alex.pokedex.Pokemon;
import net.gr33nme3dia.alex.pokedex.data.PokedexContract.*;

/**
 * Created by alex on 12/11/14.
 */
public class PokedexDbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "pokemon.db";
    public static int DATABASE_VERSION = 1;

    public PokedexDbHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_POKEMON_TABLE = "CREATE TABLE "+ PokemonEntry.TABLE_NAME+ "(" +
                PokemonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PokemonEntry.COLUMN_AVATAR + " TEXT NOT NULL," +
                PokemonEntry.COLUMN_NOMBRE + " TEXT NOT NULL," +
                PokemonEntry.COLUMN_NUMERO + " INTEGER NOT NULL," +
                PokemonEntry.COLUM_ALTURA + " REAL NOT NULL," +
                PokemonEntry.COLUM_PESO + " REAL NOT NULL );";
        final String SQL_CREATE_LUGAR_TABLE =" CREATE TABLE"+ LugarEntry.TABLE_NAME +"(" +
                LugarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LugarEntry.COLUMN_LATITUD + " REAL NOT NULL," +
                LugarEntry.COLUMN_LONGITUD + " REAL NOT NULL," +
                LugarEntry.COLUMN_POKEMON_ID + " INTEGER NOT NULL," +
                "FOREIGN KEY(" + LugarEntry.COLUMN_POKEMON_ID + ") REFERENCES " +
                PokemonEntry.TABLE_NAME +" (" + PokemonEntry._ID + ") );";

        sqLiteDatabase.execSQL(SQL_CREATE_POKEMON_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LUGAR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + PokemonEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + LugarEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

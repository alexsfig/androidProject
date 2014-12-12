package net.gr33nme3dia.alex.pokedex.data;

import android.provider.BaseColumns;

/**
 * Created by alex on 12/11/14.
 */
public class PokedexContract {
    public static final class PokemonEntry implements BaseColumns{
        public static final String TABLE_NAME = "pokemons";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_AVATAR = "avatar";
        public static final String COLUMN_NUMERO = "numero";
        public static final String COLUM_ALTURA = "altura";
        public static final String COLUM_PESO = "peso";
    }
    public static final class LugarEntry implements BaseColumns{
        public static final String TABLE_NAME = "lugares";
        public static final String COLUMN_POKEMON_ID = "pokemon_id";
        public static final String COLUMN_LATITUD = "latitud";
        public static final String COLUMN_LONGITUD = "longitud";
    }
}

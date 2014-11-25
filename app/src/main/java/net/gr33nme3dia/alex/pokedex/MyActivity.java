package net.gr33nme3dia.alex.pokedex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyActivity extends ActionBarActivity implements
        PokemonListFragment.Callbacks{
    private boolean mTwoPane;
    private Pokemon mCurrentPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if ( findViewById(R.id.pokemon_detail_container) != null){
            mTwoPane = true;
            ((PokemonListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.pokemon_list))
                    .setActivateOnItemClick(true);
        }
        if ( savedInstanceState == null && mTwoPane ){
            PokemonDetailFragment fragment = PokemonDetailFragment.newInstance(null);
            getSupportFragmentManager().beginTransaction().add(R.id.pokemon_detail_container, fragment).commit();
        }
    }
    @Override
    public void onItemSelected(Pokemon pokemon) {
        mCurrentPokemon = pokemon;
        if (mTwoPane){
            PokemonDetailFragment fragment = PokemonDetailFragment.newInstance(mCurrentPokemon);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.pokemon_detail_container, fragment).commit();
        }
        else {
            Intent detailIntent = new Intent(this, PokemonDetailActivity.class);
            detailIntent.putExtra("pokemon", pokemon);
            startActivity(detailIntent);
        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */

}

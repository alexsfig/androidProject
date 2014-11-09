package net.gr33nme3dia.alex.pokedex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alex on 10/31/14.
 */
public class PokemonDetailFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private String mNombre;

    public static PokemonDetailFragment newInstance(String mNombre) {
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, mNombre);
        fragment.setArguments(args);
        return fragment;
    }
    public PokemonDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNombre = getArguments().getString(ARG_NOMBRE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_detail,
                container, false);

        TextView nombre = (TextView)rootView.findViewById(R.id.textview_pokemon_nombre);
        nombre.setText(mNombre);

        return rootView;
    }


}
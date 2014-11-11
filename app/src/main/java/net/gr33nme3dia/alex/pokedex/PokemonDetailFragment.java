package net.gr33nme3dia.alex.pokedex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by alex on 10/31/14.
 */
public class PokemonDetailFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_AVATAR = "avatar";
    private String mnombre;
    private String mavatar;

    public static PokemonDetailFragment newInstance(Pokemon pokemon) {
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, pokemon.getNombre());
        args.putString(ARG_AVATAR, pokemon.getAvatar());
        fragment.setArguments(args);
        return fragment;
    }
    public PokemonDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mnombre = getArguments().getString(ARG_NOMBRE);
            mavatar = getArguments().getString(ARG_AVATAR);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_detail,
                container, false);

        TextView nombre = (TextView)rootView.findViewById(R.id.textview_pokemon_nombre);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView3);
        if (TextUtils.isEmpty(mavatar) || mavatar == null)
            Picasso.with(getActivity()).load(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
        else
            Picasso.with(getActivity()).load(mavatar).error(R.drawable.ic_launcher).into(imageView);
        nombre.setText(mnombre+"..."+mavatar);

        return rootView;
    }


}
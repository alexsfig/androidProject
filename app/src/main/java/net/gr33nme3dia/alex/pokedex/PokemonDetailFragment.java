package net.gr33nme3dia.alex.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import java.util.Locale;

/**
 * Created by alex on 10/31/14.
 */
public class PokemonDetailFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_AVATAR = "avatar";
    private static final String ARG_POKEMON = "pokemon";
    private ShareActionProvider mShareActionProvider;
    private String mnombre;
    private String mavatar;
    private Pokemon mpokemon;
    private ImageView imageView;
    private Bitmap mBitmap;
    private String url;
    private Location loc;
    
    public static PokemonDetailFragment newInstance(Pokemon pokemon) {
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POKEMON, pokemon);
        fragment.setArguments(args);
        
        return fragment;
    }
    public PokemonDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mpokemon = getArguments().getParcelable(ARG_POKEMON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_pokemon_detail,
                container, false);

        TextView nombre = (TextView)rootView.findViewById(R.id.textview_pokemon_nombre);
        imageView = (ImageView)rootView.findViewById(R.id.imageView3);
        if ( mpokemon != null){
            mavatar = mpokemon.getAvatar();
            ImageRequest request = new ImageRequest(mavatar,
                    new Response.Listener<Bitmap>(){
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            mBitmap = bitmap;
                            imageView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            imageView.setImageResource(R.drawable.ic_launcher);
                        }
                    });
            PokedexApplication.getInstance().addToRequestQueue(request);
            nombre.setText(mpokemon.getNombre());
        }
        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pokemon_detail, menu);

        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(item);
        mShareActionProvider.setShareIntent(getDefaultShareIntent());
        super.onCreateOptionsMenu(menu,inflater);
    }

    private Intent getDefaultShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (mpokemon != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, "¿Quien ese ese pokemon?");
            intent.putExtra(Intent.EXTRA_TEXT, mpokemon.getNombre());
        }
        return intent;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(getActivity());
            return true;
        }
        else if(id == R.id.action_view){
            if(mpokemon!=null && mpokemon.getAvatar()!=null && mpokemon.getAvatar().length()>0){
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mpokemon.getAvatar()));
                startActivity(intent);
                return true;
            }
        }
        else if(id == R.id.action_gallery){
            if (mpokemon != null && mpokemon.getAvatar() != null && mpokemon.getAvatar().length() > 0) {
                url = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), mBitmap,mpokemon.getNombre(),"");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
//            File myDir=new File("/sdcard/Download");
//            FileOutputStream out = null;
//            String fname = mpokemon.getNombre() + ".jpg";
//            File file = new File (myDir, fname);
//            if (file.exists ()) file.delete ();
//            try {
//                out = new FileOutputStream(file);
//                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    if (out != null) {
//                        out.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            Intent intent = new Intent();
//            Uri uri = Uri.fromFile(file);
//
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setDataAndType(uri, "image/*");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            return true;

        else if (id == R.id.action_maps){
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 13.6929400, -89.2181910);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
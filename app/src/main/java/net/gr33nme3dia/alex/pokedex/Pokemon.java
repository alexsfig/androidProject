package net.gr33nme3dia.alex.pokedex;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 10/26/14.
 */
public class Pokemon implements Parcelable {
    private String nombre;
    private String avatar;
    private String altura;
    private String peso;

    public Pokemon(String charizard, String s, String v, String v1) {
        this.nombre = charizard;
        this.avatar = s;
        this.altura = v;
        this.peso = v1;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String imagen) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.avatar);
    }

    public Pokemon() {
    }

    private Pokemon(Parcel in) {
        this.nombre = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
}

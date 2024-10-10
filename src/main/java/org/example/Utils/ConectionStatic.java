package org.example.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConectionStatic {
    static URL urlDescripcion;
    static URL urlRango9en9;
    static URL urlDetallePokemon;
    {
        try {
            urlDescripcion = new URL("https://pokeapi.co/api/v2/pokemon-species/");
            urlRango9en9 = new URL("https://pokeapi.co/api/v2/pokemon?limit=9&offset=");
            urlDetallePokemon = new URL("https://pokeapi.co/api/v2/pokemon/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // CONEXION DE URL  Y OBTENCION DE DATOS RECIBR DATOS DE LA POKEAPI
    public StringBuilder conexionEnvioDatos(URL urlConexion) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) urlConexion.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int response = conn.getResponseCode();
        if (response != 200) {
            throw new IOException(" UN PROBLEMA EN LA RESPUESTA VERIFICAR URL  O CONEXION " + response);

        } else {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }

    }




    public URL getUrlDescripcion() {
        return urlDescripcion;
    }

    public void setUrlDescripcion(URL urlDescripcion) {
        this.urlDescripcion = urlDescripcion;
    }

    public URL getUrlRango9en9() {
        return urlRango9en9;
    }

    public void setUrlRango9en9(URL urlRango9en9) {
        this.urlRango9en9 = urlRango9en9;
    }

    public URL getUrlDetallePokemon() {
        return urlDetallePokemon;
    }

    public void setUrlDetallePokemon(URL urlDetallePokemon) {
        this.urlDetallePokemon = urlDetallePokemon;
    }
}

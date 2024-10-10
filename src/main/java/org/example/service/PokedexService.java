package org.example.service;

import org.example.Utils.ConectionStatic;
import org.example.form.PokedexFrom;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import static org.example.Utils.FontUtils.*;

public class PokedexService {
PokedexFrom PokedexFrom;
    static  String spriteURL1;
    static int textoVida = 0;
    static int ataque = 0;
   static  int defensa = 0;
   static int velocidad = 0;
    ConectionStatic conectionStatic = new ConectionStatic();


    private int pokemonId=0;


    public void mostrarPokemon(int pokemonId, ArrayList<JPanel> panelesContenido) throws IOException {

        URL url = new URL( conectionStatic.getUrlRango9en9().toString() + pokemonId);
            JSONObject jsonObject = new JSONObject(conectionStatic.conexionEnvioDatos(url).toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                String nombre = jsonArray.getJSONObject(i).getString("name");

                nombre = nombre.toUpperCase().charAt(0) + nombre.substring(1 , nombre.length()).toLowerCase();//VOLVER PRIMERA LETRA MAYUSCULA

               // OBTENEMOS NOMBRE DEL POKEMON EJ. 1. Bulbasur
                int pokemonSeleccionadoId = pokemonId + (i + 1);
                JLabel titulo = new JLabel((i + pokemonId + 1) + ". " + nombre);
                JLabel imagenPokemon = new JLabel();
                titulo.setForeground(Color.white);
                titulo.setFont(font);
                JPanel panel = panelesContenido.get(i);

                panel.removeAll();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                panel.add(titulo);
                panel.add(Box.createVerticalGlue());



                // Añadir espaciadores para centrar el JLabel

                buscarPokemon(pokemonSeleccionadoId, imagenPokemon, panel); // buscar pokemon

                panel.add(imagenPokemon);  //agregar imagen al panel
                //  panel.setFont(Font.getFont(line));

                panel.setName(nombre);
                panel.revalidate();
                panel.repaint();


// ACCION DE MOSTRAR EL JpanelContenido
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String pokemonSeleccionado = panel.getName();

                        // Crear un nuevo JDialog no modal
                        JDialog dialog = new JDialog(PokedexFrom,false);
                        dialog.setResizable(false);
                        dialog.setBounds(50,40,300,400);
                        dialog.setLocationRelativeTo(null);


                        // Añadir una etiqueta en el centro
                        JLabel namePokemon = new JLabel(pokemonSeleccionado);
                        JLabel descripcion =new JLabel();
                        JLabel habilidades = new JLabel();


                        namePokemon.setFont(fuenteLetra);

                        // Crear el panel que contendrá la información del Pokémon
                        JPanel infoPanel = new JPanel();

                        infoPanel.setBackground(colorOscuro);
                        namePokemon.setForeground(Color.WHITE);

                        infoPanel.add(namePokemon);

                        JLabel imagenPokemon = new JLabel();  // Crear el JLabel para la imagen
                        infoPanel.add(imagenPokemon); // Añadir la imagen al panel

                        try {
                            // verificar a que paquete podrian ir
                            // Obtener detalles del Pokémon seleccionado
                           buscarPokemon(pokemonSeleccionadoId,imagenPokemon,panel);
                            URL pokemonURL1 = new URL(conectionStatic.getUrlDescripcion().toString()+pokemonSeleccionadoId +"/");  //descripcion del pokemon



                                ImageIcon imageIcon = new ImageIcon(new URL(spriteURL1));
                               ImageIcon resizedIcon = ajustarImagen(imageIcon, 210, 200);
                          imagenPokemon.setIcon(resizedIcon);


                                JSONObject jsonObject1 = new JSONObject(conectionStatic.conexionEnvioDatos(pokemonURL1).toString());
                                if(jsonObject1.has("flavor_text_entries")){

                                    JSONArray jsonArray1 = jsonObject1.getJSONArray("flavor_text_entries");
                                    String descripcion2="";

                                    for (int i = 0; i < jsonArray1.length(); i++) {
                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                        String lengueaje = jsonObject2.getJSONObject("language").getString("name");

                                     if (lengueaje.equals("es")) {
                                           descripcion2 = jsonObject2.getString("flavor_text");
                                           break;
                                       }
                                    }
                                    JTextArea jDescripcion  = new JTextArea(descripcion2);
jDescripcion.setPreferredSize(new Dimension(272,24));

                                    // Crear el JProgressBar
                                    JProgressBar jProgressBar0 = new JProgressBar();
                                    JProgressBar jProgressBar1 = new JProgressBar();
                                    JProgressBar jProgressBar2 = new JProgressBar();
                                    JProgressBar jProgressBar3 = new JProgressBar();

                                    // Crear un JPanel con OverlayLayout
                                    JPanel progressPanel = new JPanel();
                                    progressPanel.setLayout(new OverlayLayout(progressPanel));

                                    JPanel panelDefensa = new JPanel();
                                    panelDefensa.setLayout(new OverlayLayout(panelDefensa));

                                    JPanel panelAtaque = new JPanel();
                                    panelAtaque.setLayout(new OverlayLayout(panelAtaque));


                                    JPanel panelVelocidad = new JPanel();
                                    panelVelocidad.setLayout(new OverlayLayout(panelVelocidad));



// Crear el JLabel que mostrará el texto
buscarDatosPokemon(pokemonSeleccionadoId);

JLabel progressLabel = new JLabel(String.valueOf("HP " + textoVida+"%")); // textoVida primero entra al metodoBuscarDatosPokemon y despues se llena la variable estatica
                         JLabel Jdefensa = new JLabel("Defensa "+String.valueOf(defensa)+"%");
                                    JLabel JAtaque = new JLabel("Ataque "+String.valueOf(ataque)+"%");
                                    JLabel JVelocidad = new JLabel("Velocidad " +String.valueOf(velocidad)+"%");

                                    jProgressBar0.setValue(textoVida);
                                    jProgressBar1.setValue(defensa);
                                    jProgressBar2.setValue(ataque);
                                    jProgressBar3.setValue(velocidad);

                                    jProgressBar0.setForeground(Color.green);  // color del progressbar
                                    jProgressBar1.setForeground(Color.blue);  // color del progressbar
                                    jProgressBar2.setForeground(Color.ORANGE);  // color del progressbar
                                    jProgressBar3.setForeground(Color.gray);  // color del progressbar



                                    progressLabel.setAlignmentX(0.5f); // Centramos el texto horizontalmente
                                    progressLabel.setAlignmentY(0.5f); // Centramos el texto verticalmente


                                    Jdefensa.setAlignmentX(0.5f);
                                    Jdefensa.setAlignmentY(0.5f);

                                    JAtaque.setAlignmentX(0.5f);
                                    JAtaque.setAlignmentY(0.5f);

                                    JVelocidad.setAlignmentX(0.5f);
                                    JVelocidad.setAlignmentY(0.5f);


                                    progressLabel.setForeground(Color.black); // Color del texto
                                    Jdefensa.setForeground(Color.black);
                                    JAtaque.setForeground(Color.black);
                                    JVelocidad.setForeground(Color.black);

// Añadir ambos componentes al panel
                                    progressPanel.add(progressLabel);
                                    progressPanel.add(jProgressBar0);

                                    panelDefensa.add(Jdefensa);
                                    panelDefensa.add(jProgressBar1);

                                    panelAtaque.add(JAtaque);
                                    panelAtaque.add(jProgressBar2);

                                    panelVelocidad.add(JVelocidad);
                                    panelVelocidad.add(jProgressBar3);



jDescripcion.setFont(fuenteDescripcion);
jDescripcion.setWrapStyleWord(true);
jDescripcion.setEnabled(false);
jDescripcion.setBackground(colorOscuro);
jDescripcion.setForeground(Color.white);




// AÑADIENDO COMPONENTES AL PANEL
                                    infoPanel.add(jDescripcion);
                                    infoPanel.add(progressPanel);
                                    infoPanel.add(panelDefensa);
                                    infoPanel.add(panelAtaque);
                                    infoPanel.add(panelVelocidad);



                                }



                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        dialog.add(infoPanel);

                        // Añadir un WindowFocusListener para cerrar el diálogo si se hace clic fuera
                        dialog.addWindowFocusListener(new WindowFocusListener() {
                            @Override
                            public void windowGainedFocus(WindowEvent e) {
                                // No hace nada cuando gana el foco
                            }

                            @Override
                            public void windowLostFocus(WindowEvent e) {
                                dialog.dispose(); // Cierra el diálogo cuando pierde el foco (clic fuera)
                            }
                        });

                        dialog.setVisible(true); // Muestra el diálogo
                    }
                });

            }

        }


    public void buscarPokemon(int i, JLabel ImagenPokemon, JPanel panel) throws IOException {

        URL url = new URL(conectionStatic.getUrlDetallePokemon().toString() + i + "/");

        // aqui podemos obtener mas cosas como nombre, habilidades.. todo los detales del pokemon
        JSONObject jsonObject = new JSONObject(conectionStatic.conexionEnvioDatos(url).toString());
        JSONArray jsonArray = jsonObject.getJSONArray("types");

        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(j).getJSONObject("type");

            String typeNombre = jsonObject1.getString("name");

            JLabel typePokemon = new JLabel(typeNombre);

            typePokemon.setForeground(Color.WHITE);
            typePokemon.setOpaque(true);

            // Obtener el color basado en el tipo
            Color colorFondo = obtenerColorPorTipo(typeNombre);
            typePokemon.setOpaque(true); // Necesario para que se vea el color de fondo
            typePokemon.setBackground(colorFondo); //
            panel.add(Box.createRigidArea(new Dimension(5, 5)));
            typePokemon.setFont(font1);

            panel.add(typePokemon);


        }

            //BUSCAR IMAGEN sprites / front_default paneles principales

            spriteURL1 = jsonObject.getJSONObject("sprites").getString("front_default");
            mostrarPokemon(spriteURL1, ImagenPokemon);

            panel.revalidate();
            panel.repaint();

        }


//MOSTRAR POKEMON POR ID PARA EXTRAER DATOS
    public void buscarDatosPokemon(int pokemonid) throws IOException {
        URL  url = new URL(   "https://pokeapi.co/api/v2/pokemon/"+ pokemonid + "/");

        JSONObject jsonObject = new JSONObject(conectionStatic.conexionEnvioDatos(url).toString());
JSONArray jsonArray = jsonObject.getJSONArray("stats");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject statObject = jsonArray.getJSONObject(i);
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            int vidaTotal = statObject.getInt("base_stat");

            JSONObject statInfo = statObject.getJSONObject("stat");
            String nombreStat = statInfo.getString("name");  // Nombre de la estadística
            switch (nombreStat) {
                case "hp":
                    textoVida = vidaTotal;  // Asignar a la variable correcta solo para "hp"
                    break;
                case "defense":
                    defensa = vidaTotal;
                    break;
                case "speed":
                    velocidad = vidaTotal;
                    break;
                case "attack":
                    ataque = vidaTotal;
                    break;
            }


        }



    }


    // MOSTRAR IMAGEN POKEMON
    public void mostrarPokemon(String spriteURL, JLabel imagenPokemon) throws IOException {
        URL url = new URL(spriteURL);
        ImageIcon imageIcon = new ImageIcon(url);

        // Ajustar la imagen y luego establecerla en el JLabel
        ImageIcon resizedIcon = ajustarImagen(imageIcon, 120, 120);
        imagenPokemon.setIcon(resizedIcon);
    }




}

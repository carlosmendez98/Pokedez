package org.example.form;

import org.example.service.PokedexService;


import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PokedexFrom  extends  JFrame {
    private JPanel panel1;
    private JButton btnClose;
    private JButton btmMinimizar;
    private JPanel JPanelmenu;
    private JPanel JPanelContenido1;
    private JPanel JPanelContenido2;
    private JPanel JPanelContenido3;
    private JPanel JPanelContenido4;
    private JPanel JPanelContenido5;
    private JPanel JPanelContenido6;
    private JPanel JPanelContenido7;
    private JPanel JPanelContenido8;
    private JPanel JPanelContenido9;
    private JButton btnPaginaAnterior;
    private JButton btnPaginaSiguiente;

    private ArrayList<JPanel> panelesContenido;
    JLabel typePokemon;

    private int pokemonId = 0;

    public PokedexFrom() throws IOException {

        PokedexService pokedexService = new PokedexService();


        // BOTON ANTERIOR
        btnPaginaAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pokemonId >= 9) {
                    pokemonId -= 9;
                    try {
                        pokedexService.mostrarPokemon(pokemonId,panelesContenido);
                        // mostrarPokemon(); // metodo mostrarpokemon()
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }



                }
            }
        });

        // BOTON SIGUIENTE
        btnPaginaSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokemonId += 9;
                try {
                    pokedexService.mostrarPokemon(pokemonId,panelesContenido);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        // BOTON SALIR
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        //BOTON MINIMIZAR
        btmMinimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

        setSize(610, 610);
        setContentPane(panel1);
        panelesContenido = new ArrayList<>();
        panelesContenido.add(JPanelContenido1);
        panelesContenido.add(JPanelContenido2);
        panelesContenido.add(JPanelContenido3);
        panelesContenido.add(JPanelContenido4);
        panelesContenido.add(JPanelContenido5);
        panelesContenido.add(JPanelContenido6);
        panelesContenido.add(JPanelContenido7);
        panelesContenido.add(JPanelContenido8);
        panelesContenido.add(JPanelContenido9);

        pokedexService.mostrarPokemon(pokemonId,panelesContenido);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }







}






















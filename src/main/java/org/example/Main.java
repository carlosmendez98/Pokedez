package org.example;

import org.example.form.PokedexFrom;

import javax.swing.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                PokedexFrom pokedexForm = new PokedexFrom();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });


    }
}
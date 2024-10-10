package org.example.Utils;

import javax.swing.*;
import java.awt.*;

public class FontUtils {
    public static final Font font = new Font("Arial", Font.BOLD, 25); // para el titulo
    public static final Font fuenteLetra = new Font("Arial", Font.BOLD, 19);
    public static final Font fuenteDescripcion = new Font("Arial",Font.ITALIC,11);
    public static final Font font1 = new Font("Arial", Font.BOLD, 12);
    public static final Color colorOscuro = new Color(39, 39, 57);




    // METODO AJUSTAR IMAGEN:


    public static ImageIcon ajustarImagen(ImageIcon originalIcon, int width, int height) {

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // CONDICION PARA COLOR DE LOS TIPOS
     public static  Color obtenerColorPorTipo(String tipo) {
        switch (tipo) {
            case "grass":
                return new Color(75, 200, 75); // Verde
            case "fire":
                return new Color(255, 100, 0); // Rojo
            case "water":
                return new Color(0, 100, 255); // Azul
            case "poison":
                return new Color(150, 0, 150); // Morado
            case "electric":
                return new Color(151, 161, 3, 255); // Amarillo
            case "bug":
                return Color.green.darker();  // Verde claro
            // Agrega más tipos según sea necesario
            default:
                return Color.pink.darker(); // Color por defecto
        }
    }
}

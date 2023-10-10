/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegoacertijos2;

/**
 *
 * @author FLIA ARENAS CARMONA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JuegoAcertijos2 extends JFrame {
    private int pantallaActual = 1;
    private JPanel panelPantalla1, panelPantalla2;
    private List<JButton> botonesMovibles;

    public JuegoAcertijos2() {
        setTitle("Juego de Acertijos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new CardLayout());

        botonesMovibles = new ArrayList<>();

        panelPantalla1 = crearPantalla1();
        panelPantalla2 = crearPantalla2();

        add(panelPantalla1, "pantalla1");
        add(panelPantalla2, "pantalla2");

        mostrarPantalla1();
    }

    private JPanel crearPantalla1() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Selecciona el color correcto: Rojo o Azul");
        panel.add(etiqueta, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(9, 10));

        // Lista de colores sin repetir
        List<Color> coloresSinRepetir = obtenerColoresSinRepetir();

        for (Color color : coloresSinRepetir) {
            JButton boton = new JButton();
            boton.setBackground(color);
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (color.equals(Color.RED)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido a la realidad");
                        mostrarPantalla2();
                    } else {
                        JOptionPane.showMessageDialog(null, "No te quedes, sal de la simulación");
                    }
                }
            });
            panelBotones.add(boton);
        }

        panel.add(panelBotones, BorderLayout.CENTER);

        return panel;
    }

    private List<Color> obtenerColoresSinRepetir() {
        List<Color> colores = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 90; i++) {
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            if (!colores.contains(color)) {
                colores.add(color);
            }
        }

        // Añadir el color Rojo y Azul
        colores.add(Color.RED);
        colores.add(Color.BLUE);

        // Barajar los colores para que no estén en un orden específico
        Collections.shuffle(colores);

        return colores;
    }

    private JPanel crearPantalla2() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("¡Has avanzado a la siguiente pantalla!");
        panel.add(etiqueta, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            JButton boton = new JButton();
            boton.setBackground(obtenerColorAleatorio());
            boton.setPreferredSize(new Dimension(80, 80)); // Tamaño de los botones
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    moverBoton(boton);
                }
            });
            panelBotones.add(boton);
            botonesMovibles.add(boton);
        }

        panel.add(panelBotones, BorderLayout.CENTER);

        JButton botonSiguiente = new JButton("pista");
        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "la perseverancia en los 3 primarios");
            }
        });

        panel.add(botonSiguiente, BorderLayout.SOUTH);

        return panel;
    }

    private void moverBoton(JButton boton) {
        int deltaX = 10;
        int deltaY = 10;
        Point ubicacionActual = boton.getLocation();
        boton.setLocation((int) ubicacionActual.getX() + deltaX, (int) ubicacionActual.getY() + deltaY);
    }

    private Color obtenerColorAleatorio() {
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = 0.6f + random.nextFloat() * 0.4f; // Colores más saturados
        float brightness = 0.6f + random.nextFloat() * 0.4f; // Colores más brillantes
        return Color.getHSBColor(hue, saturation, brightness);
    }

    private void mostrarPantalla1() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "pantalla1");
        pantallaActual = 1;
    }

    private void mostrarPantalla2() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "pantalla2");
        pantallaActual = 2;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JuegoAcertijos2 juego = new JuegoAcertijos2();
                juego.setVisible(true);
            }
        });
    }
}
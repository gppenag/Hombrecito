/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 */
public class NewPanel extends JPanel implements ActionListener, MouseListener {

    //private final int initial_X=
    int x = 0;
    int k = 150;
    int y = 20;
    private Timer timer;
    private int Delay = 20;
    private final Color color;
    private int secuenciaD = 0;
    private int secuenciaI = 2;
    int variable = 1;

    public NewPanel() throws Exception {
        initBoard();
        this.addMouseListener(this);
        timer = new Timer(Delay, this);
        this.color = Color.ANTIQUEWHITE;
        timer.start();

    }

    private void initBoard() throws Exception {
        addKeyListener((KeyListener) new TAdapter());
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Puntos", 600, 30);
        g.drawString(": xxx", 650, 30);

        Image fondo = cargarImagen("fondo.png");
        g.drawImage(fondo, 0, 0, null);

        if(variable == 1){
        Image menDer = cargarImagen("free_radical_game_sprites.png");
        g.drawImage(menDer, x, y+380, x + 45, y+(380 + 45), 192 + (this.secuenciaD * 32), 192, 192 + (this.secuenciaD * 32) + 32, 192 + 32, this);
        }
        
        if(variable == -1){
        Image menIzq = cargarImagen("free_radical_game_sprites.png");
        g.drawImage(menIzq, x , y+380, x + 45, y+(380 + 45), 256 - (this.secuenciaI * 32), 160, 256 - (this.secuenciaI * 32) - 32, 160 + 32, this);
        }
        
       
        /*
        Image gato = cargarImagen("cats.gif");
        //10 parametros imagen a donde se va a dibujar los siguientes 5 del archivo 
        g.drawImage(gato, x, 370, x + 132, 320 + 80, (this.secuencia * 132), 0, (this.secuencia * 132) + 132, 80, this);

        Image moneda = cargarImagen("FullCoins.png");
        g.drawImage(moneda, x + 20, 150, x + 23, 150 + 18, (this.secuencia * (127 / 8)), 0, (this.secuencia * (127 / 8)) + (127 / 8), 16, this);
        */
        /*
        g.drawString("01", x + 25, 340);
        g.setColor(java.awt.Color.GRAY);
        g.fillOval(x + 30, 380, 30, 30);
        g.fillOval(x + 100, 380, 30, 30);
        g.drawRect(x + 20, 320, 120, 60);
        g.drawRect(x + 110, 335, 60, 10);
        g.drawOval((x + k) + y + 90, 80, 30, 30);
        g.drawRect((x + k), y + 60, 15, 30);
        g.drawOval(400, 351, 56, 56);
        */
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("soltar");
        }

        public void keyPressed(KeyEvent e) {
            System.out.println("teclear");
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                System.out.println("VK_SPACE");
            }
            if (key == KeyEvent.VK_LEFT) {
                variable = -1;
            }
            if (key == KeyEvent.VK_RIGHT) {
                variable = 1;    
            }
            if (key == KeyEvent.VK_UP) {
                y = y-10;
            }
            if (key == KeyEvent.VK_DOWN) {
                y = y+10;
            }
            
        }
    }

    /*
    url = new URL("file:ball.wav");
    AudioClip ac = Applet.newAudioClip(url);
    ac.play;*/
    
    public void detectarcolisionnes() {
        Rectangle tanque = getBounds();
        Rectangle objeto = new Rectangle(400, 351, 56, 56);
        if (tanque.intersects(objeto)) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(variable == 1){
        x += 2;
        if (this.secuenciaD == 2) {
            this.secuenciaD = 0;
        } else {
            this.secuenciaD++;
        }

        // detectarcolisionnes();
        repaint();
    }else if(variable == -1){
        x -= 2;
        if (this.secuenciaI == 0) {
            this.secuenciaI = 2;
        } else {
            this.secuenciaI--;
        }
        repaint();
    }
    
    }

    @Override
    public Rectangle getBounds() {
        //Dimension del carro
        return new Rectangle(x + 20, 320, 120, 60);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mp = e.getPoint();
        if (getBounds().contains(mp)) {
            this.timer.stop();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Image cargarImagen(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}

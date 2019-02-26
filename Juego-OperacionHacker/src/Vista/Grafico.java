package Vista;

import Mundo.Jugador;
import Mundo.Obstaculo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;


public class Grafico extends Canvas{
    private int xTam, yTam;
    private BufferStrategy bs;
    private Graphics g;
    
    private Image fondo = null;
    //private EventosTeclado controles;
    public Grafico(int xTam, int yTam) {
        this.xTam = xTam;
        this.yTam = yTam;
        fondo = new ImageIcon(getClass().getResource("recursos/fondo.png")).getImage();
        this.setPreferredSize(new Dimension(this.xTam, this.yTam));
        this.setMaximumSize(new Dimension(this.xTam, this.yTam));
        this.setMinimumSize(new Dimension(this.xTam, this.yTam));
        this.setFocusable(true); 
    }   
    
    public void dibujar(Jugador jugador, Obstaculo[] obs){
        bs = this.getBufferStrategy();        
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        g.clearRect(0, 0, this.xTam, this.yTam);        
        g.drawImage(fondo, 0, 0, null);          
        dibujaObjeto(jugador.getPosX(), jugador.getPosY(), jugador.getImagen());        
        for(int i = 0; i < obs.length; i++){
            dibujaObjeto(obs[i].getPosX(),obs[i].getPosY(),obs[i].getImagen());  
        } 
        g.setColor(Color.BLACK);
        g.setFont( new Font( "Tahoma", Font.BOLD, 20));
        g.drawString(jugador.getNombre(), 10, 33);
        
        g.setColor(Color.BLACK);
        g.setFont( new Font( "Tahoma", Font.BOLD, 18));
        g.drawString("Vidas:" + jugador.getVidas(), 680, 33);
        
        g.dispose();
        bs.show();
       
    }
    
    public void dibujaObjeto(int posX, int posY, Image objeto){
        g.drawImage(objeto, posX, posY, null);
    }

    
    public void dibujarInfoPantalla(String ruta){
        bs = this.getBufferStrategy();        
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        Image img = new ImageIcon(getClass().getResource(ruta)).getImage();
        g.clearRect(0, 0, this.xTam, this.yTam);        
        g.drawImage(img, 0, 0, null);  
        g.dispose();
        bs.show();
       
    }
}

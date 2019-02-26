package Mundo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Jugador {
    private String nombre;    
    private int vidas = 2;
    private int posX, posY;
    private Image imagen;
    public Jugador(String nombre) {
        this.nombre = nombre;
        imagen = new ImageIcon(getClass().getResource("../Vista/recursos/personaje_izq.png")).getImage();
        this.posX = 752;
        this.posY = 233;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    
}

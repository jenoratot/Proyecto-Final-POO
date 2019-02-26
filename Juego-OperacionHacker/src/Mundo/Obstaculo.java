package Mundo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstaculo {
    private String nombreObstaculo;
    private String pregunta;
    private String[] respuestasPosibles;
    private int respuestaCorrecta;
    private int posX, posY;
    private Image imagen;
    private int tamX, tamY;
    private boolean resuelto;

    public Obstaculo(String nombreObstaculo, String pregunta, String[] respuestasPosibles, int respuestaCorrecta, int posX, int posY, String img,int tamX,int tamY) {
        this.nombreObstaculo = nombreObstaculo;
        this.pregunta = pregunta;
        this.respuestasPosibles = respuestasPosibles;
        this.respuestaCorrecta = respuestaCorrecta;
        this.posX = posX;
        this.posY = posY;
        this.imagen = new ImageIcon(getClass().getResource(img)).getImage();
        this.tamX = tamX;
        this.tamY = tamY;
        this.resuelto = false;
    }

    public String getNombreObstaculo() {
        return nombreObstaculo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getRespuestasPosibles() {
        return respuestasPosibles;
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

    public int getTamX() {
        return tamX;
    }

    public void setTamX(int tamX) {
        this.tamX = tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public void setTamY(int tamY) {
        this.tamY = tamY;
    }

    public boolean estaResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }
    
    
    
    public boolean esCorrecta(int opcion){
            if (opcion == this.respuestaCorrecta)
                return true;
            else
                return false;
    }
}


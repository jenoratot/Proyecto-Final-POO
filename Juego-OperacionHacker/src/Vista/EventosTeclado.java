package Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EventosTeclado implements KeyListener{
    private boolean[] keys = new boolean[256];
    public static boolean ARRIBA, ABAJO, DERECHA, IZQUIERDA;
    public EventosTeclado() {
        ARRIBA = false;
        ABAJO = false;
        DERECHA = false;
        IZQUIERDA = false;
    }   
    
    public void actualizar(){
        ARRIBA = keys[KeyEvent.VK_UP];
        ABAJO = keys[KeyEvent.VK_DOWN];
        DERECHA = keys[KeyEvent.VK_RIGHT];
        IZQUIERDA = keys[KeyEvent.VK_LEFT];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        //System.out.print("Tecla:"+e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    } 
    
    
}

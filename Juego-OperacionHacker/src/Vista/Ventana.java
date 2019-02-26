package Vista;

import Mundo.Jugador;
import Mundo.Obstaculo;
import java.awt.Color;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventana extends JFrame implements Runnable{
    public static final int XTAM=930;
    public static final int YTAM=506;
    private Grafico grafico;
    
    private Thread hilo;

    
    private boolean esIniciado;
    private boolean iniciando = true;
    
    boolean mov = true;
    
    private Jugador jugador;
    String ruta="recursos/personaje_izq.png";
    private EventosTeclado eventos;
    private Obstaculo[] obstaculos;
    public Ventana(Jugador jugador, Obstaculo[] obs){        
        this.jugador=jugador;
        this.obstaculos = obs;
        grafico = new Grafico(XTAM, YTAM);              
        this.add(grafico);
        eventos = new EventosTeclado();
        grafico.addKeyListener(eventos);
        //Datos de Ventana        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setTitle("Operacion Hacker");
	this.setSize(XTAM, YTAM);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public void run() {        
        while(esIniciado){ 
            if (this.iniciando){
                this.grafico.dibujarInfoPantalla("recursos/inicio.png");
                try {
                    sleep(2000);
                    this.grafico.dibujarInfoPantalla("recursos/inicio.png");
                    try {
                    this.grafico.dibujarInfoPantalla("recursos/instr.png");
                    sleep(2000);
                    this.grafico.dibujarInfoPantalla("recursos/instr.png");
                } catch (InterruptedException ex) {
                    System.out.print(ex);
                }
                } catch (InterruptedException ex) {
                    System.out.print(ex);
                }                
                try {
                    this.grafico.dibujarInfoPantalla("recursos/instr.png");
                    sleep(3000);
                    this.grafico.dibujarInfoPantalla("recursos/instr.png");
                } catch (InterruptedException ex) {
                    System.out.print(ex);
                }
                this.iniciando = false;
            }else{
                //Ciclo de juego     
                
                eventos.actualizar();
                actualizarMovimiento();  
                this.grafico.dibujar(this.jugador, this.obstaculos);                 
            }
        }
        pausar();
    }
    
    public void start(){
        hilo = new Thread(this);
        hilo.start();
        this.esIniciado = true;
    }
    
    private void pausar(){
        try {
            hilo.join();
            this.esIniciado = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actualizarMovimiento(){         
        for(int i = 0; i < this.obstaculos.length; i++){
            int distanciaX = this.jugador.getPosX()-this.obstaculos[i].getPosX();
            int distanciaY = this.jugador.getPosY()-this.obstaculos[i].getPosY();            
            if((distanciaX <= this.obstaculos[i].getTamX() && distanciaX >= 0)
                    && (distanciaY <= this.obstaculos[i].getTamY() && distanciaY >= 0)){
                mov = this.obstaculos[i].estaResuelto();
                if (!this.obstaculos[i].estaResuelto()){                      
                    new VentanaPregunta(this.obstaculos[i],i); 
                    if(!this.obstaculos[i].estaResuelto()){
                        this.jugador.setVidas(this.jugador.getVidas()-1);
                        if (this.jugador.getVidas()<=0){
                            JOptionPane.showMessageDialog(null, "Ha perdido", "Reiniciando", HEIGHT);  
                            this.iniciando = true;
                            mov = true;
                            this.jugador.setPosX(722);
                            this.jugador.setPosY(233);
                            this.jugador.setVidas(2);                            
                            for( int np = 0; np< this.obstaculos.length; np++){
                                this.obstaculos[np].setResuelto(false);
                            }
                        }
                    }                  
                }
            }
        }
        boolean gano = true;
        for( int j = 0; j < this.obstaculos.length; j++){
            if (this.obstaculos[j].estaResuelto() == false){
                gano = false;
            }
        }
        if (gano){
            JOptionPane.showMessageDialog(null, "Ha Ganado", "Reiniciando", HEIGHT);            
            this.iniciando = true;
            mov = true;
            this.jugador.setPosX(752);
            this.jugador.setPosY(233);
            this.jugador.setVidas(2);
            for( int n = 0; n< this.obstaculos.length; n++){
                this.obstaculos[n].setResuelto(false);
            }
        }
        
        //Muros
        boolean tocaMuroHorizontalArriba = this.tocaMuro(163, -1, 208, 
                this.jugador.getPosY(), this.jugador.getPosX())||
                this.tocaMuro(62, 204, 391, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(344, -1, 208, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(401, 204, 391, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(332, 404, 638, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(133, 404, 641, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(165, -1, 191, 
                        this.jugador.getPosX(), this.jugador.getPosY());
        
        
        boolean tocaMuroHorizontalAbajo = this.tocaMuro(119, -1, 189, 
                this.jugador.getPosY(), this.jugador.getPosX())||
                this.tocaMuro(26, 189, 383, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(98, 396, 656, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(301, -1, 189, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(373, 189, 383, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(295, 396, 656, 
                        this.jugador.getPosX(), this.jugador.getPosY())||
                this.tocaMuro(112, -1, 191, 
                        this.jugador.getPosX(), this.jugador.getPosY());
                        
        
        boolean tocaMuroVerticalIzquierda = this.tocaMuro(657, 114, 205, 
                this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(657, 277, 334, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(411, 277, 390, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(411, 48, 205, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(215, 48, 205, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(215, 277, 390, 
                        this.jugador.getPosY(), this.jugador.getPosX());
        
        boolean tocaMuroVerticalDerecha = this.tocaMuro(611, 114, 205, 
                this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(611, 277, 334, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(364, 277, 390, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(364, 48, 205, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(177, 48, 205, 
                        this.jugador.getPosY(), this.jugador.getPosX()) ||
                this.tocaMuro(177, 277, 390, 
                        this.jugador.getPosY(), this.jugador.getPosX());
              
        if(EventosTeclado.ARRIBA){
            if(this.jugador.getPosY()>=0 && !tocaMuroHorizontalArriba && mov){
                this.jugador.setPosY(this.jugador.getPosY()-1);
            }
            ruta="recursos/personaje_arrb.png";            
        } else if(EventosTeclado.ABAJO){
            if((this.jugador.getPosY()+75)<=this.YTAM && !tocaMuroHorizontalAbajo && mov)
                this.jugador.setPosY(this.jugador.getPosY()+1);
            ruta="recursos/personaje_abj.png";
        }else if(EventosTeclado.IZQUIERDA  && !tocaMuroVerticalIzquierda){
            if(this.jugador.getPosX()>=0 && mov){
                this.jugador.setPosX(this.jugador.getPosX()-1);  
                //System.out.print("\nIZQ");
            }
            ruta="recursos/personaje_izq.png";
        }else if(EventosTeclado.DERECHA && !tocaMuroVerticalDerecha){
            if((this.jugador.getPosX()+50)<=this.XTAM && mov)
                this.jugador.setPosX(this.jugador.getPosX()+1);
            ruta="recursos/personaje_der.png";
        }
        Image imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        this.jugador.setImagen(imagen);    
        System.out.println("J (x,y) :"+this.jugador.getPosX()+","+this.jugador.getPosY());        
    }        
    public boolean tocaMuro(int posFija, int posIni, int posFin, int posIniJug, int posFinJug){
        boolean tocaMuro = false;
        for(int i = posIni; i <= posFin; i++){
            if (i == posIniJug && posFija == posFinJug){
                tocaMuro = true;
            }
        }
        return tocaMuro;
    }
}

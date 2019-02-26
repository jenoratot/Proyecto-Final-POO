package Control;

import Mundo.Jugador;
import Mundo.Obstaculo;
import Vista.Ventana;

public class Launcher {

    public static void main(String[] args) {
        String preguntas[] = {        
        "Si quieres distribuir tu aplicación a diferentes plataformas"
                + " ¿cuántas versiones de Java necesitas crear?",
        " ¿NetBeans es un lenguaje de programación?",
        "¿Qué modificador de acceso indica explícitamente "
                + "que un método o variable de un objeto puede "
                + "ser accedido por código fuera de la clase "
                + "de ese objeto?",
        "Cuál es la salida del siguiente codigo:"
                + "\nclassA{\n\t"
                + "private void print(){"
                + "\n\t\tSystem.out.println(´´a´´);"
                + "\n\t}"
                + "\n\tprivate void print(String str){"
                + "\n\t\tSystem.out.println(´´b´´);"
                + "\n\t}"
                + "\n\tprivate void print(int x){"
                + "\n\t\tSystem.out.println(´´c´´);"
                + "\n\t}"
                + "\n\tpublic static void main(String[]args){"
                + "\n\t\tA object=new A();"
                + "\n\t\tobject.print(12);"
                + "\n\t}"
                + "\n}",
        "Una clase Car y su subclase BMW tienen cada uno un método "
                + "run(), el cual fue escrito por el desarrollador "
                + "como parte de la definición de la clase. Si CarObj "
                + "hace referencia a un objeto del tipo BMW,¿qué hará "
                + "CarObj.run();?"
        };
        String respuestas[][]={
            {"Sólo una version",
                "Una version para cada plataforma",
                "Dos versiones"},
            {"Verdadero","Falso"},
            {"public", "static", "default", "private"},
            {"a","b","c"},
            {"Invocará el método run() definido en Car",
                "El compilado se quejará de que run() "
                + "ha sido definido dos veces", 
                "Invocará el método run() definido en BMW"}
        };
        int respuestasCorrectas[]={0,1,0,2,1};
        String imgObstaculos[]={
            "../Vista/recursos/obstaculo.png",
            "../Vista/recursos/obstaculo.png",
            "../Vista/recursos/obstaculo.png",
            "../Vista/recursos/obstaculo.png",
            "../Vista/recursos/obstaculo.png"
        };
        
        
        Jugador jugador = new Jugador("Jugador");        
        Obstaculo[] obstaculos={
            new Obstaculo("Puerta",preguntas[0],respuestas[0],respuestasCorrectas[0],649,229,imgObstaculos[0],70,70),
            new Obstaculo("Rayos Laser",preguntas[1],respuestas[1],respuestasCorrectas[1],520,147,imgObstaculos[1],50,154),
            new Obstaculo("Camara",preguntas[2],respuestas[2],respuestasCorrectas[2],433,224,imgObstaculos[2],50,50),
            new Obstaculo("CyberPerro",preguntas[3],respuestas[3],respuestasCorrectas[3],276,224,imgObstaculos[3],50,50),
            new Obstaculo("Salvar el Mundo",preguntas[4],respuestas[4],respuestasCorrectas[4],48,224,imgObstaculos[4],50,50)
        };
        new Ventana(jugador, obstaculos).start();
    }
    
}

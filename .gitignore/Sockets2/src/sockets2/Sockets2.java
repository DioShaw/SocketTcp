/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author YisusMachine
 */
public class Sockets2 {

    public static int cont = 0;
    public static int num;
    public static int num2 = 10;
    public static boolean status = true;

    /**
     *
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            generate();
            ServerSocket miSocket = new ServerSocket(9107);
            System.out.println("Socket Starting......");
            while (true) {

                Socket socket = miSocket.accept();
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                //
                System.out.println("IP:  " + socket.getInetAddress());
                System.out.println("Puerto:  " + socket.getPort());
                String v = in.readUTF();
                //
                System.out.println("Mensaje:  " + v);
                int a = 0;
                a = Integer.parseInt(v);
                v = metodo(a);

                System.out.println(v + "resultado");
                System.out.println("*****-**********-***************");
                //MENSAJE SERVER
                salida.writeUTF(v);

                salida.writeBoolean(status);

            }

        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }

//genenero el numero aleatorio

    public static void generate() {
        num = (int) (Math.random() * 100 + 1);
        System.out.println("El numero a adivinar es:..." + num);
    }

//Doy las pista para el numero ademas es el contador de intentos

    public static String metodo(int a) {

        String msj = "";
        cont = cont + 1;
        num2 = num2 - 1;
        System.out.println("contador:" + cont);

        if (cont >= 10) {
            a=num;
        }
        if (a == num ) {
            msj = analizer();
        }
        if (a > num) {
            msj = "<html><body> ERROR, BAJALE UN POCO AL NUMERO......" + "<br> te quedan: " + num2 + " Intentos </body></html>";
        }
        if (a < num ) {
            msj = "<html><body> ERROR.!! ES MAS ARRIBA.........." + "<br> te quedan: " + num2 + " Itentos :-) </body></html>";
        }

        return msj;
    }

// se generan los mensajes segun los intentos 

    public static String analizer() {
        String msj9 = "";


        if (cont >= 8 && cont <= 10 ) {
            msj9 = "<html><body> Eres un ingenuo <br> <br> te he vencido,<br>  el número secreto es:  " + num + "</body></html>";
              }
        if (cont == 6 || cont == 7) {
            msj9 = "<html><body> Eres un Novato <br><br> has tardado mucho,<br><br> el número secreto adivinado es:" + num;
        }
        if (cont == 4 || cont == 5) {
            msj9 = "<html><body> Eres un Aprendiz, podrías haberlo descubierto en menor número de intentos, \n el número secreto adivinado es:" + num;
        }
        if (cont > 1 && cont <= 3) {
            msj9 = "<html><body> Eres un Magister, has gastado muy pocos intentos,\n el número secreto adivinado es:" + num;
        }
        if (cont == 1) {
            msj9 = "<html><body> Me has vencido en el primer intento, realmente eres un Maestro, \n el número secreto adivinado es: " + num;
        }

        System.out.println("msj9" + msj9);
        status = false;
        return msj9;
    }

}

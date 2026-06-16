package SocketServer;

import java.io.IOException;
import java.net.Socket;

public class Principal {
    public static void main(String[] args) throws IOException {
        Controlador c = new Controlador();
        c.criarServerSocket(5555);
        Socket s = c.esperaConexao();
        System.out.println("Cliente Conectado!");
        c.transferenciaDeDados(s);
        System.out.println("Cliente foi finalizado!");
    }
}

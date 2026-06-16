package SocketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    public ServerSocket ss;

    public void criarServerSocket(int porta) throws IOException {
        ss = new ServerSocket(porta);
    }

    public Socket esperaConexao() throws IOException {
        Socket socket = ss.accept();
        return socket;
    }

    public void transferenciaDeDados(Socket socket) throws IOException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // fluxo: Cliente envia msg -> Servidor recebe a msg e envia uma resposta ao
            // cliente ("Sua mensagem enviada foi:" msg)
            String msg = input.readUTF();
            System.out.println("Mensagem recebida do cliente" + msg);
            output.writeUTF("Sua mensagem recebida foi " + msg);
            output.flush();
            input.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problema no tratamento da conexao com cliente " + socket.getInetAddress());
        } finally {
            fechaSocket(socket);
        }
    }

    public void fechaSocket(Socket socket) throws IOException {
        socket.close();
        System.out.println("Cliente Finalizado");
    }
}

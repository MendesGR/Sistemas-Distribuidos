package SocketCliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Estabelecendo conexao");
        try {
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Conexao estabelecida");
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            String msg = JOptionPane.showInputDialog("Digite sua msg ao servidor: ");
            output.writeUTF(msg);
            output.flush();
            String msgRecebida = input.readUTF();
            JOptionPane.showMessageDialog(null, msgRecebida);
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro no cliente " + e);
        }
    }
}

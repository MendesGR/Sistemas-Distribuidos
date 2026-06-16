package ClienteServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Estabelecendo conexão!");

        try {
            Socket socket = new Socket("10.1.6.129", 5555);
            System.out.println("Conexão estabelecida!");

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String tamanhoString, numeroString;
            int numero, numerobusca;

            // Inserir o tamanho do vetor
            int tamanho = Integer.parseInt(tamanhoString = JOptionPane.showInputDialog("Digite o tamanho do vetor: "));
            output.writeInt(tamanho);
            output.flush();

            // Entrada de elementos no vetor
            for (int i = 0; i < tamanho; i++) {
                numero = Integer.parseInt(tamanhoString = JOptionPane.showInputDialog("Digite um número: "));
                output.writeInt(numero);
                output.flush();
            }

            // Entrada do numero para busca
            numerobusca = Integer.parseInt(
                    numeroString = JOptionPane.showInputDialog("Digite o número que deseja buscar em seu vetor: "));
            output.writeInt(numerobusca);
            output.flush();

            int resul = input.readInt();
            System.out.println(resul);
            if (resul >= 0) {
                JOptionPane.showMessageDialog(null, "O número está no indice: " + resul);
            } else {
                JOptionPane.showMessageDialog(null, "O número informado não foi encontrado!");
            }

            input.close();
            output.close();
            socket.close();
        }

        catch (Exception e) {
            System.out.println("Erro no cliente: " + e);
        }
    }
}
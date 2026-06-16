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
            String nome = input.readUTF();
            System.out.println("Nome do cliente e " + nome);
            output.writeUTF("Seu nome e " + nome + "!");
            output.flush();

            String idade = input.readUTF();
            System.out.println("Idade do cliente e " + idade);
            output.writeUTF("Sua idade e " + idade + " anos");
            output.flush();

            String confIdade = input.readUTF();
            System.out.println("Confirmando se o cliente possui titulo de eleitor");
            if (confIdade.equals("Sim")) {
                output.writeUTF("Voce pode votar!");
            } else {
                output.writeUTF("Infelizmente voce nao pode votar!");
                socket.close();
            }
            output.flush();

            String voto = input.readUTF();
            switch (voto) {
                case "1":
                    output.writeUTF("Voce votou no Alexandre!");
                    break;

                case "2":
                    output.writeUTF("Voce votou no Jose!");
                    break;

                case "3":
                    output.writeUTF("Voce votou no Gabriel!");
                    break;

                case "4":
                    output.writeUTF("Voce votou na Amanda!");
                    break;

                case "5":
                    output.writeUTF("Voce votou na Kelly!");
                    break;

                default:
                    break;
            }
            output.flush();

            String confVoto = input.readUTF();
            System.out.println("Confirmando o voto do cliente");
            switch (confVoto) {
                case "1":
                    output.writeUTF("Voto confirmado! Voce votou no candidato Alexandre!");
                    break;

                case "2":
                    output.writeUTF("Voto confirmado! Voce votou no candidato Jose!");
                    break;

                case "3":
                    output.writeUTF("Voto confirmado! Voce votou no candidato Gabriel!");
                    break;

                case "4":
                    output.writeUTF("Voto confirmado! Voce votou na candidata Amanda!");
                    break;

                case "5":
                    output.writeUTF("Voto confirmado! Voce votou na candidata Kelly!");
                    break;

                default:
                    break;
            }
            output.flush();
            // String msg = input.readUTF();
            // System.out.println("Mensagem recebida do cliente" + msg);
            // output.writeUTF("Sua mensagem recebida foi " + msg);
            // output.flush();
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

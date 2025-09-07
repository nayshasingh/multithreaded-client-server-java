//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.function.Consumer;
//
//public class Server {
//
//    public Consumer<Socket>getConsumer(){
//        return (clientSocket)->{
//            try{
//                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(),true);
//                toClient.println("Hello from the server");
//
//            }catch(IOException ex){
//                ex.printStackTrace();
//            }
//        };
//    }
//    public static void main(String[] args){
//        int port = 8010;
//        Server server = new Server();
//        try{
//            ServerSocket serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(100000);
//            System.out.println("Server is listening on port "+port);
//            while(true){
//                Socket acceptedSocket = serverSocket.accept();
//                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
//                thread.start();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);

                long start = System.currentTimeMillis();
                System.out.println("Handling client " + clientSocket.getRemoteSocketAddress()
                        + " at " + start);

                // Simulate some processing (to visualize parallelism)
                Thread.sleep(2000);

                toClient.println("Hello from the server at " + start);

                long end = System.currentTimeMillis();
                System.out.println("Finished client " + clientSocket.getRemoteSocketAddress()
                        + " at " + end + " (duration: " + (end - start) + " ms)");

                toClient.close();
                clientSocket.close();

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


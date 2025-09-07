//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.InetAddress;
//import java.net.Socket;
//
//public class Client {
//    public Runnable getRunnable(){
//        return new Runnable(){
//            @Override
//            public void run(){
//                int port = 8010;
//                try {
//                    InetAddress address = InetAddress.getByName("localhost");
//                    Socket socket = new Socket(address, port);
//                    PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
//                    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    toServer.println("Hello from the client");
//                    String input = fromServer.readLine();
//                    System.out.println("Response from the server is " + input);
//                    socket.close();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        };
//    }
//    public static void main(String[] args){
//        Client client = new Client();
//        for(int i=0;i<100;i++){
//            try{
//                Thread thread = new Thread(client.getRunnable());
//                thread.start();
//                Thread.sleep(20);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable() {
        return () -> {
            int port = 8010;
            try {
                InetAddress address = InetAddress.getByName("localhost");
                Socket socket = new Socket(address, port);

                PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                long start = System.currentTimeMillis();
                toServer.println("Hello from the client at " + start);

                String input = fromServer.readLine();
                long end = System.currentTimeMillis();

                System.out.println("Response from server: " + input + " (client thread finished in "
                        + (end - start) + " ms)");

                toServer.close();
                fromServer.close();
                socket.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 20; i++) {   // try with 20 clients at once
            new Thread(client.getRunnable()).start();
        }
    }
}

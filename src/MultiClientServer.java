import java.io.*;
import java.net.*;

public class MultiClientServer {
    public static SimTime simulationtime = new SimTime();
    public static TrainstationManager trainstationManager = new TrainstationManager();
    public static void main(String[] args) {
        simulationtime.start();
        trainstationManager.generateStations();
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String receivedtext;
            while ((receivedtext = reader.readLine()) != null) {
                String result = processtext(receivedtext);
                writer.println(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String processtext(String text){
        String[] request = text.split(":");
        if(request.length==0) return "Error";
        switch(request[0]){
            case "POST": return post(request[1]);
            case "GET": return get(request[1]);
            default: return "";
        }
    }
    public String post(String request){
        String[] split = request.split("=");
        if(split.length == 0) return "";
        if(split[0].equals("USER")){
            return createUser(split[1]);
        }
        return "";
    }
    public String get(String request){
        if(request.equals("TIME")){
            System.out.println("request for getting Time: "+ MultiClientServer.simulationtime.getTime());
            return MultiClientServer.simulationtime.getTime();
        }
        return "";
    }
    public String createUser(String username){
        User newUser = new User(username);
        UserManager.addUser(newUser);
        System.out.println(UserManager.getUsers());
        return newUser.station.name;
    }
}

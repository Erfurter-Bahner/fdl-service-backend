import java.io.*;
import java.net.*;

public class MultiClientServer {
    public static SimTime simulationtime = new SimTime();
    public static TrainstationManager trainstationManager = new TrainstationManager();
    public static void main(String[] args) {
        simulationtime.start();
        trainstationManager.generateStations();
        BahnhofsLayoutManager.setLayout();
        trainstationManager.setLayouts();
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
    public String station;
    public User user;
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
            System.out.println("error first catch");
            MultiClientServer.trainstationManager.freeStation(station);
            UserManager.removeUser(user);
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("error second catch");
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
            return MultiClientServer.simulationtime.getTime();
        }
        //oben ohne details unten mit details
        String[] split = request.split(";");
        if(split.length==0) return "";
        if(split[0].equals("STATION")){ //GET:STATION:Name
            System.out.println("getting: "+split[1]);
            return getStation(split[1]);
        }
        return "";
    }
    public String createUser(String username){
        User newUser = new User(username);
        user = newUser;
        UserManager.addUser(newUser);
        System.out.println(UserManager.getUsers());
        station = newUser.station.name;
        return newUser.station.name;
    }
    public String getStation(String name){
        System.out.println(name);
        String output = "";
        for(Trainstation station: TrainstationManager.getStationlist()){
            if(station.name.equals(name)){
                //found station with same name
                // Name;Gleise;Art;ziel1,ziel2,ziel3
                output =  station.name+";"+station.gleise+";"+station.art+";";
                for(Trainstation destined: station.destinations){
                    output+=destined.name+",";
                }
                output+=";";
                // nach destinationhaltepunkten: ; 1,2,3,4..10:  1,3,2..0:  2,4,5,1..0:
                for(int[] row : station.layout.layout){
                    for(int cell : row){
                        output+=cell+",";
                    }
                    output+=":";
                }
                output+=";";
            }
        }
        System.out.println(output);
        return output;
    }
}

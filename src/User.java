public class User {
    String username;
    Trainstation station;
    public User(String username){
        this.username = username;
        //temp
        this.station = MultiClientServer.trainstationManager.getStation();
    }
    public String getUsername() {
        return username;
    }
}

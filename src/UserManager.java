import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users = new ArrayList<>();


    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static List<User> getUsers() {
        return users;
    }

}

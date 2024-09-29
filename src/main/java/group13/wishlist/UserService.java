package group13.wishlist;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    // Simulate a user repository (use a real database in production)
    final private Map<String, String> userStore = new HashMap<>();

    public boolean createUser(String username, String password) {
        if (userStore.containsKey(username)) {
            return false; // Username already exists
        }
        userStore.put(username, password); // TODO: change to store a hashed password
        return true;
    }

    public boolean validateUser(String username, String password) {
        return userStore.containsKey(username) && userStore.get(username).equals(password);
    }

}

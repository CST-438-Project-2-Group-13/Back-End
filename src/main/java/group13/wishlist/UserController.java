package group13.wishlist;

import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        boolean loginSuccess = userService.loginUser(username, password);
        if (loginSuccess) {
            return "Login successful!";
        } else {
            return "Login failed. Check your credentials.";
        }
    }

    @RequestMapping("/logout")
    public String logoutUser(@RequestParam String username) {
        boolean logoutSuccess = userService.logoutUser(username);
        return logoutSuccess ? "Logout successful!" : "Logout failed. No user logged in or username mismatch.";
    }


    @PostMapping("/newuser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Delete user
    @DeleteMapping("/users")
    public String deleteUser(@RequestParam String username, @RequestParam String confirm) {
        if ("yes".equalsIgnoreCase(confirm)) {
            boolean deleteSuccess = userService.deleteUser(username);
            return deleteSuccess ? "User deleted successfully." : "User deletion failed.";
        } else {
            return "User deletion not confirmed.";
        }
    }
}

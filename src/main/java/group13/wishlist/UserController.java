package group13.wishlist;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
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

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

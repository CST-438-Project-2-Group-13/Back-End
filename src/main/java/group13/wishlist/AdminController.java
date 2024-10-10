package group13.wishlist;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin login
    @PostMapping("/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password) {
        boolean loginSuccess = adminService.loginAdmin(username, password);
        return loginSuccess ? "Admin login successful!" : "Admin login failed. Check your credentials.";
    }

    // View all users (admin only)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Create new user
    @PutMapping("/users")
    public User createUser(@RequestParam String username, @RequestParam String password) {
        return adminService.createUser(username, password);
    }

    // Delete user
    @DeleteMapping("/users")
    public String deleteUser(@RequestParam String username, @RequestParam String confirm) {
        if ("yes".equalsIgnoreCase(confirm)) {
            boolean deleteSuccess = adminService.deleteUser(username);
            return deleteSuccess ? "User deleted successfully." : "User deletion failed.";
        } else {
            return "User deletion not confirmed.";
        }
    }

    // Update user information
    @PatchMapping("/users")
    public String updateUser(@RequestParam String username, @RequestParam String newPassword) {
        boolean updateSuccess = adminService.updateUser(username, newPassword);
        return updateSuccess ? "User updated successfully." : "User update failed.";
    }
}

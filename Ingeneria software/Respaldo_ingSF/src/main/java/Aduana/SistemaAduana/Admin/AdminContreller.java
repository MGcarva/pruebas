package Aduana.SistemaAduana.Admin;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminContreller {

    private final AdminServise adminServise;

    @PostMapping
    public void createAdmin(@RequestBody Admin admin) {
        adminServise.createAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminServise.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminServise.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
    }

    @PutMapping("/{id}")
    public void updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        adminServise.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Integer id) {
        adminServise.deleteAdmin(id);
    }
}

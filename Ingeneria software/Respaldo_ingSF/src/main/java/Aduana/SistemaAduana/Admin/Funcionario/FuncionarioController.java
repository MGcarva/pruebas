package Aduana.SistemaAduana.Admin.Funcionario;

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
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioServise funcionarioServise;

    @PostMapping
    public void createFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioServise.createFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioServise.getAllFuncionarios();
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable Integer id) {
        return funcionarioServise.getFuncionarioById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario no encontrado"));
    }

    @PutMapping("/{id}")
    public void updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        funcionarioServise.updateFuncionario(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable Integer id) {
        funcionarioServise.deleteFuncionario(id);
    }
}

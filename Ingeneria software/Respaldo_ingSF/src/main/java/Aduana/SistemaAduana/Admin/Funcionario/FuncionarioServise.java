package Aduana.SistemaAduana.Admin.Funcionario;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServise {

    private final FuncionarioRepository funcionarioRepo;

    public void createFuncionario(Funcionario funcionario) {
        funcionarioRepo.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepo.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Integer id) {
        return funcionarioRepo.findById(id);
    }

    public void updateFuncionario(Integer id, Funcionario funcionario) {
        
        if (funcionarioRepo.existsById(id)) {
            funcionario.setIdfuncionario(id); 
            funcionarioRepo.save(funcionario); // Guardamos el funcionario actualizado
        } else {
            throw new RuntimeException("Funcionario no encontrado para actualizar");
        }
    }

    public void deleteFuncionario(Integer id) {
        if (funcionarioRepo.existsById(id)) {
            funcionarioRepo.deleteById(id);
        } else {
            throw new RuntimeException("Funcionario no encontrado para eliminar");
        }
    }
}

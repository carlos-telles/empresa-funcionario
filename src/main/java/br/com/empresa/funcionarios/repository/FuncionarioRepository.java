package br.com.empresa.funcionarios.repository;

import br.com.empresa.funcionarios.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByDocumento(String documento);

    List<Funcionario> findByDepartamentoId(Long id);
}

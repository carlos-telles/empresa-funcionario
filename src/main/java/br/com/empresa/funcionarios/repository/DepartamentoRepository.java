package br.com.empresa.funcionarios.repository;

import br.com.empresa.funcionarios.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Departamento findByNomeAndId(String nome, Long id);
}

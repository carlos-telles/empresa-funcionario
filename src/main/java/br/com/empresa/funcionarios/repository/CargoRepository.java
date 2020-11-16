package br.com.empresa.funcionarios.repository;

import br.com.empresa.funcionarios.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findByNome(String nome);
}
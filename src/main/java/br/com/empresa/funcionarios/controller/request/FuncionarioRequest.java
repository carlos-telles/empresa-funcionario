package br.com.empresa.funcionarios.controller.request;

import br.com.empresa.funcionarios.model.Cargo;
import br.com.empresa.funcionarios.model.Departamento;
import br.com.empresa.funcionarios.model.Funcionario;
import br.com.empresa.funcionarios.repository.CargoRepository;
import br.com.empresa.funcionarios.repository.DepartamentoRepository;
import br.com.empresa.funcionarios.repository.FuncionarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;

public class FuncionarioRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @Min(value = 18)
    @Max(value = 100)
    private int idade;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])",
            message = "must match yyyy-MM-dd")
    private String aniversario;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String documento;
    @NotNull
    private Long idCargo;
    @NotNull
    private Long idDepartamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Funcionario toEntity(CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
        Cargo cargo = getCargo(cargoRepository);
        Departamento departamento = getDepartamento(departamentoRepository);
        return new Funcionario(nome, idade, LocalDate.parse(aniversario), documento, cargo, departamento);
    }

    public Funcionario atualizar(Long id, FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, DepartamentoRepository departamentoRepository) {
        Cargo cargo = getCargo(cargoRepository);
        Departamento departamento = getDepartamento(departamentoRepository);
        Funcionario funcionario = funcionarioRepository.getOne(id);
        funcionario.setNome(nome);
        funcionario.setIdade(idade);
        funcionario.setAniversario(LocalDate.parse(aniversario));
        funcionario.setDocumento(documento);
        funcionario.setCargo(cargo);
        funcionario.setDepartamento(departamento);

        return funcionario;
    }

    private Departamento getDepartamento(DepartamentoRepository departamentoRepository) {
        Optional<Departamento> departamento = departamentoRepository.findById(idDepartamento);
        if (!departamento.isPresent()) {
            throw new IllegalArgumentException();
        }
        return departamento.get();
    }

    private Cargo getCargo(CargoRepository cargoRepository) {
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        if (!cargo.isPresent()) {
            throw new IllegalArgumentException();
        }
        return cargo.get();
    }
}

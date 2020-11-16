package br.com.empresa.funcionarios.controller.dto;

import br.com.empresa.funcionarios.model.Funcionario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioDto {
    private Long id;
    private String nome;
    private int idade;
    private LocalDate aniversario;
    private String documento;
    private CargoDto cargo;
    private DepartamentoDto departamentoAtual;
    private List<DepartamentoDto> historicoDeDepartamentos;

    public FuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.idade = funcionario.getIdade();
        this.aniversario = funcionario.getAniversario();
        this.documento = funcionario.getDocumento();
        this.cargo = new CargoDto(funcionario.getCargo());
        this.departamentoAtual = new DepartamentoDto(funcionario.getDepartamento());
        this.historicoDeDepartamentos = funcionario.getHistoricoDeDepartamentos().stream().map(DepartamentoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getAniversario() {
        return aniversario;
    }

    public void setAniversario(LocalDate aniversario) {
        this.aniversario = aniversario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public CargoDto getCargo() {
        return cargo;
    }

    public void setCargo(CargoDto cargo) {
        this.cargo = cargo;
    }

    public List<DepartamentoDto> getHistoricoDeDepartamentos() {
        return historicoDeDepartamentos;
    }

    public void setHistoricoDeDepartamentos(List<DepartamentoDto> historicoDeDepartamentos) {
        this.historicoDeDepartamentos = historicoDeDepartamentos;
    }

    public DepartamentoDto getDepartamentoAtual() {
        return departamentoAtual;
    }

    public void setDepartamentoAtual(DepartamentoDto departamentoAtual) {
        this.departamentoAtual = departamentoAtual;
    }

    public static List<FuncionarioDto> toDto(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(FuncionarioDto::new).collect(Collectors.toList());
    }
}

package br.com.empresa.funcionarios.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;
    @Column(name = "funcionario_name")
    private String nome;
    @Column(name = "funcionario_age")
    private int idade;
    @Column(name = "funcionario_birthday")
    private LocalDate aniversario;
    @Column(name = "funcionario_document")
    private String documento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "funcionario_departamento", joinColumns = { @JoinColumn(name = "funcionario_id")},
            inverseJoinColumns = { @JoinColumn(name = "departamento_id")})
    private List<Departamento> historicoDeDepartamentos = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(String nome, int idade, LocalDate aniversario, String documento, Cargo cargo, Departamento departamento) {
        this.nome = nome;
        this.idade = idade;
        this.aniversario = aniversario;
        this.documento = documento;
        this.cargo = cargo;
        this.departamento = departamento;
        if(!this.historicoDeDepartamentos.contains(departamento)){
            this.historicoDeDepartamentos.add(departamento);
        }
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Departamento> getHistoricoDeDepartamentos() {
        return historicoDeDepartamentos;
    }

    public void setHistoricoDeDepartamentos(List<Departamento> departamentos) {
        this.historicoDeDepartamentos = departamentos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        if(!this.historicoDeDepartamentos.contains(departamento)) {
            this.historicoDeDepartamentos.add(departamento);
        }
    }
}

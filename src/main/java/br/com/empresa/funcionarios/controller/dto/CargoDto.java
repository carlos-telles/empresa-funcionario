package br.com.empresa.funcionarios.controller.dto;

import br.com.empresa.funcionarios.model.Cargo;

public class CargoDto {

    private Long id;

    private String nome;

    public CargoDto(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
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
}

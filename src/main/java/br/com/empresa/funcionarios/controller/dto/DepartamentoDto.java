package br.com.empresa.funcionarios.controller.dto;

import br.com.empresa.funcionarios.model.Departamento;

public class DepartamentoDto {
    private Long id;
    private String nome;

    public DepartamentoDto(Departamento departamento) {
        this.id = departamento.getId();
        this.nome = departamento.getNome();
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

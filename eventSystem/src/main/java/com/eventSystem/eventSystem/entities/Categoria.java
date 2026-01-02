package com.eventSystem.eventSystem.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="tb_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Atividade> atividades = new ArrayList<>();

    public Categoria(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;

    }
    public Categoria(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(getId(), categoria.getId()) && Objects.equals(getdescricao(), categoria.getdescricao()) && Objects.equals(getAtividades(), categoria.getAtividades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getdescricao(), getAtividades());
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", atividades=" + atividades +
                '}';
    }
}

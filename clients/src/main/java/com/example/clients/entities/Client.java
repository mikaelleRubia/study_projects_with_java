package com.example.clients.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String cpf;
    private Double income;
    private LocalDate bithDate;
    private Integer children;

    public Client() {
    }

    public Client(String name, String cpf, Double income, LocalDate bithDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.bithDate = bithDate;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBithDate() {
        return bithDate;
    }

    public void setBithDate(LocalDate bithDate) {
        this.bithDate = bithDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(getName(), client.getName()) && Objects.equals(getCpf(), client.getCpf()) && Objects.equals(getIncome(), client.getIncome()) && Objects.equals(getBithDate(), client.getBithDate()) && Objects.equals(getChildren(), client.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getCpf(), getIncome(), getBithDate(), getChildren());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", income=" + income +
                ", bithDate=" + bithDate +
                ", children=" + children +
                '}';
    }
}


package com.example.clients.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    @Size(min = 3, max=80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank(message = "Campo requerido")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;
    @Positive(message = "O preço de ser positivo")
    private Double income;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "A data deve estar no passado")
    private LocalDate bithDate;
    private Integer children;

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate bithDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.bithDate = bithDate;
        this.children = children;
    }

    public ClientDTO() {}

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getCpf() {return cpf;}

    public Double getIncome() {return income;}

    public LocalDate getBithDate() {return bithDate;}

    public Integer getChildren() {return children;}

}

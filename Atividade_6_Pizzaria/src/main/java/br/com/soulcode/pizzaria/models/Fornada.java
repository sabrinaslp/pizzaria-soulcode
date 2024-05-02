package br.com.soulcode.pizzaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT")
    private int idFornada;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int numFornada;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int qtdPizzas;

    @JsonIgnore // Para não mostrar na resposta da requisição
    @OneToMany(mappedBy = "fornada")
    private List<Pedido> pedidos;

}

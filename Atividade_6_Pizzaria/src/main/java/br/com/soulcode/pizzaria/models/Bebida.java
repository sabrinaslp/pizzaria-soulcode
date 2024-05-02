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
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBebida;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float preco;

    @JsonIgnore // Para não mostrar na resposta Json da requisição
    @OneToMany(mappedBy = "bebida")
    private List<BebidaPedida> bebidasPedidas;

}

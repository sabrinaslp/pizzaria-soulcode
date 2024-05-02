package br.com.soulcode.pizzaria.models;

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
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Long idTamanho;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float desconto;

    @OneToMany(mappedBy = "tamanho")
    private List<PizzaPedida> pizzasPedidas;

}

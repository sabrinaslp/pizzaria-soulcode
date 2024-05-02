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
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngrediente;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float preco;

    @ManyToMany(mappedBy = "ingredientes")
    private List<PizzaPedida> pizzasPedidas;

}

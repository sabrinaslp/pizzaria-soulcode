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
public class PizzaPedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPizzaPedida;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idPizza", nullable = false)
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "idTamanho", nullable = false)
    private Tamanho tamanho;

    @Column(nullable = false)
    private int quantidade;

    @ManyToMany
    @JoinTable(
            name = "PizzaPedida_has_Ingrediente",
            joinColumns = @JoinColumn(name = "idPizzaPedida"),
            inverseJoinColumns = @JoinColumn(name = "idIngrediente")
    )
    private List<Ingrediente> ingredientes;

}

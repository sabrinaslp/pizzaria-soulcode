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
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPizza;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private float precoBase;

    @Column(columnDefinition = "BOOL")
    private boolean personalizada;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    private List<PizzaPedida> pizzasPedidas;

}

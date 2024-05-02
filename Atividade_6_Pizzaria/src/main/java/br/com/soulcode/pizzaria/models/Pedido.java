package br.com.soulcode.pizzaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<PizzaPedida> pizzasPedidas;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idFornada")
    private Fornada fornada;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<BebidaPedida> bebidasPedidas;

    @Column(nullable = false)
    private LocalDateTime dataHora;

}

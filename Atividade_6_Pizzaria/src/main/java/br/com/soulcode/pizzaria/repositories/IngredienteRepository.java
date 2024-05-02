package br.com.soulcode.pizzaria.repositories;

import br.com.soulcode.pizzaria.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}

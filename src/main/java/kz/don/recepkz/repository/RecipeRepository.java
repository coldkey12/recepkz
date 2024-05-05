package kz.don.recepkz.repository;

import kz.don.recepkz.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeById(Long id);

    @Query("SELECT r FROM Recipe r")
    List<Recipe> listAllRecipe();

    @Query("SELECT r FROM Recipe r " +
            "WHERE r.category ilike concat('%', :search, '%') " +
            "OR r.description ilike concat('%', :search, '%') " +
            "OR r.username ilike concat('%', :search, '%')")
    List<Recipe> search(String search);
}

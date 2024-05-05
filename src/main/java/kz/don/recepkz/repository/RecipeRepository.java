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

}

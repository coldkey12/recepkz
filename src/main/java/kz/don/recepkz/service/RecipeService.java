package kz.don.recepkz.service;

import kz.don.recepkz.model.Recipe;
import kz.don.recepkz.model.User;
import kz.don.recepkz.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> listAllRecipe(){
        return recipeRepository.listAllRecipe();
    }

    public Recipe recipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public void addRecipe(Recipe recipe, User currentUser) {
        recipe.setUserId(currentUser.getId());
        recipe.setUsername(currentUser.getUsername());
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}

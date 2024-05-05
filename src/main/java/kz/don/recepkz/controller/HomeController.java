package kz.don.recepkz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kz.don.recepkz.model.Comment;
import kz.don.recepkz.model.Recipe;
import kz.don.recepkz.model.User;
import kz.don.recepkz.repository.CommentRepository;
import kz.don.recepkz.service.CommentService;
import kz.don.recepkz.service.RecipeService;
import kz.don.recepkz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/home")
    public String homePage(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        List<Recipe> recipeList = recipeService.listAllRecipe();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("recipeList", recipeList);
        return "home";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/auth")
    public String login(User user, HttpServletRequest req){
        boolean isLoginSuccessful = userService.login(user, req);
        if(isLoginSuccessful){
            return "redirect:/home";
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(){
        return "signup";
    }

    @PostMapping("/register")
    public String register(User user,@RequestParam String rePassword){
        if(!userService.isUserExistByUsername(user.getUsername())
                && user.getPassword().equals(rePassword)
        && user.getUsername()!=null){
            userService.register(user);
            return "redirect:/loginPage";
        }
        return "redirect:/registerPage";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser!=null){
            session.removeAttribute("currentUser");
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/recipe/{id}")
    public String recipeDetail(@PathVariable Long id, Model model, HttpServletRequest req){
        Recipe recipe = recipeService.recipeById(id);
        List<Comment> comments = commentService.listCommentsByRecipeId(id);
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("recipe", recipe);
        model.addAttribute("comments", comments);
        return "recipe-details";
    }

    @GetMapping("/add-recipe-page")
    public String addRecipePage(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        if(model.getAttribute("currentUser")!=null){
            return "add-recipe";
        }
        return "login";
    }

    @PostMapping("/add-recipe")
    public String addRecipe(HttpServletRequest req, Recipe recipe){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        recipe.setUsername(currentUser.getUsername());
        recipeService.addRecipe(recipe, currentUser);
        return "redirect:/home";
    }

    @PostMapping("/post-comment")
    public String postComment(HttpServletRequest req, Comment comment){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        comment.setUserId(currentUser.getId());
        comment.setUsername(currentUser.getUsername());
        if(comment.getText()!=null || comment.getText()=="") {
            commentService.addComment(comment);
        }
        return "redirect:/recipe/" + comment.getRecipeId();
    }

    @GetMapping("/update-recipe/{id}")
    public String recipeUpdate(@PathVariable Long id, Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("recipe", recipeService.recipeById(id));
        if(currentUser.getId()==recipeService.recipeById(id).getUserId()){
            return "recipe-update";
        }
        return "home";
    }

    @PostMapping("/update-old-recipe")
    public String updateRecipe(@RequestParam Long recipeId, @RequestParam LocalDateTime postDate, Recipe recipe, HttpServletRequest req){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        recipe.setUserId(currentUser.getId());
        recipe.setUsername(currentUser.getUsername());
        recipe.setId(recipeId);
        recipe.setPostDate(postDate);
        recipeService.updateRecipe(recipe);
        return "redirect:/home";
    }

    @PostMapping("/delete-recipe")
    public String deleteRecipe(@RequestParam Long recipeId, HttpServletRequest req){
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser.getId()==recipeService.recipeById(recipeId).getUserId()) {
            recipeService.deleteRecipe(recipeId);
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @GetMapping("/recipe/search")
    public String getRecipes(@RequestParam String search, Model model){
        List<Recipe> recipeList = recipeService.search(search);
        model.addAttribute("recipeList", recipeList);
        return "home";
    }
}

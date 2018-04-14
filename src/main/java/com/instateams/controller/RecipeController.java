package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import com.instateams.model.Status;
import com.instateams.service.PortionService;
import com.instateams.service.RecipeService;
import com.instateams.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController
{
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PortionService portionService;

    @ModelAttribute("allIngredients")
    public List<Ingredient> populateIngredients()
    {
        return ingredientService.findAll();
    }

    @ModelAttribute("allStatus")
    public List<Status> populateStatus()
    {
        return recipeService.allStatus();
    }

    @ModelAttribute("allPortions")
    public List<Portion> populatePortions()
    {
        return portionService.findAll();
    }

    @ModelAttribute("focus")
    public String focus()
    {
        return "recipes";
    }


    @RequestMapping("/")
    public String allRecipes(Model model)
    {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/index";
    }

    @RequestMapping("/recipes/add")
    public String addForm(Model model)
    {
        if (!model.containsAttribute("recipe"))
        {
            model.addAttribute("recipe", new Recipe());
        }
        model.addAttribute("action", "/recipes/add");

        return "recipe/form";
    }

    @RequestMapping(value = "/recipes/add", method = RequestMethod.POST)
    public String addRecipe(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipe", result);
            redirectAttributes.addFlashAttribute("recipe", recipe);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return "redirect:/recipes/add";
        }

        recipeService.save(recipe);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Recipe added",
                FlashMessage.Status.SUCCESS));

        return String.format("redirect:/recipes/%s", recipe.getId());
    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.POST)
    public String editRecipe(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipe", result);
            redirectAttributes.addFlashAttribute("recipe", recipe);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return String.format("redirect:/recipes/%s/edit", recipe.getId());
        }

        recipeService.save(recipe);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Recipe updated",
                FlashMessage.Status.SUCCESS));

        return String.format("redirect:/recipes/%s", recipe.getId());
    }


    @RequestMapping("/recipes/{id}")
    public String recipeDetails(@PathVariable Long id, Model model)
    {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe/details";
    }

    @RequestMapping("/recipes/{id}/edit")
    public String editForm(@PathVariable Long id, Model model)
    {
        Recipe recipe = recipeService.findById(id);
        if (!model.containsAttribute("recipe"))
        {
            model.addAttribute("recipe", recipe);
        }
        model.addAttribute("action", "/recipes/" + recipe.getId());

        return "recipe/form";
    }

    @RequestMapping("recipes/{id}/portions")
    public String editCollForm(@PathVariable Long id, Model model)
    {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe/portions";
    }

    @RequestMapping("/recipes/{id}/delete")
    public String deleteRecipe(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Deletion failed",
                FlashMessage.Status.FAILURE));

        Recipe recipe = recipeService.findById(id);
        recipeService.delete(recipe);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Recipe deleted",
                FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }
}

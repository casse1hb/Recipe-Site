package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Ingredient;
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
public class IngredientController
{
    @Autowired
    private IngredientService ingredientService;

    @ModelAttribute("focus")
    public String focus()
    {
        return "ingredients";
    }

    @RequestMapping("/ingredients")
    public String allIngredients(Model model)
    {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        if (!model.containsAttribute("ingredient"))
        {
            model.addAttribute("ingredient", new Ingredient());
            model.addAttribute("action", "/ingredients");
        }

        return "ingredient/index";
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.POST)
    public String addIngredient(@Valid Ingredient ingredient, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredient", result);
            redirectAttributes.addFlashAttribute("ingredient", ingredient);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return "redirect:/ingredients";
        }

        ingredientService.save(ingredient);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Ingredient added", FlashMessage.Status
                .SUCCESS));

        return "redirect:/ingredients";
    }

    @RequestMapping("/ingredients/{id}")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Ingredient ingredient = ingredientService.findById(id);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("action", String.format("/ingredients/%s", id));

        return "ingredient/index";
    }

    @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.POST)
    public String editIngredient(@Valid Ingredient ingredient, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredient", result);
            redirectAttributes.addFlashAttribute("ingredient", ingredient);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return String.format("redirect:/ingredients/%s", ingredient.getId());
        }

        ingredientService.save(ingredient);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Ingredient updated", FlashMessage.Status
                .SUCCESS));

        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/ingredients/{id}/delete")
    public String deleteIngredient(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Deletion failed",
                FlashMessage.Status.FAILURE));

        Ingredient ingredient = ingredientService.findById(id);
        ingredientService.delete(ingredient);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Ingredient deleted.", FlashMessage.Status
                .SUCCESS));

        return "redirect:/ingredients";
    }
}

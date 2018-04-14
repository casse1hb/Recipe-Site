package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Portion;
import com.instateams.model.Ingredient;
import com.instateams.service.PortionService;
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
public class PortionController
{
    @Autowired
    private PortionService portionService;

    @Autowired
    private IngredientService ingredientService;

    @ModelAttribute("allIngredients")
    public List<Ingredient> populateIngredients()
    {
        return ingredientService.findAll();
    }

    @ModelAttribute("focus")
    public String focus()
    {
        return "portions";
    }

    @RequestMapping("/portions")
    public String allPortions(Model model)
    {
        List<Portion> portions = portionService.findAll();
        model.addAttribute("portions", portions);

        if (!model.containsAttribute("portion"))
        {
            model.addAttribute("portion", new Portion());
        }
        model.addAttribute("action", "/portions");

        return "portion/index";
    }

    @RequestMapping(value = "/portions", method = RequestMethod.POST)
    public String addPortion(@Valid Portion portion, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.portion", result);
            redirectAttributes.addFlashAttribute("portion", portion);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return "redirect:/portions";
        }

        portionService.save(portion);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Portion added",
                FlashMessage.Status.SUCCESS));

        return "redirect:/portions";
    }

    @RequestMapping("/portions/{id}")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Portion portion = portionService.findById(id);

        if (!model.containsAttribute("portion"))
        {
            model.addAttribute("portion", portion);
        }
        model.addAttribute("action", String.format("/portions/%s", id));

        return "portion/index";
    }

    @RequestMapping(value = "/portions/{id}", method = RequestMethod.POST)
    public String editPortion(@Valid Portion portion, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.portion", result);
            redirectAttributes.addFlashAttribute("portion", portion);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Invalid data",
                    FlashMessage.Status.FAILURE));

            return String.format("redirect:/portions/%s", portion.getId());
        }

        portionService.save(portion);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Portion updated",
                FlashMessage.Status.SUCCESS));

        return "redirect:/portions";
    }

    @RequestMapping("portions/{id}/delete")
    public String deletePortion(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Deletion failed",
                FlashMessage.Status.FAILURE));

        Portion portion = portionService.findById(id);
        portionService.delete(portion);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Portion deleted.",
                FlashMessage.Status.SUCCESS));


        return "redirect:/portions";
    }
}

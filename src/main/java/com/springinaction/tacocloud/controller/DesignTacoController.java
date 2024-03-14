package com.springinaction.tacocloud.controller;

import com.springinaction.tacocloud.Ingredient;
import com.springinaction.tacocloud.Ingredient.Type;
import com.springinaction.tacocloud.Order;
import com.springinaction.tacocloud.Taco;
import com.springinaction.tacocloud.data.IngredientRepository;
import com.springinaction.tacocloud.data.TacoRepository;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO","Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO","Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF","Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN","Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC","Lettuce", Type.VEGGIES),
//                new Ingredient("CHED","Cheddar", Type.CHEESE),
//                new Ingredient("JACK","Monterrey", Type.CHEESE),
//                new Ingredient("SLSA","Salsa", Type.SAUCE),
//                new Ingredient("SRCR","Sour Cream", Type.SAUCE)
//        );

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        // 선택된 식자재 내역을 저장한다
        log.info("Processing design: " + design);

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .toList();
    }
}

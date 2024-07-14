package com.example.bmicalculator.controller;

import com.example.bmicalculator.model.BmiForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BmiController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("bmiForm", new BmiForm());
        return "bmiForm";
    }

    @PostMapping("/calculate")
    public String calculateBmi(@ModelAttribute BmiForm bmiForm, Model model) {
        double weight = bmiForm.getWeight();
        double height = bmiForm.getHeightInMeters();
        double bmi = weight / (height * height);
        String category = getCategory(bmi);

        model.addAttribute("bmi", bmi);
        model.addAttribute("category", category);
        model.addAttribute("bmiForm", bmiForm);
        return "result";
    }

    private String getCategory(double bmi) {
        if (bmi < 16) {
            return "Wygłodzenie";
        } else if (bmi < 17) {
            return "Wychudzenie";
        } else if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Waga prawidłowa";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else if (bmi < 35) {
            return "Otyłość I stopnia";
        } else if (bmi < 40) {
            return "Otyłość II stopnia";
        } else {
            return "Otyłość III stopnia";
        }
    }
}

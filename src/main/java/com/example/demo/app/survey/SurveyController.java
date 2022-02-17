package com.example.demo.app.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@GetMapping("/form")
	public String form(
		Model model,
		SurveyForm surveyForm,
		@ModelAttribute("complete") String complete)
	{
		model.addAttribute("title", "SurveyForm");
		return "survey/form";
	}
	
	@PostMapping("/form")
	public String formGoBack(Model model, SurveyForm surveyForm) {
		model.addAttribute("title", "SurveyForm");
		return "survey/form";
	}
	
	@PostMapping("/confirm")
	public String confirm(
		Model model,
		@Validated SurveyForm surveyForm,
		BindingResult result)
	{
		if(result.hasErrors()) {
			model.addAttribute("title", "SurveyForm");
			return "survey/form";
		}
		model.addAttribute("title", "Cnfirm Page");
		return "survey/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(
		Model model,
		@Validated SurveyForm surveyForm,
		BindingResult result,
		RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors()) {
			model.addAttribute("title", "SurveyForm");
			return "survey/form";
		}
		model.addAttribute("title", "form");
		redirectAttributes.addAttribute("complete", "完了");
		return "redirect:/survey/form";
	}
}

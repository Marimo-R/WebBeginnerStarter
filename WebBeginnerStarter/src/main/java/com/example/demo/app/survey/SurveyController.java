package com.example.demo.app.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	//private final SurveyService surveyService;
	
	//public SurveyController(SurveyService surveyService){
	//	this.surveyService = surveyService;
	//}
	
	@GetMapping
	public String index(Model model) {
		
		//hands-on
		
		return "survey/index";
	}
	
	@GetMapping("/form")
	public String form(SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		
		model.addAttribute("title", "Survey Form");
		
		return "survey/form";
	}
	
	@PostMapping("/form")
	public String formBack(SurveyForm surveyForm, Model model) {
		model.addAttribute("title", "SurveyForm");		
		return "survey/form";
	}
	
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Survey Form");
			return "survey/form";
		}
		model.addAttribute("title", "Confirm From");
		return "survey/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/from";
		}
		redirectAttributes.addFlashAttribute("complete", "Registerd!");
		return "redirect:/survey/form";
	}
	
}

package btl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import btl.Car;
import btl.data.CarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/") 
public class CarController {
	private final CarRepository carRepo;
	
	@Autowired
	public CarController(CarRepository carRepo) {
		this.carRepo = carRepo;
	} 
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		
	}
	
	@GetMapping
	public String showAddForm(Model model) {
		List<Car> car = (List<Car>) carRepo.findAll(); 
		model.addAttribute("cars", car);
		return "findAll";
	}
	
	@PostMapping
	public String addIngredient(Car car, Model model) {
		carRepo.save(car);
		model.addAttribute(car);
//		log.info("Ingredient saved: " + car);
		return "addIngredientSuccess";
	}
}

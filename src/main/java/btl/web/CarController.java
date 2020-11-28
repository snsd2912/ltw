package btl.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import btl.Car;
import btl.data.CarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/car") 
public class CarController {
	private final CarRepository carRepo;
	
	@Autowired
	public CarController(CarRepository carRepo) {
		this.carRepo = carRepo;
	} 
	
	@GetMapping
	public String showAllCar(Model model) {
		List<Car> car = (List<Car>) carRepo.findAll(); 
		model.addAttribute("cars", car);
		return "findAll";
	}
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("car", new Car());
		return "add";
	}
	@PostMapping("/add")
	public String addCar(Car car, Model model) {
		carRepo.save(car);
		model.addAttribute(car);
//		log.info("Ingredient saved: " + car);
//		return "addCarSuccess";
		return "redirect:/car";
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model) {
		model.addAttribute("car", new Car());
		return "edit";
	}
	@PutMapping("/edit/{id}")
	public String editCar(@PathVariable("id") Long id, @RequestBody Car carRequest, Model model) {
		/* Optional<Car> car = carRepo.findById(id); */
		Optional<Car> car1 =carRepo.findById(id);
		Car car = car1.get();
		car.setBrandname(carRequest.getBrandname());
		car.setColor(carRequest.getColor());
		car.setModel(carRequest.getModel());
		car.setPlatenumber(carRequest.getPlatenumber());
		car.setSeatnumber(carRequest.getSeatnumber());
		car.setUsedyear(carRequest.getUsedyear());
		car.setLastmaintaincedate(carRequest.getLastmaintaincedate());
		carRepo.save(car);
		model.addAttribute("car", car);
		return "redirect:/car";
	}
	
	@GetMapping("/{id}")
	public Car carById(@PathVariable("id") Long id) {
	Optional<Car> optCar = carRepo.findById(id);
			if (optCar.isPresent()) {
			return optCar.get();
			}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public String deleteCar(@PathVariable("id") Long id, Car car,Model model) {
		carRepo.deleteById(id);
		model.addAttribute(car);
		return "redirect:/car";
	}
}

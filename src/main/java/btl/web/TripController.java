package btl.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import btl.Car;
import btl.Employee;
import btl.Pair;
import btl.Trip;
import btl.data.CarRepository;
import btl.data.EmployeeRepository;
import btl.data.TripRepository;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/tk") 
public class TripController {
	private final TripRepository tripRepo;
	private final EmployeeRepository emRepo;
	private final CarRepository carRepo;
	
	@Autowired
	public TripController(TripRepository tripRepo, EmployeeRepository emRepo, CarRepository carRepo) {
		this.tripRepo = tripRepo;
		this.emRepo = emRepo;
		this.carRepo = carRepo;
	} 
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		
	}
	
	@GetMapping("/luong")
	public String showSalaryForm(Model model) {
		List<Employee> em  = (List<Employee>) emRepo.findAll(); 
		ArrayList<Pair<Employee, Long> > pair = new ArrayList<>();
		for(Employee employee : em) {
			model.addAttribute("pairs", employee.getName() );
			List<Trip> trip1 = (List<Trip>) tripRepo.findByCarId(employee.getId());
			List<Trip> trip2 = (List<Trip>) tripRepo.findBySubdriverId(employee.getId());
			long luong = 0;
			for(Trip t : trip1) {
				int level = t.getRoute().getComplicatedlevel();
				if(level==1) luong += 300000;
				else if(level==2) luong+=200000;
				else luong+=100000;
			}
			
			for(Trip t : trip2) {
				int level = t.getRoute().getComplicatedlevel();
				if(level==1) luong += 150000;
				else if(level==2) luong+=100000;
				else luong+=50000;
			}
			pair.add( new Pair<Employee, Long>(employee, luong));
		}
		model.addAttribute("pairs", pair );
		return "tk";
	}
	
	@GetMapping("/xe")
	public String showCarForm(Model model) {
		List<Car> em  = (List<Car>) carRepo.findAll(); 
		ArrayList<Pair<Car, Float> > pair = new ArrayList<>();
		for(Car car : em) {
			List<Trip> trip1 = (List<Trip>) tripRepo.findByCarId(car.getId());
			float doanhthu = 0;
			for(Trip t : trip1) {
				doanhthu += t.getPrice() * t.getNumberofpassengers();
			}
			pair.add( new Pair<Car, Float>(car, doanhthu));
		}
		model.addAttribute("pairs", pair );
		return "tkdoanhthu";
	}
	
	
}

package btl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne(targetEntity=Route.class)
	private Route route;
	@ManyToOne(targetEntity=Car.class)
	private Car car;
	@ManyToOne(targetEntity=Employee.class)
	private Employee driver;
	@ManyToOne(targetEntity=Employee.class)
	private Employee subdriver;
	private int numberofpassengers;
	private float price;
}
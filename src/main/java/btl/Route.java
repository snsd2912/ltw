package btl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Route {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String start;
	private String end;
	private float length;
	private int complicatedlevel;
}

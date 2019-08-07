package pl.kobra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_department")
	private Integer idDepartment;
	
	@Column(name = "name")
	private String name;
	
	public Department() {
		
	}
	public Department(String name) {
		this.name = name;
	}

	public Integer getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [idDepartment=" + idDepartment + ", name=" + name + "]";
	}
	
	
}

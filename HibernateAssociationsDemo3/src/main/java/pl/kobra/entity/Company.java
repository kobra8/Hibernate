package pl.kobra.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

	@Entity
	@Table(name="company")
	public class Company {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_company")
		private Integer idCompany;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "value")
		private Integer value;
		
		@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}) // Ustawienie na zapis, usuwanie i odœwierzanie kaskadowe obiektów powi¹zanych
		@JoinColumn(name="id_company_detail")
		private CompanyDetail companyDetail;
		
		@OneToMany(mappedBy = "company", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
		private List<Property> properties;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "id_company")
		private Set<Department> departments;

		public Company() {
			
		}

		public Company(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public Integer getIdCompany() {
			return idCompany;
		}

		public void setIdCompany(Integer idCompany) {
			this.idCompany = idCompany;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		

		public CompanyDetail getCompanyDetail() {
			return companyDetail;
		}

		public void setCompanyDetail(CompanyDetail companyDetail) {
			this.companyDetail = companyDetail;
		}
		
		public List<Property> getProperties() {
			return properties;
		}

		public void setProperties(List<Property> properties) {
			this.properties = properties;
		}
		
		public void addProperty(Property property) {
			if (properties == null) {
				properties = new ArrayList<>(); // Je¿eli nie istnieje lista to trzeba j¹ zainicjalizowaæ
			} 
			
			properties.add(property); // Dodanie pojedynczej property dop listy
			property.setCompany(this); // Ustawienie wartoœci pola company na aktualn¹ instancjê Company - metoda z klasy Property
		}

		public Set<Department> getDepartments() {
			return departments;
		}

		public void setDepartments(Set<Department> departments) {
			this.departments = departments;
		}
		
		public void addDepartment(Department department) {
			if (departments == null) {
				departments = new HashSet<>(); // Je¿eli nie istnieje set to trzeba go zainicjalizowaæ
			} 
			departments.add(department);
		}

		@Override
		public String toString() {
			return "Company [idCompany=" + idCompany + ", name=" + name + ", value=" + value + "]";
		}
		
		
}

package pl.kobra;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Property;

public class OneToManyUniGetApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klas
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
			
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		int id = 31;
		session.beginTransaction();
		
		Department departmentSingle = session.get(Department.class, 8);
		System.out.println(departmentSingle);
		
		Company company = session.get(Company.class, id);
		System.out.println(company);
		
		Set<Department> departments = company.getDepartments();
		
		for(Department department: departments) {
			System.out.println(department);
		}
		
					
		session.getTransaction().commit();
		
		factory.close();
	}

}

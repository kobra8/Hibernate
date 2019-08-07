package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Property;

public class OneToManyUniSaveApp {

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
		
		Company company = session.get(Company.class, id);
		System.out.println(company);
		
		Department department = new Department("sales");
		Department department2 = new Department("production");
		Department department3 = new Department("HR");
		
		company.addDepartment(department);
		company.addDepartment(department2);
		company.addDepartment(department3);
		
		session.persist(company); // Zapis wykona siê  kaskadowo - po zapisaniu company powinny zapisaæ siê departments
			
		session.getTransaction().commit();
		
		factory.close();
	}

}

package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Employee;
import pl.kobra.entity.Property;
import pl.kobra.entity.Training;

public class ManyToManySaveApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klas
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
		conf.addAnnotatedClass(Employee.class);
		conf.addAnnotatedClass(Training.class);
			
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Training training = new Training("Sales training");
	
		Employee employee = new Employee("Johny", "Deep", 16000);
		Employee employee2 = new Employee("Miley", "Cyrus", 16000);
		
		training.addEmployee(employee);
		training.addEmployee(employee2);
		
		session.persist(training);
			
		session.getTransaction().commit();
		
		factory.close();

	}

}

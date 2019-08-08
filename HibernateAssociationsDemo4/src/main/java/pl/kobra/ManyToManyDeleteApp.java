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

public class ManyToManyDeleteApp {

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
		
		int idEmployeeToDelete = 42;
		int idTrainigToDelete = 3;
		
//		Employee employee = session.get(Employee.class, idEmployeeToDelete);
//		session.delete(employee);
		
		Training training = session.get(Training.class, idTrainigToDelete);
		session.delete(training);
			
		session.getTransaction().commit();
		
		factory.close();
	}

}

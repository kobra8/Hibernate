package pl.kobra.hibernateDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class PrimaryKeyApp {

	public static void main(String[] args) {
		
		// Stworzenie obiektu Configuration
				Configuration conf = new Configuration();
				// Wczytanie pliku konfiguracyjnego hibernate.cfg
				conf.configure("hibernate.cfg.xml");	
				// Wczytanie adnotacji klasy Employee
				conf.addAnnotatedClass(Employee.class);
				// Stworzenie obiektu Session factory
				SessionFactory factory = conf.buildSessionFactory();
				// Pobieranie sesji
				Session session = factory.getCurrentSession();
				// Stworzenie 3 obiektów klasy Employee
				Employee employee = new Employee();
				employee.setFirstName("Jan");
				employee.setLastName("Kowalski");
				employee.setSalary(10000);
				
				Employee employee2 = new Employee();
				employee2.setFirstName("Anna");
				employee2.setLastName("Walewska");
				employee2.setSalary(14000);
				
				Employee employee3 = new Employee();
				employee3.setFirstName("Greg");
				employee3.setLastName("Novak");
				employee3.setSalary(11000);
				
				
				// Rozpoczêcie transakcji
				session.beginTransaction();
				// Zapisanie 3 pracowników
				session.save(employee);
				session.save(employee2);
				session.save(employee3);
				// Zakoñczenie transakcji
				session.getTransaction().commit();
				// Zamkniêcie obiektu SessionFactory
				factory.close();
		
	}

}

package pl.kobra.hibernateDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class SaveEntityApp {

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
		// Stworzenie obiektu klasy Employee
		Employee employee = new Employee();
		employee.setIdEmployee(1);
		employee.setFirstName("Jan");
		employee.setLastName("Kowalski");
		employee.setSalary(10000);
		// Rozpoczêcie transakcji
		session.beginTransaction();
		// Zapisanie pracownika
		session.save(employee);
		// Zakoñczenie transakcji
		session.getTransaction().commit();
		// Zamkniêcie obiektu SessionFactory
		factory.close();
	}

}

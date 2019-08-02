package pl.kobra.hibernateDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class UpdateDetachedEntityApp {

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
	
		// Rozpoczêcie transakcji
		session.beginTransaction();
	
		// Zakoñczenie transakcji
		session.getTransaction().commit();
		
		//Wczytywanie pojedynczej encji po id
		session = factory.getCurrentSession();
		session.beginTransaction();
		Employee employee = session.get(Employee.class, 12);
		session.getTransaction().commit();
		
		System.out.println("Dane pracownika: " + employee);
		// Ustawianie nowej wartoœci pola
		employee.setFirstName("Marcin");
		
		// Update encji nie powi¹zany z kontekstem utrwalania
		session = factory.getCurrentSession();
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
		
		System.out.println("Zaktualizowane Dane pracownika: " + employee);
		
		// Zamkniêcie obiektu SessionFactory
		factory.close();

	}

}

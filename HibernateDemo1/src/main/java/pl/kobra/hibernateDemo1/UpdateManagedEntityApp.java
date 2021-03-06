package pl.kobra.hibernateDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class UpdateManagedEntityApp {

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
			
				// Rozpocz�cie transakcji
				session.beginTransaction();
			
				// Zako�czenie transakcji
				session.getTransaction().commit();
				
				//Wczytywanie pojedynczej encji po id
				session = factory.getCurrentSession();
				session.beginTransaction();
				Employee employee = session.get(Employee.class, 12);
				
				// Update encji powi�zany z kontekstem utrwalania - nie trzeba wywo�ywa� metody update()
				employee.setFirstName("Tadeusz");
				
				session.getTransaction().commit();
								
				
				System.out.println("Zaktualizowane Dane pracownika: " + employee);
				
				// Zamkni�cie obiektu SessionFactory
				factory.close();

	}

}

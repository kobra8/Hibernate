package pl.kobra.hibernateDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class GetEntityApp {

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
				employee.setFirstName("Tadeusz");
				employee.setLastName("Wiœniewski");
				employee.setSalary(13000);
				// Rozpoczêcie transakcji
				session.beginTransaction();
				// Zapisanie pracownika
				Integer id = (Integer) session.save(employee);
				// Zakoñczenie transakcji
				session.getTransaction().commit();
				
				//Wczytywanie pojedynczej encji po id
				session = factory.getCurrentSession();
				session.beginTransaction();
				Employee retrivedEmployee = session.get(Employee.class, id);
				session.getTransaction().commit();
				
				System.out.println("Dane pracownika: " + retrivedEmployee);
				
				// Zamkniêcie obiektu SessionFactory
				factory.close();
	}

}

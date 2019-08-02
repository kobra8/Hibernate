package pl.kobra.hibernateDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.hibernateDemo1.entity.Employee;

public class GetAllEntitiesApp {

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
				// Pobieranie wszystkich encji
				List<Employee> resultList = session.createQuery("from Employee").getResultList();
				for (Employee item: resultList) {
					System.out.println("Dane pracownika: " + item);
				}
	
				// Zako�czenie transakcji
				session.getTransaction().commit();
				// Zamkni�cie obiektu SessionFactory
				factory.close();
	}

}

package pl.kobra.hqlDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class OrderByApp {

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
		
		String orderBy = "select e.firstName, e.lastName from Employee e order by e.firstName";
		String orderByDesc = "select e.firstName, e.lastName from Employee e order by e.firstName desc"; // Sortowanie malej�co
		String orderByAsc = "select e.firstName, e.lastName, e.salary from Employee e order by e.salary asc"; // Sortowanie malej�co
	
		
		Query query = session.createQuery(orderByAsc);
		List<Object[]> result = query.getResultList();
		
		session.getTransaction().commit();
		
		for (Object[] values: result) {
			System.out.println("Imi�: " + values[0] + ", Nazwisko: " + values[1] + " Wyp�ata: " + values[2]);
		}
		
		// Zamkni�cie obiektu SessionFactory
		factory.close();
	}

}

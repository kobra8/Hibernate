package pl.kobra.hqlDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class DeleteApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		// Wczytanie pliku konfiguracyjnego hibernate.cfg
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klasy Employee
		conf.addAnnotatedClass(Employee.class);
		// Stworzenie obiektu Session factory
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		int idEmployee = 108;
		
		session.beginTransaction();		
		
		String delete = "delete Employee e where e.idEmployee=:idEmployee";
		
		Query query = session.createQuery(delete);
		query.setParameter("idEmployee", idEmployee);
		int rows = query.executeUpdate(); // Ta metoda zwraca iloœæ wierszy na które mia³ wp³yw update
		System.out.println("Iloœæ usuniêtych rekordów: " + rows);
		
		session.getTransaction().commit();
		
		factory.close();

	}

}

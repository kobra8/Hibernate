package pl.kobra.hqlDemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class UpdateApp {

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
		
		int idEmployee = 118;
		int salary = 20000;
		
		session.beginTransaction();		
		
		String update = "update Employee e set e.salary=:salary where e.idEmployee=:idEmployee";
		
		Query query = session.createQuery(update);
		query.setParameter("salary", salary);
		query.setParameter("idEmployee", idEmployee);
		query.executeUpdate();
		
		session.getTransaction().commit();
		
		factory.close();

	}

}

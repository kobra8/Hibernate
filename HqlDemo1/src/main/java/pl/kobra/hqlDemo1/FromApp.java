package pl.kobra.hqlDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class FromApp {

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
				
				// Instrukcja from
				String from = "FROM Employee";
				String from2 = "from pl.kobra.hqlDemo1.entity.Employee"; //  W przypadku kiedy nazwy encji siê powtarzaj¹ podajemy pe³n¹ nazwê z pakietem
				
				Query query =  session.createQuery(from2);
				
				List<Employee> list = query.getResultList();
			
				session.getTransaction().commit();
				
				for (Employee item: list) {
					System.out.println(item);
				}
				
				
				// Zamkniêcie obiektu SessionFactory
				factory.close();

	}

}

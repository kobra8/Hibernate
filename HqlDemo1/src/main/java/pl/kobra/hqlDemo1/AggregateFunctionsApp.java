package pl.kobra.hqlDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class AggregateFunctionsApp {

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
			
				String avg = "select avg(e.salary) from Employee e"; // Œrednia
				String sum = "select sum(e.salary) from Employee e"; // Suma
				String min = "select min(e.salary) from Employee e"; // Min
				String max = "select max(e.salary) from Employee e"; // Max
				String count = "select count(e.salary) from Employee e"; // Iloœæ rekordów
				
				Query query = session.createQuery(avg);
				Query query2 = session.createQuery(sum);
				Query query3 = session.createQuery(min);
				Query query4 = session.createQuery(max);
				Query query5 = session.createQuery(count);
				
				Double result = (Double) query.getSingleResult();
				Long result2 = (Long) query2.getSingleResult();
				Integer result3 = (Integer) query3.getSingleResult();
				Integer result4 = (Integer) query4.getSingleResult();
				Long result5 = (Long) query5.getSingleResult();
			
				session.getTransaction().commit();
				
				System.out.println("Srednia wynosi: " + result);
				System.out.println("Suma wynosi: " + result2);
				System.out.println("Minimalna wartoœæ wynosi: " + result3);
				System.out.println("Maksymalna wartoœæ wynosi: " + result4);
				System.out.println("Iloœæ rekordów wynosi: " + result5);
				
				// Zamkniêcie obiektu SessionFactory
				factory.close();

	}

}

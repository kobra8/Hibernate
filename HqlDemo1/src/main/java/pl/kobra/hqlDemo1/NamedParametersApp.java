package pl.kobra.hqlDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class NamedParametersApp {

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
				
				
				String employeeFirstName = "Steven";
				String employeeLastName = "King";
				
				String queryString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName=:firstName and e.lastName=:lastName";
				// Rozpoczêcie transakcji
				session.beginTransaction();				
				
				Query namedParamsQuery = session.createQuery(queryString);
				namedParamsQuery.setParameter("firstName", employeeFirstName);
				namedParamsQuery.setParameter("lastName", employeeLastName);
				
				List<Object[]> result = namedParamsQuery.getResultList();
				
				session.getTransaction().commit();
				
				for (Object[] values: result) {
					System.out.println("Imiê: " + values[0] + ", Nazwisko: " + values[1] + " Wyp³ata: " + values[2]);
				}
				
				// Zamkniêcie obiektu SessionFactory
				factory.close();
				
	}

}

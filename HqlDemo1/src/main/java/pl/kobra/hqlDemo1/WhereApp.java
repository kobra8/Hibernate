package pl.kobra.hqlDemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

import pl.kobra.hqlDemo1.entity.Employee;

public class WhereApp {

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
				
				// Instrukcja where
				String where = "from Employee where firstName='Tadeusz'";
				String salaryWhere1 = "from Employee where salary > 12000";
				String salaryWhere2 = "from Employee where salary > 3000 and salary < 10000";
				String salaryWhere3 = "from Employee where salary is null";
				String nameWhere1 = "from Employee where lastName in ('Hutton', 'Errazuriz', 'Wiœniewski')";
				
				Query query =  session.createQuery(nameWhere1);
				
				List<Employee> list = query.getResultList();
			
				session.getTransaction().commit();
				
				for (Employee item: list) {
					System.out.println(item);
				}
				
				
				// Zamkniêcie obiektu SessionFactory
				factory.close();
	}

}

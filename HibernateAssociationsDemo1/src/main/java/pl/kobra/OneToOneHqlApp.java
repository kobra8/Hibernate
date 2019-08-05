package pl.kobra;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;

public class OneToOneHqlApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		// Wczytanie pliku konfiguracyjnego hibernate.cfg
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klasy Employee
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		// Stworzenie obiektu Session factory
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();

		String select = "select c from Company c";
		String where = " select c from Company c join c.companyDetail cd where cd.residence='Italy'";
		String sum = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";
		String orderBy = "select c.name from CompanyDetail cd join cd.company c where cd.employeeNumber < 35000 order by c.value";
		
		session.beginTransaction();
	
		Query query = session.createQuery(sum);
		Query query2 = session.createQuery(orderBy);
		
		Long result = (Long) query.getSingleResult();
		List<String> resultList = query2.getResultList();	
		
		System.out.println("Wartoœæ wszystkich frim polskich w bazie to: " + result);
		for(String c : resultList) {
			System.out.println(c);
		}
		
		session.getTransaction().commit();
		
		
	
		// Zamkniêcie obiektu SessionFactory
		factory.close();
	}

}

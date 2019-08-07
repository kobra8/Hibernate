package pl.kobra;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Property;

public class OneToManyHqlApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klas
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
			
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		String getCompany = "select c.name from Property p join p.company c where p.city='Sevilla'";
		String getCompany2 = "select c.name from Property p join p.company c join c.companyDetail d where p.city='barcelona' and d.residence='Switzerland'";
		String getCompany3 = "select c.name from Company c where size(c.properties) > 4";
		
		session.beginTransaction();
		
		Query query = session.createQuery(getCompany3);
		
		List<String> resultList = query.getResultList();
		
		for (String result : resultList) {
			System.out.println(result);
		}
			
		session.getTransaction().commit();
		
		factory.close();
	}

}

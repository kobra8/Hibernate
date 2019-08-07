package pl.kobra;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Property;

public class OneToManyDeleteApp {

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

		String getCompany = "select c from Company c where c.name='PZU'";
		
		session.beginTransaction();
		
		Query query = session.createQuery(getCompany);
		
		// Pierwszy sposób na usuwanie to iterowanie i szukanie warunku - tutaj miasto
//		Company company = (Company) query.getSingleResult();	
//		for(Property property: company.getProperties()) {
//			if("Kraków".equals(property.getCity())) {
//				session.delete(property);
//			}
//		}
		
		// Drugi sposób na usuwanie to po id property
		int idToDelete = 2;
		Property property = session.get(Property.class, idToDelete);
		session.delete(property);
			
		session.getTransaction().commit();
		
		factory.close();
	}

}

package pl.kobra;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Property;

public class OneToManyGetApp {

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

		String getCompany = "select c from Company c where c.name='Tesco'";
		
		session.beginTransaction();
		
		Query query = session.createQuery(getCompany);
		Company company = (Company) query.getSingleResult();
		System.out.println(company);
		
		for(Property property: company.getProperties()) {
			System.out.println(property);
		}
			
		session.getTransaction().commit();
		
		factory.close();

	}

}

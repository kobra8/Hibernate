package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;

public class CascadeRemoveApp {

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
		// Rozpoczêcie transakcji
		session.beginTransaction();
	
		Company company = session.get(Company.class, 8);
		
		session.remove(company);
		session.refresh(company);
	
		session.getTransaction().commit();
	
		// Zamkniêcie obiektu SessionFactory
		factory.close();
	}

}

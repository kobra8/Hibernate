package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Property;

public class CloseSessionApp {

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

		int id = 30;
		
		session.beginTransaction();
		
		System.out.println("Pobieranie obiektu company");
		Company company = session.get(Company.class, id);
		System.out.println("Pobrano opbiekt company");
		System.out.println(company);
		
		System.out.println("Jaki� czas dzia�ania programu . . .");	
			
		session.getTransaction().commit(); // Po zamkni�ciu sesji obiekt company jest od��czany
		
		// Pobranie typu LAZY dla property nie uda si� bo sesja zosta�a ju� zamkni�ta
		// Mozna otworzy� wtedy now� sesj� ale aby company by�o dost�pne robimy merge do sesji na tym obiekcie
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		Company mergedCompany = (Company) session.merge(company);
		
		System.out.println("Nieruchomo�ci pobrane w spos�b LAZY ale w nowej sesji:");
		for(Property property: mergedCompany.getProperties()) {
			System.out.println(property);
		}
		
		session.getTransaction().commit();
		
		factory.close();
	}

}

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
		
		System.out.println("Jakiœ czas dzia³ania programu . . .");	
			
		session.getTransaction().commit(); // Po zamkniêciu sesji obiekt company jest od³¹czany
		
		// Pobranie typu LAZY dla property nie uda siê bo sesja zosta³a ju¿ zamkniêta
		// Mozna otworzyæ wtedy now¹ sesjê ale aby company by³o dostêpne robimy merge do sesji na tym obiekcie
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		Company mergedCompany = (Company) session.merge(company);
		
		System.out.println("Nieruchomoœci pobrane w sposób LAZY ale w nowej sesji:");
		for(Property property: mergedCompany.getProperties()) {
			System.out.println(property);
		}
		
		session.getTransaction().commit();
		
		factory.close();
	}

}

package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;

public class BidirectionalApp {

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

		Company company = new Company("PZU", 50000);
		CompanyDetail companyDetail = new CompanyDetail("Polska", 15655);
		companyDetail.setCompany(company);
		company.setCompanyDetail(companyDetail);
		
		CompanyDetail companyDetail2 = session.get(CompanyDetail.class, 9);

		
		session.beginTransaction();
		
		session.persist(companyDetail); // Po zdefiniowaniu relacji dwukierunkowej zapis detali powoduje te¿ zapis obiektu nadrzêdnego
		
		session.getTransaction().commit();
		
		
		// System.out.println(companyDetail2.getCompany()); // Tutaj poprzez obiekt companyDetail pobieramy obiekt nadrzêdny dziêki zdefiniowanej relacji
	
		// Zamkniêcie obiektu SessionFactory
		factory.close();
	}

}

package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;

public class OneToOneApp {

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
	
		Company company = new Company("Pepsi", 10000000);
		CompanyDetail companyDetail = new CompanyDetail("USA", 10500);
		company.setCompanyDetail(companyDetail); // Przypisanie de encji obiektu companyDetail
		session.save(companyDetail);
		session.save(company);
	
		session.getTransaction().commit();
	
		// Zamkniêcie obiektu SessionFactory
		factory.close();

	}

}

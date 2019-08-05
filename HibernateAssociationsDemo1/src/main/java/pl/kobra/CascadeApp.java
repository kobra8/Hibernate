package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;

public class CascadeApp {

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
	
		Company company = new Company("PKP", 6000000);
		CompanyDetail companyDetail = new CompanyDetail("Polska", 125012);
		company.setCompanyDetail(companyDetail); // Przypisanie de encji obiektu companyDetail
		session.persist(company); // Zamiast save perist zapisuje kaskadowo powi¹zane obiekty
	
		session.getTransaction().commit();
	
		// Zamkniêcie obiektu SessionFactory
		factory.close();

	}

}

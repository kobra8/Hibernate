package pl.kobra;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Employee;
import pl.kobra.entity.Property;
import pl.kobra.entity.Training;

public class ManyToManyGetApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klas
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
		conf.addAnnotatedClass(Employee.class);
		conf.addAnnotatedClass(Training.class);
			
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
// Stworzenie nowego szkolenia i dodanie pobranych poracowników	- potem zapis	
//		Training training = new Training("Java training");
//		Employee employee1 = session.get(Employee.class, 119);
//		Employee employee2 = session.get(Employee.class, 120);
//		
//		training.addEmployee(employee1);
//		training.addEmployee(employee2);		
//		session.persist(training);
		
		String getTraining = "from Training";
		Query query = session.createQuery(getTraining);
		List<Training> resultList = query.getResultList();

		for(Training training: resultList) {
			System.out.println("Szkolenie: \n" + training);
			for (Employee employee: training.getEmployees()) {
				System.out.println("Pracownik: " + employee);
			}
		}
		
//		Training training = session.get(Training.class, 1);
//		System.out.println(training);
//		
//		for (Employee employee : training.getEmployees()) {
//			System.out.println("Pracownik: " + employee);
//		}
			
		session.getTransaction().commit();
		
		factory.close();
	}

}

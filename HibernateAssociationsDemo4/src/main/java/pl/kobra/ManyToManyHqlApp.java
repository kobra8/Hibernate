package pl.kobra;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Employee;
import pl.kobra.entity.Property;
import pl.kobra.entity.Training;

public class ManyToManyHqlApp {

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
		
		int minEmployeeNumber = 6;
		String course = "javascript";
		int trainingNumber = 1;
		int maxSalary = 12000;
		
		String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber"; // Szkolenia z wiêcej ni¿ 5 osobami
		String getEmployee = "select e from Employee e join e.trainings t where t.name =:course"; // Osoby które bior¹ udzia³ w szkoleniu javascript
		String getEmployeeMore = "select e from Employee e where size(e.trainings)= :trainingNumber and e.salary < :maxSalary"; // Osoby które bior¹ udzia³ tylko w 1 szkoleniu i zarabiaj¹ mniej ni¿ 12000
		
		Query query = session.createQuery(getTraining);
		query.setParameter("minEmployeeNumber", minEmployeeNumber);
		
		Query query2 = session.createQuery(getEmployee);
		query2.setParameter("course", course);

		Query query3 = session.createQuery(getEmployeeMore);
		query3.setParameter("trainingNumber", trainingNumber);
		query3.setParameter("maxSalary", maxSalary);
		
		List<Training> trainings = query.getResultList();
		List<Employee> employees = query2.getResultList();
		List<Employee> employeesMore = query3.getResultList();
		
		System.out.println("Szkolenia z wiêcej ni¿ 5 osobami");
		for(Training training: trainings) {	
			System.out.println(training);
		}
		
		System.out.println("Osoby które bior¹ udzia³ w szkoleniu javascript");
		for (Employee employee: employees) {
			System.out.println(employee);
		}
		
		System.out.println("Osoby które bior¹ udzia³ tylko w 1 szkoleniu i zarabiaj¹ mniej ni¿ 12000");
		for (Employee employee: employeesMore) {
			System.out.println(employee);
		}
			
		session.getTransaction().commit();
		
	}

}

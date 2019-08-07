package pl.kobra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.kobra.entity.Company;
import pl.kobra.entity.CompanyDetail;
import pl.kobra.entity.Department;
import pl.kobra.entity.Property;

public class OneToManyUniDeleteApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");	
		// Wczytanie adnotacji klas
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
			
		SessionFactory factory = conf.buildSessionFactory();
		// Pobieranie sesji
		Session session = factory.getCurrentSession();
		
		int id = 9;
		int idHr = 7;
		int idCompany = 31;
		String departmentNameToDelete = "HR";
		session.beginTransaction();
		
		// Usuwanie spos�b 1 - po id obiektu department
		Department department = session.get(Department.class, id);
		session.delete(department);
		
		// Usuwanie spos�b 2 - poprzez obiekt company
		Company company = session.get(Company.class, idCompany);
		for(Department item: company.getDepartments()) {
			if (department.getName().equals(departmentNameToDelete)) {
				company.getDepartments().remove(item); // Wywo�ujemy usuni�cie obiektu department z encji company aby si� nie przwr�ci�a przy zamykaniu transakcji
				session.delete(item); // Samo usuni�cie obiektu w encji department nie wystarczy bo si� zn�w przywr�ci z encji company wi�c trzeba wykona� linijk� powy�ej
			}
		}
		
		// Usuwanie spos�b 3 HQL
		String deleteQuery = "delete Department d where d.idDepartment=:idDepartment";
		Query query = session.createQuery(deleteQuery);
		query.setParameter("idDepartment", idHr);
		int deletedRows = query.executeUpdate();
		System.out.println("Ilko�� usni�tych rekord�w: " + deletedRows);
					
		session.getTransaction().commit();
		
		factory.close();
	}

}

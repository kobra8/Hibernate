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
		
		// Usuwanie sposób 1 - po id obiektu department
		Department department = session.get(Department.class, id);
		session.delete(department);
		
		// Usuwanie sposób 2 - poprzez obiekt company
		Company company = session.get(Company.class, idCompany);
		for(Department item: company.getDepartments()) {
			if (department.getName().equals(departmentNameToDelete)) {
				company.getDepartments().remove(item); // Wywo³ujemy usuniêcie obiektu department z encji company aby siê nie przwróci³a przy zamykaniu transakcji
				session.delete(item); // Samo usuniêcie obiektu w encji department nie wystarczy bo siê znów przywróci z encji company wiêc trzeba wykonaæ linijkê powy¿ej
			}
		}
		
		// Usuwanie sposób 3 HQL
		String deleteQuery = "delete Department d where d.idDepartment=:idDepartment";
		Query query = session.createQuery(deleteQuery);
		query.setParameter("idDepartment", idHr);
		int deletedRows = query.executeUpdate();
		System.out.println("Ilkoœæ usniêtych rekordów: " + deletedRows);
					
		session.getTransaction().commit();
		
		factory.close();
	}

}

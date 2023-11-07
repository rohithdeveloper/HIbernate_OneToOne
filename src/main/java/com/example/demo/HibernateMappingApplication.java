package com.example.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Address;
import com.example.demo.entity.Person;

import jakarta.persistence.Query;

@SpringBootApplication
public class HibernateMappingApplication {

	public static void main(String[] args) {
		// SpringApplication.run(HibernateMappingApplication.class, args);

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		Person p1 = new Person();

		p1.setFirstName("Rohith");
		p1.setLastName("Parimella");

		Address a1 = new Address();
		a1.setStreet("church street");
		a1.setCity("Hanmakonda");
		a1.setZipCode("506172");

		p1.setAddress(a1);
		a1.setPerson(p1);

		session.save(p1);
		transaction.commit();
		System.out.println("success");
//		System.out.println("Person Details:"+ p1.getFirstName()+" "+ p1.getLastName());
//		System.out.println("Address Details:"+a1.getStreet()+" "+ a1.getCity()+" "+ a1.getZipCode());

		session.close();

		session=sessionFactory.openSession();
		Transaction transaction2 = session.beginTransaction();
		Query query = session.createQuery("FROM Person", Person.class);
		List<Person> persons = query.getResultList();
		for (Person p : persons) {
			System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.getAddress());
		}
		transaction2.commit();
		session.close();
	}

}

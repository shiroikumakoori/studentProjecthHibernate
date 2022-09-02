package clinic.clinicProject;

import java.util.ArrayList;

import org.Entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
	private static SessionFactory factory;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
			 factory = new Configuration().configure("resource/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Failed to create Session Instance");
			throw new ExceptionInInitializerError(e);
		}
    	
    	Session session = factory.openSession();
    	Transaction tx = session.beginTransaction();
    	

    	PatientData p1 = new PatientData();
    	
    	p1.setAge(22);
    	p1.setFirstName("jack");
    	p1.setLastName("no");
    	   	
    	ClinicalDataHeight p1d1 = new ClinicalDataHeight();

    	p1d1.setComponetName("weight");
    	p1d1.setComponetValue("50kg");
    	p1d1.setMeasuredDateTime("10:00");
    	
    	
    	PatientData p2 = new PatientData();

    	p1.setAge(50);
    	p1.setFirstName("may");
    	p1.setLastName("yes");
    	
    	ClinicalDataHeight p2d1 = new ClinicalDataHeight();

    	p2d1.setComponetName("weight");
    	p2d1.setComponetValue("500kg");
    	p2d1.setMeasuredDateTime("11:00");
    	p2d1.setPatientId(p2);
  
    	p1.setClinicObj(p1d1);
    	p1d1.setPatientId(p1);
    	p2.setClinicObj(p2d1);
    	p2d1.setPatientId(p2);
    	
      	session.persist(p1);
      	session.persist(p2);

    	
    	tx.commit();
    	session.close();
    	System.out.println("Success");
    	
    	
    }
}

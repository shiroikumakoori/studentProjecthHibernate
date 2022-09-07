package clinic.clinicProject;

import java.util.ArrayList;
import java.util.List;

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
    	p1.setClinicObj(new ArrayList<ClinicalDataHeight>());
    	   	
    	ClinicalDataHeight p1d1 = new ClinicalDataHeight();
    	ClinicalDataHeight p1d2 = new ClinicalDataHeight();

    	p1d1.setComponetName("weight");
    	p1d1.setComponetValue("50kg");
    	p1d1.setMeasuredDateTime("10:00");
    	
    	p1d2.setComponetName("height");
    	p1d2.setComponetValue("100");
    	p1d2.setMeasuredDateTime("10:10");
    	
    	
    	PatientData p2 = new PatientData();

    	p2.setAge(50);
    	p2.setFirstName("may");
    	p2.setLastName("yes");
    	p2.setClinicObj(new ArrayList<ClinicalDataHeight>());
    	
    	ClinicalDataHeight p2d1 = new ClinicalDataHeight();
    	ClinicalDataHeight p2d2 = new ClinicalDataHeight();

    	p2d1.setComponetName("weight");
    	p2d1.setComponetValue("500kg");
    	p2d1.setMeasuredDateTime("11:00");
    	
    	p2d2.setComponetName("height");
    	p2d2.setComponetValue("1000");
    	p2d2.setMeasuredDateTime("11:10");   	

  
    	p1.getClinicObj().add(p1d1);
      	p1.getClinicObj().add(p1d2);
      	p2.getClinicObj().add(p2d1);
      	p2.getClinicObj().add(p2d2);
      	
//    	p1d1.setPatientId(p1);
//    	p1d1.setPatientId(p1);
//
//    	p2d1.setPatientId(p2);
//    	p2d1.setPatientId(p2);
    	
      	session.persist(p1);
      	session.persist(p2);

    	
    	tx.commit();
    	session.close();
    	System.out.println("Success");
    	
    	
    }
}

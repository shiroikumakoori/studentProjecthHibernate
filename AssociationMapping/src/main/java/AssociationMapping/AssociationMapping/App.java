package AssociationMapping.AssociationMapping;


import java.util.ArrayList;

import org.dxc.entity.*;
import org.hibernate.*;
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
    	
    	Answer ans1 = new Answer();
    	ans1.setAnswername("Java is a programming lanaguage");
    	ans1.setPostedBy("Kim");
    	
    	Answer ans2 = new Answer();
    	ans2.setAnswername("Java is a platform");
    	ans2.setPostedBy("Sam");
    	
    	Answer ans3 = new Answer();
    	ans2.setAnswername("Servlet is an interface");
    	ans2.setPostedBy("Caryl");
    	
    	Answer ans4 = new Answer();
    	ans2.setAnswername("Servlet is an API");
    	ans2.setPostedBy("Almond");
    	
    	ArrayList<Answer> list1 = new ArrayList<Answer>();
    	list1.add(ans1);
    	list1.add(ans2);
    	
    	ArrayList<Answer> list2 = new ArrayList<Answer>();
    	list2.add(ans3);
    	list2.add(ans4);
    	
    	Question qn1 = new Question();
    	qn1.setQname("What is Java");
    	qn1.setAnswer(list1);
    	
    	Question qn2 = new Question();
    	qn1.setQname("What is Servlet");
    	qn1.setAnswer(list2);
    	
    	session.persist(qn1);
    	session.persist(qn2);
    	
    	tx.commit();
    	session.close();
    	System.out.println("Success");
    }
}

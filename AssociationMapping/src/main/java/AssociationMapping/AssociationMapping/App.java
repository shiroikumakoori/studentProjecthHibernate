package AssociationMapping.AssociationMapping;


import java.util.ArrayList;
import java.util.List;

import org.dxc.ManyToMany.Entity.AnswerMany;
import org.dxc.ManyToMany.Entity.QuestionMany;
import org.dxc.ManyToOne.Entity.Address;
import org.dxc.ManyToOne.Entity.FamilyMember;
import org.dxc.OneToOne.Entity.*;
import org.dxc.entity.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import net.bytebuddy.implementation.bytecode.Addition;



/**
 * Hello world!
 *
 */

public class App 
{
	private static SessionFactory factory;
	static void oneToOne(Session session, Transaction tx)
	{
		Wife w1 = new Wife();
		Husband h1 = new Husband();
		
		w1.setName("Mary");
		h1.setName("Mick");
		
		w1.setHusband(h1);
		h1.setWife(w1);;;
		
//		Wife w2 = new Wife();
//		Husband h2 = new Husband();
//		
//		w2.setName("Alice");
//		h2.setName("Goose");
//		w2.setHusband(h2);
//		h2.setWife(w2);
		
		
		session.persist(h1);
//		session.persist(h2);
	}
	static void  manyToOne(Session session, Transaction tx)
	{
		FamilyMember father = new FamilyMember();
		FamilyMember mother = new FamilyMember();
		FamilyMember son = new FamilyMember();
		FamilyMember daughter = new FamilyMember();
		
		Address addr  = new Address();
		
		addr.setAddress("Woodlands street 1000");
		addr.setPostalCode(123000);
		
		father.setName("Tan Great father");
		mother.setName("Tan maybe mother");
		son.setName("Tan the great VII");
		daughter.setName("Tan the great priest ");
		
		father.setAddressJoin(addr);
		mother.setAddressJoin(addr);
		son.setAddressJoin(addr);
		daughter.setAddressJoin(addr);
		
	
		session.persist(father);
		session.persist(mother);
		session.persist(son);
		session.persist(daughter);
		
		
	}
    static void oneToMany(Session session, Transaction tx)
    {

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
    }
	static void manyToMany(Session session, Transaction tx)
	{
	    AnswerMany an1=new AnswerMany();  
	    an1.setAnswername("Java is a programming language");  
	    an1.setPostedBy("Ravi Malik");  
	      
	    AnswerMany an2=new AnswerMany();  
	    an2.setAnswername("Java is a platform");  
	    an2.setPostedBy("Sudhir Kumar");  
	     
	    QuestionMany q1=new QuestionMany();  
	    q1.setQname("What is Java?");  
	    List<AnswerMany> l1=new ArrayList<AnswerMany>();  
	    l1.add(an1);  
	    l1.add(an2);  
	    q1.setAnswers(l1);  
	      
	      
	    AnswerMany ans3=new AnswerMany();    
        ans3.setAnswername("Servlet is an Interface");    
        ans3.setPostedBy("Jai Kumar");    
            
        AnswerMany ans4=new AnswerMany();    
        ans4.setAnswername("Servlet is an API");    
        ans4.setPostedBy("Arun");    
	      
	    QuestionMany q2=new QuestionMany();  
	    q2.setQname("What is Servlet?");  
	    List<AnswerMany> l2=new ArrayList<AnswerMany>();  
	    l2.add(ans3);  
	    l2.add(ans4);  
	    q2.setAnswers(l2);  
	      
	    session.persist(q1);    
	    session.persist(q2);    
}
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
    	
//    	manyToOne(session, tx);
    	oneToOne(session,tx);
    	tx.commit();
    	session.close();
    	System.out.println("Success");
    }
}

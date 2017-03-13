package hibernate.ui.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 

public class HibernateUtil {

	
	private  SessionFactory buildSessionFactory(String xml) {
		 try {
			 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure(xml).build();
			 Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			 return metadata.getSessionFactoryBuilder().build();
		 }
		 catch (Throwable ex) {
			 System.err.println("Fallo al crear la sesion" + ex);
			 throw new ExceptionInInitializerError(ex);
		 }
	 }

	 
	 public  Session abrirSessionDropCreate() {
		 return buildSessionFactory("hibernate.cfg.xml").openSession();
	 } 
	 
	 public  Session abrirSessionActualizar() {
		 return buildSessionFactory("hibernate.actualiza.xml").openSession();
	 } 
	
	
}

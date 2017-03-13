package hibernate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.ui.mapeo.Autores;
import hibernate.ui.mapeo.AutoresLibros;
import hibernate.ui.mapeo.DepositoLegal;
import hibernate.ui.mapeo.Ejemplares;
import hibernate.ui.mapeo.Libros;
import hibernate.ui.session.HibernateUtil;

public class Main2 {
	

	private static Session session;
	private static HibernateUtil hibernateUtil = new HibernateUtil();

	public static void main(String[] args) {
		
		session =  hibernateUtil.abrirSessionActualizar();// abrirSessionDropCreate();//
	
		session.beginTransaction();
		
		
	/*	//INTRODUCIMOS DATOS EN LAS TABLAS LIBROS Y DEPOSITOLEGAL		
		Libros libro1 = creaLibro("El Silmarillion","12-123-1234-a");
		
		Libros libro2 = creaLibro("El Hobbit","12-123-1235-a");
		Libros libro3 = creaLibro("La Comunidad del Anillo","12-123-1236-a");
		Libros libro4 = creaLibro("El Señor de los Anillos","12-123-1237-a");
		Libros libro5 = creaLibro("First and Only","12-123-1234-b");
		Libros libro6 = creaLibro("Xenos","12-123-1235-b");
		Libros libro7 = creaLibro("Ravenor","12-123-1236-b");
		Libros libro8 = creaLibro("Horus, Señor de la Guerra","12-123-1237-b");
		Libros libro9 = creaLibro("Cuentos inconclusos de Númenor y la Tierra Media","12-123-1238-b");
		
		DepositoLegal dele1 = creaDepositoLegal(libro1,"123456789012345678.0");
		DepositoLegal dele2 = creaDepositoLegal(libro2,"123456789012345678.1");
		DepositoLegal dele3 = creaDepositoLegal(libro3,"123456789012345678.2");
		DepositoLegal dele4 = creaDepositoLegal(libro4,"123456789012345678.3");
		DepositoLegal dele5 = creaDepositoLegal(libro5,"123456789012345678.4");
		DepositoLegal dele7 = creaDepositoLegal(libro7,"123456789012345678.5");
		DepositoLegal dele8 = creaDepositoLegal(libro8,"123456789012345678.6");
		
		Autores autor1 = crearAutor("jrrt","j.r.r.tolkien");
		Autores autor2 = crearAutor("daab","dan abnett");
		
		AutoresLibros al1 = crearAutoresLibros(autor1, libro1) ;
		AutoresLibros al2 = crearAutoresLibros(autor1, libro2) ;
		AutoresLibros al3 = crearAutoresLibros(autor1, libro3) ;
		AutoresLibros al4 = crearAutoresLibros(autor1, libro4) ;
		AutoresLibros al9 = crearAutoresLibros(autor1, libro5) ;
		AutoresLibros al5 = crearAutoresLibros(autor2, libro5) ;
		AutoresLibros al6 = crearAutoresLibros(autor2, libro6) ;
		AutoresLibros al7 = crearAutoresLibros(autor2, libro7) ;
		AutoresLibros al8 = crearAutoresLibros(autor2, libro8) ;
	*/	
	//	Ejemplares ej  = crearEjemplares(34,"euros", libro1 );
		
		//CERRAMOS TRANSACCION
	//	sessionUpdate.getTransaction().commit();
		
	//	session.beginTransaction();
//		visualizarLibros();
	//	visualizaLibrosDepositoLegal();
	//	visualizaLibrosEjemplares();
	//  listaAutoresLibro();
		visualizarLibrosDepo();
	//	session.getTransaction().commit();
	/*	Ejemplares e = new Ejemplares();
		e.setCodEjemplar(11);
		
		session.delete(e);
	/*	sesion.delete(dele1);
	//	session.delete(libro1);
	*/	
	//	Libros libro1up = actualizaLibro(libro2,"El SilmarillionI","12-123-1234-a");
		
	
		session.getTransaction().commit();
		
	}
	
	private static Autores crearAutor(String codAutor,String nombreAutor) {
		Autores autor = new Autores();
		autor.setCodAutor(codAutor);
		autor.setNombreAutor(nombreAutor);
		session.save(autor);
		return autor;
	}
	
	private static AutoresLibros crearAutoresLibros(Autores autor,Libros libro){
		AutoresLibros al = new AutoresLibros();
		al.setCodAutor(autor);
		al.setCodLibro(libro);
		session.save(al);
		return al;
	}
	
	private static Ejemplares crearEjemplares(int i, String string, Libros j) {
		Ejemplares eje = new Ejemplares();
		eje.setImporte(i);
		eje.setTipoMoneda(string);
		eje.setCodLibro(j);
		session.save(eje);
		return eje;
	}

	public static void listaAutoresLibro() {
		session.beginTransaction();
		Query cons = session.createQuery ("from AutoresLibros a left join fetch a.codLibro");
		List<AutoresLibros> listaContactos = cons.list(); //sin left no aparecen las personas sin libros
		System.out.println("Hay " + listaContactos.size() + "autores en la base de datos");
	}
	
	private static void visualizarLibrosDepo(){
		List<Libros> l = (List<Libros>) session.createQuery ("from Libros l left join fetch l.depositoLegal").getResultList();
		for (Libros libros : l) 
			System.out.println( libros.getCodLibro() +"*"+libros.getNombreLibro() +"*"+libros.getISBN()+"*"+libros.getDepositoLegal());
	}
	
	private static void visualizarLibros(){
		List<Libros> l = (List<Libros>) session.createQuery ("from Libros").getResultList();
		for (Libros libros : l) 
			System.out.println( libros.getCodLibro() +"*"+libros.getNombreLibro() +"*"+libros.getISBN()+"*"+libros.getFechaIntro());
	}
	
	private static void visualizaLibrosDepositoLegal() {
		Query cons = session.createQuery ("from Libros  l, DepositoLegal  d where l.codLibro=d.codLibroDeposito order by l.codLibro");
		Iterator q = cons.iterate();
		System.out.println("\nVisualizar todos los libros con todos sus datos incluyendo el deposito legal el que lo tenga");
		System.out.println("--------------------------------------------------------------------------------------------");
		while (q.hasNext()) {
			Object[] par =(Object[]) q.next();
			Libros l = (Libros) par[0];
			DepositoLegal d = (DepositoLegal) par[1];
			System.out.println( l.getCodLibro() +"*"+l.getNombreLibro() +"*"+l.getISBN()+"*"+l.getFechaIntro()+"*"+d.getDepositolegal());
		}
	}

	private static void visualizaLibrosEjemplares() {
		Query cons = session.createQuery ("from Libros  l, Ejemplares  e where l.codLibro=e.codLibro order by l.codLibro");
		//Query cons = session.createQuery ("from  Ejemplares left join  e where l.codLibro=e.codLibro order by l.codLibro");
		Iterator q = cons.iterate();
		while (q.hasNext()) {
			Object[] par =(Object[]) q.next();
			Libros l = (Libros) par[0];
			Ejemplares e = (Ejemplares) par[1];
			System.out.println( l.getCodLibro() +"*"+l.getNombreLibro() +"*"+l.getISBN()+"*"+l.getFechaIntro()+"*"+e.getCodEjemplar());
		}
	}

	private static DepositoLegal creaDepositoLegal(Libros libro,String depositoLegal) {
		
		DepositoLegal dele = new DepositoLegal();
		dele.setCodLibroDeposito(libro);
		dele.setDepositolegal(depositoLegal);
		session.save(dele);
		return dele;
	}



	private static Libros creaLibro(String titulo, String isbn) {
		Libros libro = new Libros();
		libro.setNombreLibro(titulo);
		libro.setISBN(isbn);
		libro.setFechaIntro(Date.valueOf(LocalDate.now()));
		session.save(libro);
		return libro;
	}

	private static Libros actualizaLibro(Libros libro,String titulo, String isbn) {
		libro.setNombreLibro(titulo);
		libro.setISBN(isbn);
		libro.setFechaIntro(Date.valueOf(LocalDate.now()));
		session.saveOrUpdate(libro);
		return libro;
	}



	@SuppressWarnings("deprecation")
	private static List<Autores> autores() {
		List<Autores> autoresList = new ArrayList<>();
		Query q = session.createQuery("from autores");
		autoresList = q.getResultList();
		System.out.println(autoresList.toString());
		return autoresList;
	}



}

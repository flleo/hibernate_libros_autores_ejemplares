package hibernate.ui.session;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


import hibernate.ui.mapeo.Autores;
import hibernate.ui.mapeo.AutoresLibros;
import hibernate.ui.mapeo.DepositoLegal;
import hibernate.ui.mapeo.Ejemplares;
import hibernate.ui.mapeo.Libros;

public class ConsultasBD {
	
	private static Session session;
	private static HibernateUtil hibernateUtil = new HibernateUtil();
	
	public ConsultasBD(){
		session = hibernateUtil.abrirSessionActualizar();
	}
	
	public Iterator listaAutoresLibro(int codLibro) {
		session.beginTransaction();
		Query cons = session.createQuery ("from Autores a inner join AutoresLibros l on a.codAutor=l.codAutor where l.codLibro="+codLibro);
		Iterator q = cons.iterate();

		return q;
	}
	
	public void listaLibrosDepositoLegal() {
		session.beginTransaction();
		Query cons = session.createQuery ("from Libros  l, DepositoLegal  d where l.codLibro=d.codLibroDeposito order by l.codLibro");
		Iterator q = cons.iterate();
		List<DepositoLegal> depositos ;
		System.out.println("\nVisualizar todos los libros con todos sus datos incluyendo el deposito legal el que lo tenga");
		System.out.println("--------------------------------------------------------------------------------------------");
		while (q.hasNext()) {
			Object[] par =(Object[]) q.next();
			Libros l = (Libros) par[0];
			DepositoLegal d = (DepositoLegal) par[1];
			System.out.println( l.getCodLibro() +"*"+l.getNombreLibro() +"*"+l.getISBN()+"*"+l.getFechaIntro()+"*"+d.getDepositolegal());
		}
		session.getTransaction().commit();
	}

	public List<DepositoLegal> listaDepositoLegal(){
		session.beginTransaction();
		List<DepositoLegal> depositos = (List<DepositoLegal>) session.createQuery("from DepositoLegal").getResultList();
		session.getTransaction().commit();
		
		return depositos;
	}
	
	public  List<Libros> listaLibros(){
		session.beginTransaction();
		List<Libros> libros = (List<Libros>) session.createQuery ("from Libros").getResultList();
		session.getTransaction().commit();
		return libros;
		
		
	}
	

	public Iterator listaEjemplaresPorLibro() {
		session.beginTransaction();
		Query cons = session.createQuery ("from Ejemplares  e, Libros  l where l.codLibro=e.codLibro order by l.codLibro");
		Iterator q = cons.iterate();
		
		return q;
		
	}
	
	public List<Ejemplares> listaEjemplares() {
		session.beginTransaction();
		List<Ejemplares> eje = (List<Ejemplares>) session.createQuery ("from Ejemplares").getResultList();
		session.getTransaction().commit();
		return eje;
	}


	public List<Autores> listaAutores() {
		session.beginTransaction();
		List<Autores> aut = (List<Autores>) session.createQuery ("from Autores").getResultList();
		session.getTransaction().commit();
		return aut;
	}
	
	public DepositoLegal creaActualizaDepositoLegal(Libros libro,String depositoLegal) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		DepositoLegal dele = new DepositoLegal();
		dele.setCodLibroDeposito(libro);
		dele.setDepositolegal(depositoLegal);
		session.saveOrUpdate(dele);
		session.getTransaction().commit();
		return dele;
	}



	public Libros creaLibro(String titulo, String isbn) {
		session.beginTransaction();
		Libros libro = new Libros();
		libro.setNombreLibro(titulo);
		libro.setISBN(isbn);
		libro.setFechaIntro(Date.valueOf(LocalDate.now()));
		session.save(libro);
		session.getTransaction().commit();
		return libro;
	}
	
	public AutoresLibros creaAutorLibro(Autores autor, Libros libro) {
		session.beginTransaction();
		AutoresLibros al = new AutoresLibros();
		al.setCodAutor(autor);
		al.setCodLibro(libro);
		session.save(al);
		session.getTransaction().commit();
		return al;
		
	}

	public Libros actualizaLibro(Libros libro,String titulo, String isbn) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		libro.setNombreLibro(titulo);
		libro.setISBN(isbn);
		libro.setFechaIntro(Date.valueOf(LocalDate.now()));
		session.saveOrUpdate(libro);
		session.getTransaction().commit();
		return libro;
	}
	

	public void eliminaLibro(Libros libro) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		session.delete(libro);
		session.getTransaction().commit();
	}

	public void eliminaEjemplar(Ejemplares ejemplar) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		session.delete(ejemplar);
		session.getTransaction().commit();
	}

	public void eliminaDepositoLegal(DepositoLegal de) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		session.delete(de);
		session.getTransaction().commit();
	}

	public void eliminaAutorLibro(AutoresLibros a) {
		session = hibernateUtil.abrirSessionActualizar();
		session.beginTransaction();
		session.delete(a);
		session.getTransaction().commit();
	}


	public static Session getSession() {
		return session;
	}



}

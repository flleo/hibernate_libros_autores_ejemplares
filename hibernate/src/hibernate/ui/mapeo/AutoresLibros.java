package hibernate.ui.mapeo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="autoreslibros")
public class AutoresLibros implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="codAutor") 
	private Autores codAutor;
	
	@Id
	@ManyToOne
	@JoinColumn(name="codLibro") 
	private Libros codLibro;


	public Libros getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(Libros codLibro) {
		this.codLibro = codLibro;
	}

	public Autores getCodAutor() {
		return codAutor;
	}

	public void setCodAutor(Autores codAutor) {
		this.codAutor = codAutor;
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package hibernate.ui.mapeo;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class LibrosDepositoLegal {

	@Id
	@OneToOne
	@JoinColumn(name="libros") 
	private Libros codLibro;
	
	@Id
	@OneToOne
	@JoinColumn(name="depositolegal") 
	private DepositoLegal codLibroDeposito;
	
	
	
	
	
	
	
	

	public Libros getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(Libros codLibro) {
		this.codLibro = codLibro;
	}

	public DepositoLegal getCodLibroDeposito() {
		return codLibroDeposito;
	}

	public void setCodLibroDeposito(DepositoLegal codLibroDeposito) {
		this.codLibroDeposito = codLibroDeposito;
	}
	
	
	
	
	
	
	
}

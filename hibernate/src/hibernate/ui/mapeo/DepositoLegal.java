package hibernate.ui.mapeo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="depositolegal")
public class DepositoLegal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	@JoinColumn(name="codLibroDeposito") 
	private Libros codLibroDeposito;
	
	@Column(columnDefinition= "char(20)")
	private String depositolegal;
	
	
	
	public Libros getCodLibroDeposito() {
		return codLibroDeposito;
	}
	public void setCodLibroDeposito(Libros codLibroDeposito) {
		this.codLibroDeposito = codLibroDeposito;
	}
	public String getDepositolegal() {
		return depositolegal;
	}
	public void setDepositolegal(String depositolegal) {
		this.depositolegal = depositolegal;
	}
	
	
	
	
	
}

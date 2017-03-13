package hibernate.ui.mapeo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table(name="libros")
public class Libros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codLibro;
	
	@Column(columnDefinition= "varchar(100)")
	private String nombreLibro;	
	
	@Column(columnDefinition= "varchar(20)")
	private String ISBN;	
	
	@Temporal(TemporalType.DATE)
	private Date fechaIntro;
	
	//Permite un libro sin depositolegal, pero no al reves
	@OneToOne (cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@PrimaryKeyJoinColumn //no crea el campo depositoLegal
	private DepositoLegal  depositoLegal; 
	
	
	
	
	
	
	
	public int getCodLibro() {
		return codLibro;
	}
	
	public void setCodLibro(int codLibro) {
		this.codLibro = codLibro;
	}
	public String getNombreLibro() {
		return nombreLibro;
	}
	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public Date getFechaIntro() {
		return fechaIntro;
	}
	public void setFechaIntro(Date fechaIntro) {
		this.fechaIntro = fechaIntro;
	}

	public DepositoLegal getDepositoLegal() {
		return depositoLegal;
	}

	public void setDepositoLegal(DepositoLegal depositoLegal) {
		this.depositoLegal = depositoLegal;
	}
	
	
	
}

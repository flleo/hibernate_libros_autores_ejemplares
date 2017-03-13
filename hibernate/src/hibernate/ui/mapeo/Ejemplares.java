package hibernate.ui.mapeo;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity 
@Table(name="ejemplares")
public class Ejemplares implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codEjemplar;
	
	@ManyToOne
	@JoinColumn(name="codLibro") 
	private Libros codLibro;
	
	@Column(columnDefinition= "decimal(5,2)")
	private double importe;
	
	@Column(columnDefinition= "varchar(20)")
	private String tipoMoneda;
	
	
	
	

	public int getCodEjemplar() {
		return codEjemplar;
	}

	public void setCodEjemplar(int codEjemplar) {
		this.codEjemplar = codEjemplar;
	}

	public Libros getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(Libros codLibro) {
		this.codLibro = codLibro;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	
	
	
}

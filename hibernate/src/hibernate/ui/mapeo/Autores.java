package hibernate.ui.mapeo;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="autores")
public class Autores implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (columnDefinition= "char(4)")
	private String codAutor;
	
	@Column(columnDefinition= "varchar(30)")
	private String nombreAutor;
	
	
	
	
	
	
	
	
	public String getCodAutor() {
		return codAutor;
	}
	public void setCodAutor(String codAutor) {
		this.codAutor = codAutor;
	}
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	
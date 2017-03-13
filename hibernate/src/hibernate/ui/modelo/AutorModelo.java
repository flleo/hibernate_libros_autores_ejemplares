package hibernate.ui.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AutorModelo {

	private StringProperty codAutor;
	private StringProperty nombreAutor;
	
	public AutorModelo(){
		codAutor = new SimpleStringProperty();
		nombreAutor = new SimpleStringProperty();
	}

	public final StringProperty codAutorProperty() {
		return this.codAutor;
	}
	

	public final String getCodAutor() {
		return this.codAutorProperty().get();
	}
	

	public final void setCodAutor(final String codAutor) {
		this.codAutorProperty().set(codAutor);
	}
	

	public final StringProperty nombreAutorProperty() {
		return this.nombreAutor;
	}
	

	public final String getNombreAutor() {
		return this.nombreAutorProperty().get();
	}
	

	public final void setNombreAutor(final String nombreAutor) {
		this.nombreAutorProperty().set(nombreAutor);
	}
	
	
	
}

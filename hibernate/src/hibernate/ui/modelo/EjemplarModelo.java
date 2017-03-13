package hibernate.ui.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EjemplarModelo {

	private IntegerProperty codEjemplar;
	private DoubleProperty importe;
	private StringProperty tipoMoneda;
	private IntegerProperty codLibro;
	
	public EjemplarModelo(){
		codEjemplar = new SimpleIntegerProperty(this, "codEjemplar");
		importe = new SimpleDoubleProperty(this,"importe");
		tipoMoneda = new SimpleStringProperty(this,"tipoMoneda");
		codLibro = new SimpleIntegerProperty(this,"codLibro");
		
	}

	public final IntegerProperty codEjemplarProperty() {
		return this.codEjemplar;
	}
	

	public final int getCodEjemplar() {
		return this.codEjemplarProperty().get();
	}
	

	public final void setCodEjemplar(final int codEjemplar) {
		this.codEjemplarProperty().set(codEjemplar);
	}
	

	public final DoubleProperty importeProperty() {
		return this.importe;
	}
	

	public final double getImporte() {
		return this.importeProperty().get();
	}
	

	public final void setImporte(final double importe) {
		this.importeProperty().set(importe);
	}
	

	public final StringProperty tipoMonedaProperty() {
		return this.tipoMoneda;
	}
	

	public final String getTipoMoneda() {
		return this.tipoMonedaProperty().get();
	}
	

	public final void setTipoMoneda(final String tipoMoneda) {
		this.tipoMonedaProperty().set(tipoMoneda);
	}
	

	public final IntegerProperty codLibroProperty() {
		return this.codLibro;
	}
	

	public final int getCodLibro() {
		return this.codLibroProperty().get();
	}
	

	public final void setCodLibro(final int codLibro) {
		this.codLibroProperty().set(codLibro);
	}
	
	
	
}

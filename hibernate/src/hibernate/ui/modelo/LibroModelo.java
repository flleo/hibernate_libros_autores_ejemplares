package hibernate.ui.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibroModelo {

	private IntegerProperty codLibro;
	private StringProperty ISBN;
	private ObjectProperty<LocalDate> fechaIntro;
	private StringProperty nombreLibro;
	
	public LibroModelo(){
		codLibro = new SimpleIntegerProperty(this, "codLibro");
		ISBN = new SimpleStringProperty(this, "ISBN");
		fechaIntro = new SimpleObjectProperty<>(this, "fechaIntro");
		nombreLibro  = new SimpleStringProperty(this, "nombreLibro");
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
	

	public final StringProperty ISBNProperty() {
		return this.ISBN;
	}
	

	public final String getISBN() {
		return this.ISBNProperty().get();
	}
	

	public final void setISBN(final String ISBN) {
		this.ISBNProperty().set(ISBN);
	}
	


	public final StringProperty nombreLibroProperty() {
		return this.nombreLibro;
	}
	

	public final String getNombreLibro() {
		return this.nombreLibroProperty().get();
	}
	

	public final void setNombreLibro(final String nombreLibro) {
		this.nombreLibroProperty().set(nombreLibro);
	}

	public final ObjectProperty<LocalDate> fechaIntroProperty() {
		return this.fechaIntro;
	}
	

	public final LocalDate getFechaIntro() {
		return this.fechaIntroProperty().get();
	}
	

	public final void setFechaIntro(final LocalDate fechaIntro) {
		this.fechaIntroProperty().set(fechaIntro);
	}
	
	
	
	
}

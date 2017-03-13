package hibernate.ui.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DepositoLegalModelo {

	private IntegerProperty codLibroDeposito;
	private StringProperty depositoLegal;
	
	public DepositoLegalModelo(){
		codLibroDeposito = new SimpleIntegerProperty(this,"codLibroDeposito");
		depositoLegal = new SimpleStringProperty(this,"depositoLegal");
		
	}

	public final IntegerProperty codLibroDepositoProperty() {
		return this.codLibroDeposito;
	}
	

	public final int getCodLibroDeposito() {
		return this.codLibroDepositoProperty().get();
	}
	

	public final void setCodLibroDeposito(final int codLibroDeposito) {
		this.codLibroDepositoProperty().set(codLibroDeposito);
	}
	

	public final StringProperty depositoLegalProperty() {
		return this.depositoLegal;
	}
	

	public final String getDepositoLegal() {
		return this.depositoLegalProperty().get();
	}
	

	public final void setDepositoLegal(final String depositoLegal) {
		this.depositoLegalProperty().set(depositoLegal);
	}
	
	
	
}

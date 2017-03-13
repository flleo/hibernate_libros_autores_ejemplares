package hibernate.ui.libro;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hibernate.ui.HibernateApp;
import hibernate.ui.mapeo.DepositoLegal;
import hibernate.ui.mapeo.Libros;
import hibernate.ui.modelo.DepositoLegalModelo;
import hibernate.ui.modelo.LibroModelo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LibroController implements Initializable{
	
	  	
		@FXML
	    private TextField isbnTField;

	    @FXML
	    private TextField tituloTField;


	    @FXML
	    private TextField depositoTField;

	
	    @FXML
	    private Button grabarLibroButton;

	    @FXML
	    private Button actualizarLibroButton;
	    
	    private HibernateApp app;
	    private GridPane view;
	    private LibroModelo nuevoLibroModelo = new LibroModelo();
	    private Libros libro = new Libros();
	    private DepositoLegalModelo depositoLegalModelo = new DepositoLegalModelo();
	    private DepositoLegal d = new DepositoLegal();
	
	
	public LibroController(HibernateApp app){
		this.app  = app;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LibroView.fxml"));
			loader.setController(this); 
			view = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
    @FXML
    void insertarLibroOnAction(ActionEvent event) {
    	
    	if(!nuevoLibroModelo.getNombreLibro().equals("") && !nuevoLibroModelo.getISBN().equals("")){
    		if(depositoLegalModelo.getDepositoLegal().equals("")) depositoLegalModelo.setDepositoLegal("0");
	    	Libros l = app.getControl().getConsultasBD().creaLibro(nuevoLibroModelo.getNombreLibro(), nuevoLibroModelo.getISBN());
	    	if(l!=null){
	    		nuevoLibroModelo.setCodLibro(l.getCodLibro());
	    		depositoLegalModelo.setCodLibroDeposito(l.getCodLibro());
	    		app.getControl().getLibrosProperty().add(nuevoLibroModelo);
	    		int n = 0;
	    		DepositoLegal d = app.getControl().getConsultasBD().creaActualizaDepositoLegal(l, depositoLegalModelo.getDepositoLegal());
				if(d==null)	n=1;
				else app.getControl().getDepositosProperty().add(depositoLegalModelo);	
    			 
	    		if(n==0){
				app.getControl().getVista().setDisable(false);
    			app.getControl().getLibroStage().close();	
	    		}
	    		else	app.error("Mensaje de error", "Ha ocurrido un error al grabar el deposito legal.");
	    	}
	    	else	app.error("Mensaje de error", "El libro no se ha grabado, revise sus datos.");
    	}
    	else{
    		app.error("Mensaje de error", "Rellene todos los datos.");
    		app.getControl().getLibroStage().toFront();
    	}
    }
	

    @FXML
    void actualizarLibroOnAction(ActionEvent event) {
    	if(!nuevoLibroModelo.getNombreLibro().equals("") && !nuevoLibroModelo.getISBN().equals("")){
    		if(depositoLegalModelo.getDepositoLegal().equals("")) {
    			unbindModelo();
    			depositoLegalModelo.setDepositoLegal("0");
    		}
	    	Libros l = app.getControl().getConsultasBD().actualizaLibro(libro,nuevoLibroModelo.getNombreLibro(), nuevoLibroModelo.getISBN());
	    	if(l!=null){
	    		depositoLegalModelo.setCodLibroDeposito(l.getCodLibro());
	    		app.getControl().getLibrosProperty().remove(nuevoLibroModelo);
	    		app.getControl().getLibrosProperty().add(nuevoLibroModelo);
	    		int n = 0;
	    		DepositoLegal d = app.getControl().getConsultasBD().creaActualizaDepositoLegal(l, depositoLegalModelo.getDepositoLegal());
				if(d==null)	n=1;
				else {
					app.getControl().getDepositosProperty().remove(depositoLegalModelo);
					app.getControl().getDepositosProperty().add(depositoLegalModelo);	
				}
    			 
	    		if(n==0){
				app.getControl().getVista().setDisable(false);
    			app.getControl().getLibroStage().close();	
	    			
	    		}
	    		else	app.error("Mensaje de error", "Ha ocurrido un error al actualizar el deposito legal.");
	    	}
	    	else	app.error("Mensaje de error", "El libro no se ha actualizado, revise sus datos.");
    	}
    	else{
    		app.error("Mensaje de error", "Rellene todos los datos.");
    		app.getControl().getLibroStage().toFront();
    	}
    }
    
    private void bindModelo() {
		nuevoLibroModelo.setFechaIntro(LocalDate.now());
		nuevoLibroModelo.ISBNProperty().bind(isbnTField.textProperty());
		nuevoLibroModelo.nombreLibroProperty().bind(tituloTField.textProperty());
		depositoLegalModelo.depositoLegalProperty().bind(depositoTField.textProperty());
		
	}
    
	public void bindActualizar(LibroModelo l,DepositoLegalModelo dModelo) {
		unbindModelo();
		
		nuevoLibroModelo = l;
		libro.setCodLibro(l.getCodLibro());
		d.setCodLibroDeposito(libro);	
		depositoLegalModelo = dModelo;
		
    	isbnTField.textProperty().bind(nuevoLibroModelo.ISBNProperty());
		tituloTField.textProperty().bind(nuevoLibroModelo.nombreLibroProperty());
		depositoTField.textProperty().bind(depositoLegalModelo.depositoLegalProperty());
		
		unbindActualizar();
		bindModelo();
	}


	private void unbindActualizar() {
		isbnTField.textProperty().unbind();
		tituloTField.textProperty().unbind();
		depositoTField.textProperty().unbind();
	}

	private void unbindModelo() {
		nuevoLibroModelo.ISBNProperty().unbind();
		nuevoLibroModelo.nombreLibroProperty().unbind();
		depositoLegalModelo.depositoLegalProperty().unbind();
	}

	public Button getGrabarLibroButton() {
		return grabarLibroButton;
	}

	public Button getActualizarLibroButton() {
		return actualizarLibroButton;
	}

	public GridPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindModelo();
	}


	
}

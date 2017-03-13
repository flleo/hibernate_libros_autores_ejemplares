package hibernate.ui;
/*
 * Realizar inserciones, eliminaciones y actualizaciones de libros, y de los autores que les asignamos a los libros. Deberá
 * permitir libros con depositolegal y sin el.
 */
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import hibernate.ui.libro.LibroController;
import hibernate.ui.mapeo.Autores;
import hibernate.ui.mapeo.AutoresLibros;
import hibernate.ui.mapeo.DepositoLegal;
import hibernate.ui.mapeo.Ejemplares;
import hibernate.ui.mapeo.Libros;
import hibernate.ui.modelo.AutorModelo;
import hibernate.ui.modelo.DepositoLegalModelo;
import hibernate.ui.modelo.EjemplarModelo;
import hibernate.ui.modelo.LibroModelo;
import hibernate.ui.session.ConsultasBD;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HibernateController implements Initializable{

	//LIBROS DEPOSITO LEGAL/////////////////////////////////////////////////////////////
    @FXML
    private TableView<LibroModelo> librosTView;

    @FXML
    private TableColumn<LibroModelo, Number> codLibroTColumn;

    @FXML
    private TableColumn<LibroModelo, String> nombreLibroTColumn;

    @FXML
    private TableColumn<LibroModelo, String> ISBNTColumn;

    @FXML
    private TableColumn<LibroModelo, LocalDate> FechaEntradaTColumn;

    @FXML
    private TableView<DepositoLegalModelo> depositoLegalTView;

    @FXML
    private TableColumn<DepositoLegalModelo, Number> codigoDLegalTCoumn;
    
    @FXML
    private TableColumn<DepositoLegalModelo, String> depositoLegalTColumn;
    
    @FXML
    private Button insertarButton;
   
    ////////EJEMPLARES///////////////////////////////////////////////////
    @FXML
    private TableView<EjemplarModelo> ejemplaresTView;

    @FXML
    private TableColumn<EjemplarModelo, Number> codEjemplarTColumn;

    @FXML
    private TableColumn<EjemplarModelo, Number> importeTColumn;

    @FXML
    private TableColumn<EjemplarModelo, String> monedaTColumn;
    
    @FXML
    private TableColumn<EjemplarModelo, Number> codigoLibroTColumn;


    @FXML
    private TextField libroTField;
    
    //AUTORES////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<AutorModelo> autoresTView;

    @FXML
    private TableColumn<AutorModelo, String> codAutorTColumn;

    @FXML
    private TableColumn<AutorModelo, String> nombreAutorTColumn;

    @FXML
    private  ComboBox<String> comboBox;
    

   

    private BorderPane view;
	private HibernateApp app;
	private List<Libros> librosMapeo;
	private List<DepositoLegal> depositosLegalMapeo;
	private List<Ejemplares> ejemplaresMapeo;
	private List<Autores> autoresMapeo;
	private ListProperty<LibroModelo> librosProperty;
	private ListProperty<DepositoLegalModelo> depositosProperty;
	private ListProperty<EjemplarModelo> ejemplaresProperty;
	private ListProperty<AutorModelo> autoresProperty, comboBoxProperty;
	private ConsultasBD consultasBD;
	private LibroController libroController;
	private Stage libroStage;
	private ObservableList<String> options;
	private LibroModelo libroModelo = new LibroModelo();
	private DepositoLegalModelo depositoModelo = new DepositoLegalModelo();
	private AutorModelo autorModelo = new AutorModelo();
	private EjemplarModelo ejemplarModelo = new EjemplarModelo();
	private Libros libroMapeo = new Libros();
	private Autores autorMapeo = new Autores();
	private AutoresLibros autorlibroMapeo = new AutoresLibros();
	private Ejemplares ejemplarMapeo = new Ejemplares();
	
	
	
	
	
	
	public HibernateController(HibernateApp app){
		this.app = app;
		consultasBD  = new ConsultasBD();
		
		librosProperty  = new SimpleListProperty<>(this, "libros", FXCollections.observableArrayList());
		depositosProperty  = new SimpleListProperty<>(this,"depositosLegales", FXCollections.observableArrayList());
		ejemplaresProperty = new SimpleListProperty<>(this, "ejemplares", FXCollections.observableArrayList());
		autoresProperty = new SimpleListProperty<>(this,"autores", FXCollections.observableArrayList());
		comboBoxProperty = new SimpleListProperty<>(this,"autoresCombo", FXCollections.observableArrayList());
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RootView.fxml"));
			loader.setController(this);
			view = loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bind();	
		
	}

	//Bindeamos con el modelo
	private void bind() {
		librosTView.itemsProperty().bind(librosProperty);
		depositoLegalTView.itemsProperty().bind(depositosProperty);
		ejemplaresTView.itemsProperty().bind(ejemplaresProperty);
		autoresTView.itemsProperty().bind(autoresProperty);
	}
	
	@FXML
    void recargarOnAction(ActionEvent event) {
    	autoresMapeo = consultasBD.listaAutores();
		ejemplaresMapeo = consultasBD.listaEjemplares();
		bindTodosAutores();
    }   
	
    @FXML
    void insertarLibroOnAction(ActionEvent event) {
    	libroController = new LibroController(app);
    	libroController.getActualizarLibroButton().setVisible(false);
		libroController.getGrabarLibroButton().setVisible(true);
		libroStage = new Stage();
		libroStage.setScene(new Scene(libroController.getView(),600,250));
		libroStage.setTitle("Formulario de alta de Libro");
		libroStage.getIcons().add(new Image(getClass().getResource("resources/libro.jpg").toString()));
		libroStage.setOnCloseRequest(v->view.setDisable(false));
		libroStage.show();
		view.setDisable(true);
		
    }  
  
    @FXML
    void añadirAutorOnAction(ActionEvent event) {
    	//Seleccionamos el nombre del combo y el libro del seleccionado
    	String nombre = comboBox.getSelectionModel().getSelectedItem();
    	//Buscamos entre las listas
    	libroMapeo.setCodLibro(libroModelo.getCodLibro());
    	for (Autores autores : autoresMapeo) 
    		if(autores.getNombreAutor().equals(nombre)){
    			consultasBD.creaAutorLibro(autores, libroMapeo);
    			autorModelo.setCodAutor(autores.getCodAutor());
    			autoresProperty.add(autorModelo);
    		}
    	bindAutores();
    }
    
 
    
    @FXML
    void actualizarLibroOnAction(ActionEvent event) {
    	libroController = new LibroController(app);
		libroController.bindActualizar(libroModelo,depositoModelo);
		libroController.getActualizarLibroButton().setVisible(true);
		libroController.getGrabarLibroButton().setVisible(false);
		libroStage = new Stage();
		libroStage.setScene(new Scene(libroController.getView(),600,250));
		libroStage.setTitle("Formulario de Actualización de Libro");
		libroStage.getIcons().add(new Image(getClass().getResource("resources/libro.jpg").toString()));
		libroStage.setOnCloseRequest(v->view.setDisable(false));
		libroStage.show();
		view.setDisable(true);
    }
    
    @FXML
    void eliminarLibroOnAction(ActionEvent event) {
    	if (app.confirm("Eliminar libros", "Eliminando libro.", "¿Seguro que desea eliminar el libro seleccionado?")) {
			libroMapeo.setCodLibro(libroModelo.getCodLibro());
			DepositoLegal de = new DepositoLegal();
    		de.setCodLibroDeposito(libroMapeo);
    		consultasBD.eliminaDepositoLegal(de);
			consultasBD.eliminaLibro(libroMapeo); librosProperty.remove(libroModelo);
			depositosProperty.remove(depositoModelo);
			bind();
		}
    }
    
    @FXML
    void eliminarEjeOnAction(ActionEvent event) {

    	if (app.confirm("Eliminar ejemplar", "Eliminando ejemplar.", "¿Seguro que desea eliminar el ejemplar seleccionado?")) {
    		libroMapeo.setCodLibro(libroModelo.getCodLibro());
    		ejemplarModelo  = ejemplaresTView.getSelectionModel().getSelectedItems().get(0);
    		ejemplarMapeo.setCodEjemplar(ejemplarModelo.getCodEjemplar());
    		ejemplarMapeo.setCodLibro(libroMapeo);
    		consultasBD.eliminaEjemplar(ejemplarMapeo);
    		ejemplaresProperty.remove(ejemplarModelo);
    		try{
	    		for (Ejemplares eje : ejemplaresMapeo) 
					if(eje.getCodEjemplar()==ejemplarMapeo.getCodEjemplar())
						ejemplaresMapeo.remove(eje);
    		}catch (Exception e) {
				// TODO: handle exception
			}
		}
    }
    
    @FXML
    void eliminarAutorOnAction(ActionEvent event) {
    	if (app.confirm("Eliminar autor", "Eliminando autor.", "¿Seguro que desea eliminar el autor seleccionado?")) {
    	autorModelo = autoresTView.getSelectionModel().getSelectedItems().get(0);
    	autorMapeo.setCodAutor(autorModelo.getCodAutor());
    	libroMapeo.setCodLibro(libroModelo.getCodLibro());
    	autorlibroMapeo.setCodAutor(autorMapeo);
    	autorlibroMapeo.setCodLibro(libroMapeo);
    	consultasBD.eliminaAutorLibro(autorlibroMapeo);
    	autoresProperty.remove(autorModelo);
    	}
    }
    
    @FXML
    void librosOnClick(MouseEvent event) {
    	libroModelo =  librosTView.getSelectionModel().getSelectedItems().get(0);
    	libroMapeo.setCodLibro(libroModelo.getCodLibro());
    	
    	for (DepositoLegalModelo depositos : depositosProperty) 
			if(depositos.getCodLibroDeposito()==libroModelo.getCodLibro()) depositoModelo = depositos;
    	bindEjemplares();
    	bindAutores();
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Le doy propiedades a la tabla Libro
		codLibroTColumn.setCellValueFactory(codLibro-> codLibro.getValue().codLibroProperty());
		nombreLibroTColumn.setCellValueFactory(nombreLibro -> nombreLibro.getValue().nombreLibroProperty());
		ISBNTColumn.setCellValueFactory(isbn-> isbn.getValue().ISBNProperty());
		FechaEntradaTColumn.setCellValueFactory(fecha-> fecha.getValue().fechaIntroProperty());
		
		//Propiedades tabla Deposito Legal
		codigoDLegalTCoumn.setCellValueFactory(value->value.getValue().codLibroDepositoProperty());
		depositoLegalTColumn.setCellValueFactory(value->value.getValue().depositoLegalProperty());
		
		//Propiedades tabla Ejemplares
		codEjemplarTColumn.setCellValueFactory(value->value.getValue().codEjemplarProperty());
		importeTColumn.setCellValueFactory(value->value.getValue().importeProperty());
		monedaTColumn.setCellValueFactory(value->value.getValue().tipoMonedaProperty());
		codigoLibroTColumn.setCellValueFactory(value->value.getValue().codLibroProperty());
		
		//Propiedades tabla Autor
		codAutorTColumn.setCellValueFactory(value->value.getValue().codAutorProperty());
		nombreAutorTColumn.setCellValueFactory(value->value.getValue().nombreAutorProperty());
		
		//Creación de las listas
		librosMapeo = consultasBD.listaLibros();
		depositosLegalMapeo = consultasBD.listaDepositoLegal();
		autoresMapeo = consultasBD.listaAutores();
		ejemplaresMapeo = consultasBD.listaEjemplares();
		
		
		bindLibros();	
		bindTodosAutores();
	}
	
	private void bindTodosAutores() {
		List<String> a = new ArrayList<>();
		for (Autores autores : autoresMapeo) a.add(autores.getNombreAutor());
		comboBox.setItems(FXCollections.observableArrayList(a));
	}

	public void bindLibros(){
		librosProperty.clear();
		depositosProperty.clear();
		//Relleno del Modelo para TabLibros
		int i,j;
		for (i=0,j=0; i < librosMapeo.size(); i++,j++) {
			LibroModelo li  = new LibroModelo();
			li.setCodLibro(librosMapeo.get(i).getCodLibro());
			li.setFechaIntro(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(librosMapeo.get(i).getFechaIntro())));
			li.setISBN(librosMapeo.get(i).getISBN());
			li.setNombreLibro(librosMapeo.get(i).getNombreLibro());
			librosProperty.add(li);
			
			DepositoLegalModelo depo  = new DepositoLegalModelo();
			try{
				if(depositosLegalMapeo.get(j).getCodLibroDeposito().getCodLibro()==(li.getCodLibro())){
					depo.setCodLibroDeposito(depositosLegalMapeo.get(j).getCodLibroDeposito().getCodLibro());
					depo.setDepositoLegal(depositosLegalMapeo.get(j).getDepositolegal());
				}
				else{
					depo.setCodLibroDeposito(li.getCodLibro());
					depo.setDepositoLegal("0");
					j--;
				}
			}
				catch (Exception e) {
					depo.setCodLibroDeposito(li.getCodLibro());
					depo.setDepositoLegal("0");
				}
			depositosProperty.add(depo);
		}
	}
    
	
	private void bindEjemplares() {
		ejemplaresProperty.clear();
		libroTField.textProperty().bind(libroModelo.nombreLibroProperty());
		for (int i = 0; i < ejemplaresMapeo.size(); i++) {
			if(ejemplaresMapeo.get(i).getCodLibro().getCodLibro()==libroModelo.getCodLibro()){
				ejemplarModelo = new EjemplarModelo();
				ejemplarModelo.setCodEjemplar(ejemplaresMapeo.get(i).getCodEjemplar());
				ejemplarModelo.setCodLibro(ejemplaresMapeo.get(i).getCodLibro().getCodLibro());
				ejemplarModelo.setImporte(ejemplaresMapeo.get(i).getImporte());
				ejemplarModelo.setTipoMoneda(ejemplaresMapeo.get(i).getTipoMoneda());
				
				ejemplaresProperty.add(ejemplarModelo);
			}
		}
	}
	
	private void bindAutores() {
		autoresProperty.clear();
		int codLibro = libroModelo.getCodLibro();
		Iterator it = consultasBD.listaAutoresLibro(codLibro);
		while (it.hasNext()) {
			Object[] par =(Object[]) it.next();
			Autores a = (Autores) par[0];
			AutorModelo am = new AutorModelo();
			am.setCodAutor(a.getCodAutor());
			am.setNombreAutor(a.getNombreAutor());
			autoresProperty.add(am);
		}
		consultasBD.getSession().getTransaction().commit();
	}
	
	
	

	public ListProperty<LibroModelo> getLibrosProperty() {
		return librosProperty;
	}

	public ListProperty<DepositoLegalModelo> getDepositosProperty() {
		return depositosProperty;
	}
	
	

	public TableView<LibroModelo> getLibrosTView() {
		return librosTView;
	}

	public ConsultasBD getConsultasBD() {
		return consultasBD;
	}
	
	

	public LibroModelo getLibro() {
		return libroModelo;
	}

	public Stage getLibroStage() {
		return libroStage;
	}
	

	public BorderPane getVista() {
		return view;
	}
}

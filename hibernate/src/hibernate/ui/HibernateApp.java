package hibernate.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HibernateApp extends Application {
	
	private	HibernateController control;
	private Stage primaryStage;
	private Scene scene;
	
	public HibernateApp(){
		control = new HibernateController(this);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		scene = new Scene(control.getVista(),800,600);
		
		this.primaryStage = primaryStage;
		primaryStage.setScene(scene);
		primaryStage.setTitle("Proyecto de Hibernate Bd Biblioteca");
		primaryStage.getIcons().add(new Image(getClass().getResource("resources/hibernate.jpg").toString()));
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(value-> System.exit(0));
	}
	
	public void error(String header, String content) {
		Alert error = new Alert(AlertType.ERROR);
		error.initOwner(getPrimaryStage());
		error.setTitle("Error");
		error.setHeaderText(header);
		error.setContentText(content);
		error.showAndWait();
	}
	
	public boolean confirm(String title, String header, String content) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.initOwner(getPrimaryStage());
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(content);
		return confirm.showAndWait().get().equals(ButtonType.OK);
	}
	
	public boolean alert(String title, String header, String content) {
		Alert confirm = new Alert(AlertType.WARNING);
		confirm.initOwner(getPrimaryStage());
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(content);
		return confirm.showAndWait().get().equals(ButtonType.OK);
	}
	
	
	public void info(String header, String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.initOwner(getPrimaryStage());
		info.setTitle(getPrimaryStage().getTitle());
		info.setHeaderText(header);
		info.setContentText(content);
		info.showAndWait();
	}
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	

	public HibernateController getControl() {
		return control;
	}
	
	

	

	public static void main(String[] args) {
		launch();

	}

	

}

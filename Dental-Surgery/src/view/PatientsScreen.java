package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Patient;
import model.Person;

public class PatientsScreen extends Pane {

	private static PatientsScreen instance;
	private TableView tblPatients;

	public PatientsScreen() {
		instance = this;
		go();
	}

	public static PatientsScreen getInstance() {
		if (instance == null) {
			return new PatientsScreen();
		} else {
			instance.go();
			return instance;
		}
	}

	public void go() {
		VBox pane = MainScreen.getInstance().getLayout();
		pane.setPadding(new Insets(20));

		// PERSONAL
		HBox personalFields = new HBox(10);

		Label title = new Label("Patients");
		title.setFont(new Font("Arial", 22));
		title.setWrapText(true);
		TextField fldName = new TextField("");
		TextField fldLastName = new TextField("");
		TextField fldEmail = new TextField("");
		TextField fldAddress = new TextField("");
		TextField fldPhoneNumber = new TextField("");

		personalFields.getChildren().addAll(fldName, fldLastName, fldEmail, fldAddress, fldPhoneNumber);
		
		fldName.prefWidthProperty().bind(personalFields.widthProperty().multiply(0.15));
		fldLastName.prefWidthProperty().bind(personalFields.widthProperty().multiply(0.15));
		fldEmail.prefWidthProperty().bind(personalFields.widthProperty().multiply(0.2));
		fldAddress.prefWidthProperty().bind(personalFields.widthProperty().multiply(0.3));
		fldPhoneNumber.prefWidthProperty().bind(personalFields.widthProperty().multiply(0.2));

		fldName.setPromptText("First Name");
		fldLastName.setPromptText("Last Name");
		fldEmail.setPromptText("Email");
		fldAddress.setPromptText("Address");
		fldPhoneNumber.setPromptText("Phone No.");

		HBox buttons = new HBox(10);
		Button btnSearchPatient = new Button("Search");
		Button btnAddPatient = new Button("Add Patient");
		btnSearchPatient.setPadding(new Insets(10, 20, 10, 20));
		btnAddPatient.setPadding(new Insets(10, 20, 10, 20));
		btnSearchPatient.setPrefWidth(150);
		btnAddPatient.setPrefWidth(150);
		buttons.setAlignment(Pos.BASELINE_RIGHT);
		buttons.getChildren().addAll(btnSearchPatient, btnAddPatient);

		HBox.setHgrow(fldAddress, Priority.ALWAYS);

		tblPatients = createTable();

		pane.getChildren().addAll(title, tblPatients, personalFields, buttons);
		pane.setStyle("-fx-background-color: #C4CFDD");
	}

	private TableView<Patient> createTable() {
		TableView<Patient> table = new TableView<Patient>();
		TableColumn<Patient, String> firstNameCol = new TableColumn<Patient,String>("First Name");
		TableColumn<Patient, String> lastNameCol = new TableColumn<Patient, String>("Last Name");
		TableColumn<Patient, String> emailCol = new TableColumn<Patient, String>("Email");
		TableColumn<Patient, String> addressCol = new TableColumn<Patient, String>("Address");
		TableColumn<Patient, String> phoneCol = new TableColumn<Patient, String>("Phone No.");
		
		
	    ObservableList<Person> personData = FXCollections.observableArrayList();
		Patient testPatient = new Patient("John", "Smith", "jsmith@gmail.com", "23 Rock Ave.", "555-123-4431");
		
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        
        personData.add(testPatient);
     		
		
		
		firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		addressCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
		phoneCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));

		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, addressCol, phoneCol);
		
		table.getItems().add(testPatient);
		return table;
	}
}

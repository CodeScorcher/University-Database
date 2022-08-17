package application;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UniversityGUI extends Application {
	// Static method defined in the Application class for launching a JavaFX application.
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		Database database = new Database();
		
		// TableView, TableColumn, and TableCell are used to display and modify a table.
		// Create an instance of table view to display data.
		TableView<Person> tableView = new TableView<>();
		// tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		ObservableList<Person> data = tableView.getItems();
		data.addAll(database);
		
		// Create table column for first names.
		TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setMinWidth(150);
		
		Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> firstNameColumnCellValue;
		
		firstNameColumnCellValue = cellDataFeatures -> {
            Person person = cellDataFeatures.getValue();
            String firstName = person.getFirstName();
            ObservableValue<String> firstNameObservableValue = new SimpleStringProperty(firstName);
            return firstNameObservableValue;
        };
        
		firstNameColumn.setCellValueFactory(firstNameColumnCellValue);
		
		// Create table column for last names.
		TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setMinWidth(150);
		
		Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> lastNameColumnCellValue;
		
		lastNameColumnCellValue = cellDataFeatures -> {
			Person person = cellDataFeatures.getValue();
			String lastName = person.getLastName();
			ObservableValue<String> lastNameObservableValue = new SimpleStringProperty(lastName);
			return lastNameObservableValue;
		};
		
		lastNameColumn.setCellValueFactory(lastNameColumnCellValue);
		
		// Create table column for email addresses.
		TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(200);
		
		Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> emailColumnCellValue;
		
		emailColumnCellValue = cellDataFeatures -> {
			Person person = cellDataFeatures.getValue();
			String email = person.getEmailAddress();
			ObservableValue<String> emailObservableValue = new SimpleStringProperty(email);
			return emailObservableValue;
		};
		
		emailColumn.setCellValueFactory(emailColumnCellValue);
		
		// Create table column for home addresses.
		TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setMinWidth(313);
		
		Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> addressColumnCellValue;
		
		addressColumnCellValue = cellDataFeatures -> {
			Person person = cellDataFeatures.getValue();
			Address addressClass = person.getAddress();
			String addressString = "";
			addressString += addressClass.getStreetNumber() + " ";
			addressString += addressClass.getStreetName() + ", ";
			addressString += addressClass.getCity() + ", ";
			addressString += addressClass.getState() + ", ";
			addressString += addressClass.getZipCode();
			ObservableValue<String> addressObservableValue = new SimpleStringProperty(addressString);
			return addressObservableValue;
		};
		
		addressColumn.setCellValueFactory(addressColumnCellValue);
		
		// Create table column for phone numbers.
		TableColumn<Person, String> phoneNumberColumn = new TableColumn<>("Phone Number(s)");
		phoneNumberColumn.setMinWidth(150);
		
		Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> phoneNumberColumnCellValue;
		
		phoneNumberColumnCellValue = cellDataFeatures -> {
			Person person = cellDataFeatures.getValue();
			String phoneNumber = person.getFormattedPhoneNumbers();
			ObservableValue<String> phoneNumberObservableValue = new SimpleStringProperty(phoneNumber);
			return phoneNumberObservableValue;
		};
		
		phoneNumberColumn.setCellValueFactory(phoneNumberColumnCellValue);
		
		// Create table column for student information (class standing, GPA).
		TableColumn<Person, String> studentColumn = new TableColumn<>("Student Info");
		studentColumn.setMinWidth(150);
		
		Callback<CellDataFeatures<Person, String>, ObservableValue<String>> studentColumnCellValue;
		
		studentColumnCellValue = cellDataFeatures -> {
			Student student;
			String studentData;
			
			if(cellDataFeatures.getValue().getClass().getTypeName() == "application.Student") {
				student = (Student) cellDataFeatures.getValue();
				studentData = "Class Standing: " + student.getClassStanding() + "\nGPA: " + student.getGpa();
				ObservableValue<String> studentObservableValue = new SimpleStringProperty(studentData);
				return studentObservableValue;
			}
			
			studentData = "-";
			ObservableValue<String> studentObservableValue = new SimpleStringProperty(studentData);
			return studentObservableValue;
		};
		
		studentColumn.setCellValueFactory(studentColumnCellValue);
		
		// Create table column for faculty information (office location, salary, office hours, rank).
		TableColumn<Person, String> facultyColumn = new TableColumn<>("Faculty Info");
		facultyColumn.setMinWidth(180);
		
		Callback<CellDataFeatures<Person, String>, ObservableValue<String>> facultyColumnCellValue;
		
		facultyColumnCellValue = cellDataFeatures -> {
			Faculty faculty;
			String facultyData;
			
			if(cellDataFeatures.getValue().getClass().getTypeName() == "application.Faculty") {
				faculty = (Faculty) cellDataFeatures.getValue();
				facultyData = "Office Location: " + faculty.getOfficeLocation() + "\nOffice Hours: " + faculty.getOfficeHours() + "\nRank: " + faculty.getRank();
				ObservableValue<String> facultyObservableValue = new SimpleStringProperty(facultyData);
				return facultyObservableValue;
			}
			
			facultyData = "-";
			ObservableValue<String> facultyObservableValue = new SimpleStringProperty(facultyData);
			return facultyObservableValue;
		};
		
		facultyColumn.setCellValueFactory(facultyColumnCellValue);
		
		// Create table column for staff information (office location, salary, job title).
		TableColumn<Person, String> staffColumn = new TableColumn<>("Staff Info");
		staffColumn.setMinWidth(220);
		
		Callback<CellDataFeatures<Person, String>, ObservableValue<String>> staffColumnCellValue;
		
		staffColumnCellValue = cellDataFeatures -> {
			Staff staff;
			String staffData;
			
			if(cellDataFeatures.getValue().getClass().getTypeName() == "application.Staff") {
				staff = (Staff) cellDataFeatures.getValue();
				staffData = "Office Location: " + staff.getOfficeLocation() + "\nJob Title: " + staff.getJobTitle() + "\nSalary: " + staff.getSalary();
				ObservableValue<String> staffObservableValue = new SimpleStringProperty(staffData);
				return staffObservableValue;
			}
			
			staffData = "-";
			ObservableValue<String> staffObservableValue = new SimpleStringProperty(staffData);
			return staffObservableValue;
		};
		
		staffColumn.setCellValueFactory(staffColumnCellValue);
		
		tableView.setItems(data);
		tableView.getColumns().add(firstNameColumn);
		tableView.getColumns().add(lastNameColumn);
		tableView.getColumns().add(emailColumn);
		tableView.getColumns().add(addressColumn);
		tableView.getColumns().add(phoneNumberColumn);
		tableView.getColumns().add(studentColumn);
		tableView.getColumns().add(facultyColumn);
		tableView.getColumns().add(staffColumn);
		
		// Create file chooser and set its extension filter to only accept comma-separated values (csv) files.
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma-Separated Values (*.csv)", "*.csv"));
		fileChooser.setTitle("Select File");
		
		BorderPane rootPane = new BorderPane();
	    
	    HBox databaseOptions = new HBox(15);
	    databaseOptions.setPadding(new Insets(15,15,15,15));
	    databaseOptions.setAlignment(Pos.CENTER);
	    
		Button addData = new Button("Add");
		addData.setOnAction(e -> {
			GridPane pane = new GridPane();
			pane.setHgap(15);
			pane.setVgap(15);
			pane.setPadding(new Insets(15, 15, 15, 15));
			
			// First name label and text field.
			Label firstNameLabel = new Label("First Name");
			GridPane.setConstraints(firstNameLabel, 0, 0); // column 0 row 0
			
			TextField firstName = new TextField();
			firstName.setMaxWidth(150);
			GridPane.setConstraints(firstName, 0, 1);
			
			// Last name label and text field.
			Label lastNameLabel = new Label("Last Name");
			GridPane.setConstraints(lastNameLabel, 1, 0);
			
			TextField lastName = new TextField();
			lastName.setMinWidth(150);
			GridPane.setConstraints(lastName, 1, 1);
			
			// Street address label and text field.
			Label streetAddressLabel = new Label("Street Address");
			GridPane.setConstraints(streetAddressLabel, 0, 2);
			
			TextField streetAddress = new TextField();
			streetAddress.setMaxWidth(150);
			GridPane.setConstraints(streetAddress, 0, 3);
			
			// City label and text field.
			Label cityLabel = new Label("City");
			GridPane.setConstraints(cityLabel, 1, 2);
			
			TextField city = new TextField();
			city.setMaxWidth(150);
			GridPane.setConstraints(city, 1, 3);
			
			// State label and text field.
			Label stateLabel = new Label("State");
			GridPane.setConstraints(stateLabel, 0, 4);
			
			TextField state = new TextField();
			state.setMaxWidth(150);
			GridPane.setConstraints(state, 0, 5);
			
			// Zip code label and text field.
			Label zipCodeLabel = new Label("Zip Code");
			GridPane.setConstraints(zipCodeLabel, 1, 4);
			
			TextField zipCode = new TextField();
			zipCode.setMaxWidth(150);
			GridPane.setConstraints(zipCode, 1, 5);
			
			
			
			HBox phoneNumberBox = new HBox();
			phoneNumberBox.setSpacing(15);
			GridPane.setConstraints(phoneNumberBox, 0, 7);
			
			// Phone number label and text field.
			Label phoneNumberLabel = new Label("Phone Number");
			GridPane.setConstraints(phoneNumberLabel, 0, 6);
			
			// Phone number type label and text field.
			ObservableList<String> phoneNumberTypeList = FXCollections.observableArrayList("Home", "Mobile", "Work", "Fax");
			
			ComboBox<String> phoneNumberType = new ComboBox<>(phoneNumberTypeList);
			phoneNumberType.setValue("-Type-");
			
			TextField phoneNumber = new TextField();
			
			phoneNumberBox.getChildren().addAll(phoneNumberType, phoneNumber);
			
			// Email address label and text field.
			Label emailAddressLabel = new Label("Email Address");
			GridPane.setConstraints(emailAddressLabel, 1, 6);
			
			TextField emailAddress = new TextField();
			emailAddress.setMinWidth(150);
			GridPane.setConstraints(emailAddress, 1, 7);
			
			// Position label and combo box.
			Label positionLabel = new Label("Position");
			GridPane.setConstraints(positionLabel, 0, 8);
			
			// Create combo box that defines the role of the entry that is being added to the database.
			ObservableList<String> positionObservableList = FXCollections.observableArrayList("Student", "Faculty", "Staff");
			ComboBox<String> position = new ComboBox<>(positionObservableList);
			position.setValue("-Select-");
			GridPane.setConstraints(position, 0, 9);
			
			// Faculty & Staff: Office location label and text field.
			Label officeLocationLabel = new Label("Office Location");
			GridPane.setConstraints(officeLocationLabel, 0, 10);
			
			TextField officeLocation = new TextField();
			officeLocation.setMaxWidth(150);
			GridPane.setConstraints(officeLocation, 0, 11);
			
			// Faculty: Office hours label and text field.
			Label officeHoursLabel = new Label("Office Hours");
			GridPane.setConstraints(officeHoursLabel, 1, 10);
			
			TextField officeHours = new TextField();
			GridPane.setConstraints(officeHours, 1, 11);
			
			// Faculty: Rank label and text field.
			Label rankLabel = new Label("Rank");
			GridPane.setConstraints(rankLabel, 0, 12);
			
			TextField rank = new TextField();
			rank.setMaxWidth(150);
			GridPane.setConstraints(rank, 0, 13);
			
			// Faculty and Staff: Salary label and text field.
			Label salaryLabel = new Label("Salary");
			GridPane.setConstraints(salaryLabel, 1, 12);
			
			TextField salary = new TextField();
			GridPane.setConstraints(salary, 1, 13);
			
			// Staff: Job title label and text field.
			Label jobTitleLabel = new Label("Job Title");
			GridPane.setConstraints(jobTitleLabel, 0, 12);
			
			TextField jobTitle = new TextField();
			jobTitle.setMaxWidth(150);
			GridPane.setConstraints(jobTitle, 0, 13);
			
			// Student: Class standing label and text field.
			Label classStandingLabel = new Label("Class Standing");
			GridPane.setConstraints(classStandingLabel, 0, 10);
			
			TextField classStanding = new TextField();
			classStanding.setMaxWidth(150);
			GridPane.setConstraints(classStanding, 0, 11);
			
			// Student: GPA label and text field.
			Label gpaLabel = new Label("GPA");
			GridPane.setConstraints(gpaLabel, 1, 10);
			
			TextField gpa = new TextField();
			GridPane.setConstraints(gpa, 1, 11);
			
			position.setOnAction(parameter -> {
				switch(position.getValue()) {
					case "Student":
						if(pane.getChildren().contains(rankLabel))
							pane.getChildren().removeAll(officeLocationLabel, officeLocation, officeHoursLabel, officeHours, rankLabel, rank, salaryLabel, salary);
						
						else if(pane.getChildren().contains(jobTitle))
							pane.getChildren().removeAll(officeLocationLabel, officeLocation, jobTitleLabel, jobTitle, salaryLabel, salary);
						
						if(!pane.getChildren().contains(classStanding))
							pane.getChildren().addAll(classStandingLabel, classStanding, gpaLabel, gpa);
						
						break;
					case "Faculty":
						if(pane.getChildren().contains(classStanding))
							pane.getChildren().removeAll(classStandingLabel, classStanding, gpaLabel, gpa);
						
						else if(pane.getChildren().contains(jobTitle))
							pane.getChildren().removeAll(officeLocationLabel, officeLocation, jobTitleLabel, jobTitle, salaryLabel, salary);
						
						if(!pane.getChildren().contains(rank))
							pane.getChildren().addAll(officeLocationLabel, officeLocation, officeHoursLabel, officeHours, rankLabel, rank, salaryLabel, salary);
						
						break;
					case "Staff":
						if(pane.getChildren().contains(classStanding))
							pane.getChildren().removeAll(classStandingLabel, classStanding, gpaLabel, gpa);
						
						else if(pane.getChildren().contains(rank))
							pane.getChildren().removeAll(officeLocationLabel, officeLocation, officeHoursLabel, officeHours, rankLabel, rank, salaryLabel, salary);
						
						if(!pane.getChildren().contains(jobTitle))
							pane.getChildren().addAll(officeLocationLabel, officeLocation, jobTitleLabel, jobTitle, salaryLabel, salary);
						
						break;
				}
			});
			
			// Create submit button entry form.
			Button submitButton = new Button("Submit");
			GridPane.setConstraints(submitButton, 0, 14);
			
			pane.getChildren().addAll(firstNameLabel, firstName, lastNameLabel, lastName, 
									  streetAddressLabel, streetAddress, cityLabel, city, 
									  stateLabel, state, zipCodeLabel, zipCode, emailAddressLabel, 
									  emailAddress, phoneNumberLabel, phoneNumberBox, positionLabel, 
									  position, submitButton);
			
			Scene form = new Scene(pane, 450, 600);
			Stage scene = new Stage();
			scene.setTitle("Add Entry");
			scene.setScene(form);
			scene.setResizable(false);
			scene.initModality(Modality.APPLICATION_MODAL);
			
			submitButton.setOnAction(parameter -> {
				try {
					String streetAddressParts[] = streetAddress.getText().split(" ", 2);
					
					if(position.getValue() == "Student")
						database.add(new Student(firstName.getText(), lastName.getText(), emailAddress.getText(), 
									 new Address(streetAddressParts[0], streetAddressParts[1], city.getText(), state.getText(), zipCode.getText()), 
									 phoneNumberType.getValue(), phoneNumber.getText(), classStanding.getText(), Double.parseDouble(gpa.getText())));
					else if(position.getValue() == "Faculty")
						database.add(new Faculty(firstName.getText(), lastName.getText(), emailAddress.getText(), 
								 	 new Address(streetAddressParts[0], streetAddressParts[1], city.getText(), state.getText(), zipCode.getText()), 
								 	 phoneNumberType.getValue(), phoneNumber.getText(),  officeLocation.getText(), salary.getText(), officeHours.getText(), rank.getText()));
					else if(position.getValue() == "Staff")
						database.add(new Staff(firstName.getText(), lastName.getText(), emailAddress.getText(), 
							 	 new Address(streetAddressParts[0], streetAddressParts[1], city.getText(), state.getText(), zipCode.getText()), 
							 	 phoneNumberType.getValue(), phoneNumber.getText(),  officeLocation.getText(), salary.getText(), jobTitle.getText()));
				}
				catch(PhoneNumberFormatException ex) {
					ex.printStackTrace();
				}
				
				data.clear();
				data.addAll(database);
				scene.close();
			});
			
			scene.showAndWait();
		});
		
		Button removeData = new Button("Remove");
		removeData.setOnAction(e -> {
			if(tableView.getSelectionModel().getSelectedItem() != null) {
				database.removeIf(person -> (person.getId() == tableView.getSelectionModel().getSelectedItem().getId()));
				data.remove(tableView.getSelectionModel().getSelectedItem());
			};
		});
		
		Button readDataFile = new Button("Read File");
		
		readDataFile.setOnAction(e -> {
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
			try {
				if(selectedFile != null) {
					readData(database, selectedFile);
					data.clear();
					data.addAll(database);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
        Button writeDataToFile = new Button("Save Database To File");
        
        writeDataToFile.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            
            try {
				if(selectedFile != null)
					writeData(database, selectedFile);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
        });
        
        // Create flow pane in order to format our search box.
        FlowPane flowPane = new FlowPane();
		flowPane.setHgap(15);
		flowPane.setVgap(5);
		flowPane.setPadding(new Insets(15,15,15,15));
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setStyle("-fx-background-color: gold");
		
		// Create a filtered data list.
		FilteredList<Person> filteredData = new FilteredList<>(data, p -> true);
		
		SortedList<Person> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		TextField searchBox = new TextField();
		searchBox.setPromptText("Search for data...");
		
		searchBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					filteredData.setPredicate(person -> {
						String searchValue = searchBox.getText();
						
						// Display all entries if the search box is empty.
						if(searchValue == null || searchValue.isEmpty())
							return true;
						if(person.getFirstName().contains(searchValue))
							return true;
						else if(person.getLastName().contains(searchValue))
							return true;
						
						return false;
					});
					
					tableView.setItems(sortedData);
				}
			}
		});
	    
		Button searchButton = new Button("Search");
		
		searchButton.setOnAction(e -> {
			filteredData.setPredicate(person -> {
				String searchValue = searchBox.getText();
				
				// Display all entries if the search box is empty.
				if(searchValue == null || searchValue.isEmpty())
					return true;
				if(person.getFirstName().contains(searchValue))
					return true;
				else if(person.getLastName().contains(searchValue))
					return true;
				
				return false;
			});
			
			tableView.setItems(sortedData);
		});
		
	    flowPane.getChildren().add(searchBox);
	    flowPane.getChildren().add(searchButton);
        
        databaseOptions.getChildren().add(addData);
        databaseOptions.getChildren().add(removeData);
        databaseOptions.getChildren().add(readDataFile);
        databaseOptions.getChildren().add(writeDataToFile);
        
	    rootPane.setTop(flowPane);
	    rootPane.setCenter(tableView);
	    rootPane.setBottom(databaseOptions);
	    
		Scene scene = new Scene(rootPane, 800, 550);
		
		// Database icon provided by Icons8 (https://icons8.com/icon/Poo34OcLGqWZ/database)
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		
		primaryStage.setTitle("University Database");
		primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public static void writeData(Database database, File input) throws IOException, PhoneNumberFormatException{
		// Create Print Writer for the specified file we will write to.
		PrintWriter output = new PrintWriter(input);
		
		// Write database information to the file.
		output.print(database);
		output.close();
	}
	
	public static void readData(Database database, File input) throws IOException, PhoneNumberFormatException {
		// Create Scanner object for reading data from a comma-separated values file.
		Scanner csvReader = new Scanner(input);
		String[] personDetails;
		String[] addressDetails;
		String firstLineSkip;
		String secondLineSkip;
		
		while(csvReader.hasNextLine()) {
			firstLineSkip = csvReader.nextLine();
			personDetails = firstLineSkip.split(",");
			
			// If the current entry is a staff member, we use the data to add an instance of staff to database.
			if(firstLineSkip.contains("Staff")) {
				String firstName = personDetails[1];
				String lastName = personDetails[2];
				String emailAddress = personDetails[3];
				String officeLocation = personDetails[4];
				String salary = personDetails[5];
				String jobTitle = personDetails[6];
				
				secondLineSkip = csvReader.nextLine();
				addressDetails = secondLineSkip.split(",");
				
				String streetNumber = addressDetails[1];
				
				if(secondLineSkip.contains("Apt.")) {
					String apartmentNumber = addressDetails[2].substring(5);
					String streetName = addressDetails[3];
					String city = addressDetails[4];
				    String state = addressDetails[5];
				    String zipCode = addressDetails[6];
				    
				    database.add(new Staff(firstName, lastName, emailAddress, 
						new Address(streetNumber, apartmentNumber, streetName, city, state, zipCode), 
						officeLocation, salary, jobTitle));
				}
				else {
					String streetName = addressDetails[2];
					String city = addressDetails[3];
				    String state = addressDetails[4];
				    String zipCode = addressDetails[5];
				    
				    database.add(new Staff(firstName, lastName, emailAddress, 
						new Address(streetNumber, streetName, city, state, zipCode), 
						officeLocation, salary, jobTitle));
				}
			}
			
			// If current entry is faculty, we use the data to add an instance of faculty to database.
			else if(firstLineSkip.contains("Faculty")) {
				String firstName = personDetails[1];
				String lastName = personDetails[2];
				String emailAddress = personDetails[3];
				String officeLocation = personDetails[4];
				String salary = personDetails[5];
				String officeHours = personDetails[6];
				String rank = personDetails[7];
				
				secondLineSkip = csvReader.nextLine();
				addressDetails = secondLineSkip.split(",");
				
				String streetNumber = addressDetails[1];
				
				if(secondLineSkip.contains("Apt.")) {
					String apartmentNumber = addressDetails[2].substring(5);
					String streetName = addressDetails[3];
					String city = addressDetails[4];
				    String state = addressDetails[5];
				    String zipCode = addressDetails[6];
				    
				    database.add(new Faculty(firstName, lastName, emailAddress, 
				    		 	 new Address(streetNumber, apartmentNumber, streetName, city, state, zipCode), 
				    		 	 officeLocation, salary, officeHours, rank));
				}
				else {
					String streetName = addressDetails[2];
					String city = addressDetails[3];
				    String state = addressDetails[4];
				    String zipCode = addressDetails[5];
				    
				    database.add(new Faculty(firstName, lastName, emailAddress, 
				    			 new Address(streetNumber, streetName, city, state, zipCode), 
				    			 officeLocation, salary, officeHours, rank));
				}
			}
			
			// If current entry is a student, we use the data to add an instance of student to the database.
			else if(firstLineSkip.contains("Student")) {
				String firstName = personDetails[1];
				String lastName = personDetails[2];
				String emailAddress = personDetails[3];
				String classStanding = personDetails[4];
				double gpa = Double.parseDouble(personDetails[5]);
				
				secondLineSkip = csvReader.nextLine();
				
				addressDetails = secondLineSkip.split(",");
				
				String streetNumber = addressDetails[1];
				
				if(secondLineSkip.contains("Apt.")) {
					String apartmentNumber = addressDetails[2].substring(5);
					String streetName = addressDetails[3];
					String city = addressDetails[4];
				    String state = addressDetails[5];
				    String zipCode = addressDetails[6];
				    
				    database.add(new Student(firstName, lastName, emailAddress, 
				    			 new Address(streetNumber, apartmentNumber, streetName, city, state, zipCode), 
				    			 classStanding, gpa));
				}
				else {
					String streetName = addressDetails[2];
					String city = addressDetails[3];
				    String state = addressDetails[4];
				    String zipCode = addressDetails[5];
				    
				    database.add(new Student(firstName, lastName, emailAddress, 
				    			 new Address(streetNumber, streetName, city, state, zipCode), 
				    			 classStanding, gpa));
				}
			}
			
			// If the next line contains a phone number, create an phone number object
			// and add that to the person object previously made in the database.
			else if(firstLineSkip.contains("Phone")) {
				String[] phoneNumberDetails = firstLineSkip.split(",");
				String type = phoneNumberDetails[1];
				String number = phoneNumberDetails[2];
				
				database.get(database.size() - 1).addPhoneNumber(type, number);
			}
		}
		
		csvReader.close();
	}
}
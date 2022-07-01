package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utill.ValidationUtil;
import view.TM.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class ManageStudentController implements Initializable {

    public AnchorPane studentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentContactNo;
    public JFXTextField txtStudentDob;
    public JFXTextField txtStudentGender;
    public JFXButton btnSave;
    public TableView <StudentTM>tblStudent;
    public JFXButton btnADD;



    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    int index=-1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        generateStudentID();
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("btnupdate"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("btndelete"));

        tblStudent.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue) ->{
            index=(int) newValue;
        } );

        txtStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        txtStudentAddress.setDisable(true);
        txtStudentDob.setDisable(true);
        txtStudentContactNo.setDisable(true);
        txtStudentGender.setDisable(true);

        loadAllStudents();
    }

    private void generateStudentID() {
        txtStudentId.setText(studentBO.generateStudentID());
    }

    JFXButton buttonDelete;
    public void setDBtn(){
        buttonDelete = new JFXButton();
        buttonDelete.setStyle("-fx-border-color: red");
        Image img = new Image("assert/icons8-delete-64.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonDelete.setGraphic(view);
    }

    JFXButton buttonUpdate;
    public void setUBtn(){
        buttonUpdate = new JFXButton();
        buttonUpdate.setStyle("-fx-border-color: blueviolet");
        Image img = new Image("assert/icons8-edit-48.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonUpdate.setGraphic(view);
    }

    public void setOnActionForDelete(String Value) {
        buttonDelete.setOnAction((event) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Student?", yes, no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no) == yes) {

                if (studentBO.deleteStudent(Value)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful.").showAndWait();

                    loadAllStudents();

                    txtStudentName.clear();
                    txtStudentAddress.clear();
                    txtStudentDob.clear();
                    txtStudentContactNo.clear();
                    txtStudentGender.clear();

                    txtStudentName.setDisable(true);
                    txtStudentAddress.setDisable(true);
                    txtStudentDob.setDisable(true);
                    txtStudentContactNo.setDisable(true);
                    txtStudentGender.setDisable(true);


                    generateStudentID();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again.").showAndWait();
                }

            } else {
            }
        });

    }

    public void setOnActionForUpdate(){

        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                StudentTM tm = tblStudent.getSelectionModel().getSelectedItem();

                txtStudentId.setText(tm.getStudent_id());
                txtStudentName.setText(tm.getName());
                txtStudentAddress.setText(tm.getAddress());
                txtStudentContactNo.setText(tm.getContact_no());
                txtStudentDob.setText(tm.getDob());
                txtStudentGender.setText(tm.getGender());


                txtStudentName.setDisable(false);
                txtStudentAddress.setDisable(false);
                txtStudentDob.setDisable(false);
                txtStudentContactNo.setDisable(false);
                txtStudentGender.setDisable(false);

                btnADD.setText("Update");

                btnADD.setOnAction(event1 -> {
                    if (studentBO.updateStudent(new StudentDTO(txtStudentId.getText(), txtStudentName.getText(),txtStudentAddress.getText(),txtStudentContactNo.getText(),txtStudentDob.getText(),txtStudentGender.getText()))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated New Student.").showAndWait();
                        loadAllStudents();
                        txtStudentName.clear();
                        txtStudentAddress.clear();
                        txtStudentDob.clear();
                        txtStudentContactNo.clear();
                        txtStudentGender.clear();

                        txtStudentName.setDisable(true);
                        txtStudentAddress.setDisable(true);
                        txtStudentDob.setDisable(true);
                        txtStudentContactNo.setDisable(true);
                        txtStudentGender.setDisable(true);


                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Try Again.").show();
                    }
                });
            }
        });
    }



    private void loadAllStudents() {
        tblStudent.getItems().clear();
        ArrayList<StudentDTO>all=studentBO.getAllStudents();
        ObservableList<StudentTM> obList= FXCollections.observableArrayList();

        for (StudentDTO c : all) {

            setUBtn();
            setDBtn();

            obList.add(new StudentTM(c.getStudent_id(),c.getName(),c.getAddress(),c.getContact_no(),c.getDob(),
                    c.getGender(),buttonUpdate,buttonDelete));

            setOnActionForDelete(c.getStudent_id());
            setOnActionForUpdate();
        }
        tblStudent.getItems().addAll(obList);
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        txtStudentName.setDisable(false);
        txtStudentAddress.setDisable(false);
        txtStudentDob.setDisable(false);
        txtStudentContactNo.setDisable(false);
        txtStudentGender.setDisable(false);
    }
    public void addStudentOnAction(ActionEvent actionEvent) {
        if (txtStudentName.getText().isEmpty() || txtStudentAddress.getText().isEmpty() || txtStudentDob.getText().isEmpty()||
        txtStudentContactNo.getText().isEmpty()||txtStudentGender.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (studentBO.saveStudent(new StudentDTO(txtStudentId.getText(), txtStudentName.getText(),txtStudentAddress.getText(),txtStudentContactNo.getText(),txtStudentDob.getText(),txtStudentGender.getText()))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added New Student.").showAndWait();

                loadAllStudents();


                txtStudentName.clear();
                txtStudentAddress.clear();
                txtStudentDob.clear();
                txtStudentContactNo.clear();
                txtStudentGender.clear();

                txtStudentName.setDisable(true);
                txtStudentAddress.setDisable(true);
                txtStudentDob.setDisable(true);
                txtStudentContactNo.setDisable(true);
                txtStudentGender.setDisable(true);

                generateStudentID();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }
    }

    public void clearStudentOnAction(ActionEvent actionEvent) {
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentDob.clear();
        txtStudentContactNo.clear();
        txtStudentGender.clear();

    }

    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) studentContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


//    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
//    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
//    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
//
//
//    private void storeValidations() {
//        map.put(txtStudentName, namePattern);
//        map.put(txtStudentAddress, addressPattern);
//
//    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
//        Object response = ValidationUtil.validate(map,btnSave);
//        if (keyEvent.getCode() == KeyCode.ENTER) {
//            if (response instanceof TextField) {
//                TextField errorText = (TextField) response;
//                errorText.requestFocus();
//            } else if (response instanceof Boolean) {
//                //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
//            }
//        }
    }

}



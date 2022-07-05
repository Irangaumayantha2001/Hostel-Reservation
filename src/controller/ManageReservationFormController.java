package controller;

import bo.BOFactory;
import bo.custom.QueryBO;
import bo.custom.ReservationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Reserve;
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
import view.TM.ReservationTM;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageReservationFormController implements Initializable {


    public AnchorPane reservationContext;

    public JFXComboBox <String>cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public Label lblReservId;

    public TableView <ReservationTM>tblReservation;

    public JFXDatePicker dpDate;
    public CheckBox chPayment;
    public TextField txtSearch;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);
    private  final ReservationBO reservationBO =(ReservationBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.RESERVATION_ROOM);

    private QueryBO queryBO = (QueryBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.QUERY);

    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReservation.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("update"));
        tblReservation.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("delete"));

        tblReservation.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                index = (int) newValue;
            }
        });

        ArrayList<RoomDTO> all = roomBO.getAllRooms();

        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (RoomDTO course : all) {
            observableList.add(course.getRoom_id());
        }
        cmbRoomId.setItems(observableList);

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                RoomDTO course = roomBO.searchRoom(newValue);
                txtRoomType.setText(course.getType());
                txtQtyOnHand.setText(String.valueOf(course.getQty()));
                txtUnitPrice.setText(String.valueOf(course.getMonthly_rent()));

            }
        });


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

    public void setOnActionForDelete(String Value){

        buttonDelete.setOnAction((event) -> {
            ButtonType yes= new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no= new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Register Details?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                if (reservationBO.deleteReserv(Value)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful.").showAndWait();
                    txtStudentName.clear();
                    lblReservId.setText(null);
                    dpDate.setValue(null);
                    chPayment.setSelected(false);
                    cmbStudentId.getSelectionModel().clearSelection();
                    cmbRoomId.getSelectionModel().clearSelection();
                    txtUnitPrice.clear();
                    txtQtyOnHand.clear();
                    txtRoomType.clear();
                }

            }else{
            }
        });
    }

    ReservationDTO search = null;

    public void setOnActionForUpdate(){

        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                ReservationTM tm = tblReservation.getSelectionModel().getSelectedItem();

                cmbRoomId.setValue(tm.getRoom_id());
                txtRoomType.setText(tm.getType());
                txtUnitPrice.setText(String.valueOf(tm.getMonthly_rent()));
                txtQtyOnHand.setText(String.valueOf(tm.getQty()));
                lblReservId.setText(tm.getReservationId());
                search =reservationBO.searchReserv(tm.getRoom_id());

                dpDate.setValue(LocalDate.parse(search.getDate()));
                if (search.getPayment().equalsIgnoreCase("paid")){
                    chPayment.setSelected(true);
                }
            }
        });

    }

    ObservableList<ReservationTM> obList = FXCollections.observableArrayList();

    public void searchOnAction(ActionEvent actionEvent) {
        StudentDTO student = studentBO.searchStudent(txtSearch.getText());
        txtStudentName.setText(student.getName());


//        for (Reserve register : student.getRegisterList()) {
//            setUBtn();
//            setDBtn();
//            obList.add(new ReservationTM(register.getRes_id(),register.getRoom().getRoom_id(),register.getRoom().getType(),register.getRoom().getMonthly_rent()
//                    ,register.getStatus(),buttonUpdate,buttonDelete));
//            setOnActionForUpdate();
//            setOnActionForDelete(register.getRes_id());
//        }
//        tblReservation.setItems(obList);

    }

    public int isExists(ReservationTM tm){
        for (int j = 0; j < obList.size(); j++) {
            if (tm.getReservationId().equals(obList.get(j).getReservationId())){
                return j;
            }
        }
        return -1;
    }

    public void addToTabelOnAction(ActionEvent actionEvent) {
        setUBtn();
        setDBtn();
        ReservationTM registerTM = new ReservationTM(lblReservId.getText(), cmbRoomId.getValue(), txtRoomType.getText(), Integer.parseInt(txtQtyOnHand.getText()), (txtUnitPrice.getText()), buttonUpdate, buttonDelete);
        setOnActionForUpdate();
        setOnActionForDelete(lblReservId.getText());

        int exists = isExists(registerTM);

        if (exists == -1) {

            tblReservation.getSelectionModel().clearSelection();
            obList.add(registerTM);
        } else {

            ReservationTM tm = obList.get(exists);

            ReservationTM register = new ReservationTM(
                    tm.getReservationId(),
                    cmbRoomId.getValue(),
                    txtRoomType.getText(),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    txtUnitPrice.getText(),
                    buttonUpdate,
                    buttonDelete
            );
            obList.remove(exists);
            obList.add(register);
        }

        tblReservation.setItems(obList);

    }

    public void updateStudentOnAction(ActionEvent actionEvent) {
        String payment = null;
        if (chPayment.isSelected()){
            payment = "Paid";
        }else {
            payment = "Not Paid";
        }

        if (cmbRoomId.getValue() == null || txtQtyOnHand.getText().isEmpty() || txtRoomType.getText().isEmpty() || txtUnitPrice.getText().isEmpty() ||
                txtStudentName.getText().isEmpty()  || payment.equals("Not Paid")) {
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        } else {
            ObservableList<ReservationTM> items = tblReservation.getItems();

            RoomDTO  course = null;
            for (ReservationTM item : items) {
                course = new RoomDTO(item.getRoom_id(),item.getType(),item.getMonthly_rent(),item.getQty());
            }

            StudentDTO student = studentBO.searchStudent(txtSearch.getText());
            ReservationDTO register = new ReservationDTO(lblReservId.getText(),String.valueOf(dpDate.getValue()),payment,student,course);

            if (reservationBO.updateReservation(register)) {
                if (studentBO.saveRegisterDetails(register, register.getStudent().getStudent_id())) {
                    if (roomBO.saveRegisterDetails(register,register.getRoom().getRoom_id())){
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Successful.").showAndWait();

                        txtStudentName.clear();

                        tblReservation.getItems().clear();
                        txtSearch.clear();
                        lblReservId.setText(null);
                        dpDate.setValue(null);
                        chPayment.setSelected(false);
                        cmbRoomId.getSelectionModel().clearSelection();
                        txtRoomType.clear();
                        txtUnitPrice.clear();
                        txtQtyOnHand.clear();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again.").show();
                    }
                }
            }
        }
    }


    public void clearOnAction(ActionEvent actionEvent) {
        txtStudentName.clear();

        tblReservation.getItems().clear();
        txtSearch.clear();
        lblReservId.setText(null);
        dpDate.setValue(null);
        chPayment.setSelected(false);
        cmbRoomId.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();

    }



    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) reservationContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }



}

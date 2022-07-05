package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import entity.Room;
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
import view.TM.RoomTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageRoomController implements Initializable {

    public AnchorPane roomContext;
    public JFXTextField txtRoomId;
    public JFXComboBox <String>cmbRoomType;
    public JFXTextField txtRoomMonthlyRent;
    public JFXTextField txtRoomQty;
    public JFXButton btnSave;

    public TableView<RoomTM> tblRoom;
    public JFXButton btnADD;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);


    int index=-1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateRoomID();

        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblRoom.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("btnupdate"));
        tblRoom.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btndelete"));

        tblRoom.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            index = (int) newValue;
        });

        txtRoomId.setDisable(true);
        cmbRoomType.setDisable(true);
        txtRoomMonthlyRent.setDisable(true);
        txtRoomQty.setDisable(true);

        loadAllRooms();


        ObservableList<String> obList = FXCollections.observableArrayList(
             "Non-AC",
                "Non-AC / Food",
                "AC",
                "AC / Food"
        );
        cmbRoomType.setItems(obList);
    }

    private void generateRoomID() {
        txtRoomId.setText(roomBO.generateRoomID());
    }

    JFXButton buttonDelete;
    public void setDBtn(){
        buttonDelete = new JFXButton();
        buttonDelete.setStyle("-fx-border-color: red");
        Image img = new Image("assets/icons/icons8-delete-64.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonDelete.setGraphic(view);
    }

    JFXButton buttonUpdate;
    public void setUBtn(){
        buttonUpdate = new JFXButton();
        buttonUpdate.setStyle("-fx-border-color: blueviolet");
        Image img = new Image("assets/icons/icons8-edit-48.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonUpdate.setGraphic(view);
    }

    public void setOnActionForDelete(String code){

        buttonDelete.setOnAction((event) -> {
            ButtonType yes= new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no= new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Room?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                if (roomBO.deleteRoom(code)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful.").showAndWait();

                    loadAllRooms();

                    cmbRoomType.getSelectionModel().clearSelection();
                    txtRoomMonthlyRent.clear();
                    txtRoomQty.clear();

                    cmbRoomType.setDisable(true);
                    txtRoomMonthlyRent.setDisable(true);
                    txtRoomQty.setDisable(true);


                    generateRoomID();

                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again.").showAndWait();
                }

            }else{
            }
        });
    }

    public void setOnActionForUpdate(){

        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                RoomTM tm = tblRoom.getSelectionModel().getSelectedItem();

                txtRoomId.setText(tm.getRoom_id());
                cmbRoomType.setValue(tm.getType());
                txtRoomMonthlyRent.setText(String.valueOf(tm.getMonthly_rent()));
                txtRoomQty.setText(String.valueOf(Integer.valueOf(tm.getQty())));


                cmbRoomType.setDisable(false);
                txtRoomMonthlyRent.setDisable(false);
                txtRoomQty.setDisable(false);


                btnADD.setText("Update");

                btnADD.setOnAction(event1 -> {
                    if (roomBO.updateRoom(new RoomDTO(txtRoomId.getText(), cmbRoomType.getValue(), Double.parseDouble(txtRoomMonthlyRent.getText()),Integer.parseInt(txtRoomQty.getText())))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated New Course.").showAndWait();
                        loadAllRooms();

                        cmbRoomType.getSelectionModel().clearSelection();
                        txtRoomMonthlyRent.clear();
                        txtRoomQty.clear();

                        cmbRoomType.setDisable(true);
                        txtRoomMonthlyRent.setDisable(true);
                        txtRoomQty.setDisable(true);


                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Try Again.").show();
                    }
                });
            }
        });
    }




    private void loadAllRooms() {
        ArrayList<RoomDTO> all = roomBO.getAllRooms();
        ObservableList<RoomTM> obList = FXCollections.observableArrayList();

        for (RoomDTO c : all) {

            setUBtn();
            setDBtn();

            obList.add(new RoomTM(c.getRoom_id(),c.getType(),c.getMonthly_rent(),c.getQty(),buttonUpdate,buttonDelete));

            setOnActionForDelete(c.getRoom_id());
            setOnActionForUpdate();
        }
        tblRoom.setItems(obList);
    }



    public void addNewRoomOnAction (ActionEvent actionEvent){
        cmbRoomType.setDisable(false);
        txtRoomMonthlyRent.setDisable(false);
        txtRoomQty.setDisable(false);
    }

    public void addRoomOnAction(ActionEvent actionEvent) {
        if (txtRoomMonthlyRent.getText().isEmpty() || txtRoomQty.getText().isEmpty() || cmbRoomType.getValue() == null){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (roomBO.saveRoom(new RoomDTO(txtRoomId.getText(), cmbRoomType.getValue(), Double.parseDouble(txtRoomMonthlyRent.getText()),Integer.parseInt(txtRoomQty.getText())))){
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added New Room.").showAndWait();

                loadAllRooms();

                cmbRoomType.getSelectionModel().clearSelection();
                txtRoomMonthlyRent.clear();
                txtRoomQty.clear();

                cmbRoomType.setDisable(true);
                txtRoomMonthlyRent.setDisable(true);
                txtRoomQty.setDisable(true);


                generateRoomID();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }
    }

    public void clearRoomOnAction(ActionEvent actionEvent) {
        cmbRoomType.getSelectionModel().clearSelection();
        txtRoomMonthlyRent.clear();
        txtRoomQty.clear();

    }

    public void moveToHome (MouseEvent mouseEvent) throws IOException {
            URL resource = getClass().getResource("../view/MainForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) roomContext.getScene().getWindow();
            window.setScene(new Scene(load));
        }


        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
        Pattern roomIdPattern = Pattern.compile("");
        Pattern typePattern = Pattern.compile("^[A-z ]{1,}$");
        Pattern rentalPattern = Pattern.compile("^[0-9]{1,5}[.][0-9]{1,3}$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,3}$");


        private void storeValidations () {
            map.put(txtRoomId, roomIdPattern);
            map.put(txtRoomMonthlyRent, rentalPattern);
            map.put(txtRoomQty, qtyPattern);
        }

        public void textFields_Key_Released (KeyEvent keyEvent){
            Object response = ValidationUtil.validate(map, btnSave);
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (response instanceof TextField) {
                    TextField errorText = (TextField) response;
                    errorText.requestFocus();
                } else if (response instanceof Boolean) {
                    //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
                }
            }
        }





}


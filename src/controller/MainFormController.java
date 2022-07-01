package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController  implements Initializable {

    @FXML
    public ImageView imgstudent;
    @FXML
    public ImageView imgroom;

    public AnchorPane MainFormContext;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgreservation;
    public ImageView imgSetting;
    public ImageView imglist;
    public Label lblstudentcount;

    private StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    lblstudentcount.setText(String.valueOf(studentBO.countStudent()));

    }




    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgstudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageStudentForm.fxml"));
                    break;
                case "imgroom":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageRoomForm.fxml"));
                    break;

                case "imgreservation":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageReservationForm.fxml"));
                    break;

                case "imglist":
                    root = FXMLLoader.load(this.getClass().getResource("../view/AllListsForm.fxml"));
                    break;

                case "imgSetting":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ForgotPasswordForm.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.MainFormContext.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }
    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgstudent":
                    lblMenu.setText("Manage Students");
                    lblDescription.setText("Click to add, edit, delete, search or view students");
                    break;
                case "imgroom":
                    lblMenu.setText("Manage Rooms");
                    lblDescription.setText("Click to add, edit, delete, search or view rooms");
                    break;

                case "imgreservation":
                    lblMenu.setText("Manage reservation");
                    lblDescription.setText("Click to add, edit, delete, search or view rooms");
                    break;

                case "imglist":
                    lblMenu.setText("View List");
                    lblDescription.setText("Click  Search  Reservations ");
                    break;


                case "imgSetting":
                    lblMenu.setText("Manage Users");
                    lblDescription.setText("Click  change  Username , Password ");
                    break;

            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }
    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }



    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) MainFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }



}

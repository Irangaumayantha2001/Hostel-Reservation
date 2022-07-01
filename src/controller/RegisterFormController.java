package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegisterFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane registerContext;
    public JFXTextField txtUserID;

    private LoginBO loginBO = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.LOGIN);

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty() || txtUserID.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (loginBO.saveLogin(new LoginDTO(txtUserID.getText(), txtUserName.getText(), txtPassword.getText()))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration Successful.").showAndWait();
                txtUserID.clear();
                txtUserName.clear();
                txtPassword.clear();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }
    }

    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) registerContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

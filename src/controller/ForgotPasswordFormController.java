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

public class ForgotPasswordFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane fogetcontext;
    public JFXPasswordField txtConfimPassword;

    private LoginBO loginBO = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.LOGIN);

    public void btnResetOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfimPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (txtPassword.getText().equals(txtConfimPassword.getText())){

                LoginDTO data = loginBO.SearchLogin(txtUserName.getText());
                LoginDTO login = new LoginDTO(data.getUserId(),txtUserName.getText(),txtPassword.getText());

                if (loginBO.updateLogin(login)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Your Password was Changed.").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again.").show();
                }

            }else {
                new Alert(Alert.AlertType.WARNING,"Check Your Password.").show();
            }
        }

    }

    public void moveToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) fogetcontext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

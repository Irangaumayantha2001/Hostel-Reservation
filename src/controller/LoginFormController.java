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

public class LoginFormController {
    public AnchorPane mainFormContext;

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    private LoginBO loginBO= (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.LOGIN);


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        }else {

            LoginDTO data = loginBO.SearchLogin(txtUserName.getText());

            if (data.getUserName().equals(txtUserName.getText()) && data.getPassword().equals(txtPassword.getText())){

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/DashBoardForm.fxml"));
                Parent load = loader.load();
                MainFormController controller = loader.<MainFormController>getController();
                Stage window = (Stage) mainFormContext.getScene().getWindow();
                window.setScene(new Scene(load));
                window.centerOnScreen();
            }else {
                new Alert(Alert.AlertType.WARNING, "Please Check Correct User Detail.").show();
            }
        }
    }

    public void moveSignIn(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) mainFormContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void moveForgetPassword(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ForgotPasswordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) mainFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

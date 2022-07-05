import entity.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utill.FactoryConfiguration;

import java.io.IOException;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Hostel-Reservation");
        primaryStage.centerOnScreen();
        primaryStage.show();




    }
}

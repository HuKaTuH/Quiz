package Login;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import DataBase.DataBase;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.Controller;

public class LoginController extends DataBase implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label ErrorField;

    private void save_data(){
        File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/data.txt");
        try{
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.println(userNameField.getText());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void loginAction(MouseEvent event) throws IOException {
        if(loginVerified(userNameField.getText(),passwordField.getText())){
            ErrorField.setOpacity(0);



            save_data();

            URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/Test.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(fxmlURL);
            root.requestFocus();
            AnchorPane scene = (AnchorPane) loginForm.getParent().getParent();
            scene.getChildren().add(root);

            TranslateTransition transitionTest =
                    new TranslateTransition(Duration.millis(600), root);
            transitionTest.setFromX(680);
            transitionTest.setToX(0);
            transitionTest.setCycleCount(1);
            transitionTest.setAutoReverse(false);

            TranslateTransition transitionLoginForm =
                    new TranslateTransition(Duration.millis(600), loginForm.getParent());
            transitionLoginForm.setFromX(loginForm.getParent().getLayoutX());
            transitionLoginForm.setToX(-680);
            transitionLoginForm.setCycleCount(1);
            transitionLoginForm.setAutoReverse(false);

            ParallelTransition parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(
                    transitionLoginForm,
                    transitionTest
            );
            parallelTransition.setCycleCount(1);
            parallelTransition.play();
            parallelTransition.setOnFinished(e ->{
                scene.getChildren().remove(loginForm.getParent());
            });

        }
        else{
            FadeTransition visible = new FadeTransition(Duration.millis(200), ErrorField);
            visible.setFromValue(0.0f);
            visible.setToValue(1.0f);
            visible.setCycleCount(1);
            visible.setAutoReverse(false);
            visible.play();
        }
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ErrorField.setOpacity(0.0f);
        ErrorField.setText("Username or password is inccorect.");
    }
}

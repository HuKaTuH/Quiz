package Signup;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SignupController extends Controller implements Initializable {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane signupForm;

        @FXML
        private Label signupLogo;

        @FXML
        private TextField userNameField;

        @FXML
        private Label ErrorField;

        @FXML
        private TextField emailField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private PasswordField confirmPasswordField;

        @FXML
        private Button signupButton;

        private void warrningRegistred(String errorText, Color color){
                ErrorField.setTextFill(color);
                ErrorField.setText(errorText);
                FadeTransition visible = new FadeTransition(Duration.millis(200), ErrorField);
                visible.setFromValue(0.0f);
                visible.setToValue(1.0f);
                visible.play();
        }

        /*
            registred return
                0 - username is too short
                1 - user exist
                2 - email format error
                3 - password is to short
                4 - password != confirmPassword
                5 - succes registred
        */

        @FXML
        void signupAction(MouseEvent event) {
                switch (registred(userNameField.getText(), emailField.getText(), passwordField.getText(), confirmPasswordField.getText())){
                        case 0:
                                warrningRegistred("Username is too short",Color.RED);
                                break;
                        case 1:
                                warrningRegistred("Username already exists",Color.RED);
                                break;
                        case 2:
                                warrningRegistred("Invalid format email", Color.RED);
                                break;
                        case 3:
                                warrningRegistred("Password is too short", Color.GREEN);
                                break;
                        case 4:
                                warrningRegistred("Passwords do not match", Color.GREEN);
                                break;
                        case 5:
                                warrningRegistred("Registration completed successfully", Color.GREEN);
                                break;
                }

        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ErrorField.setOpacity(0.0f);
    }
}


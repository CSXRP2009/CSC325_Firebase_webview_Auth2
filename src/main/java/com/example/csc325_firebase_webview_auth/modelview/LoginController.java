package com.example.csc325_firebase_webview_auth.modelview;

import com.example.csc325_firebase_webview_auth.App;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;

    @FXML
    private TextArea result;


    public void login(ActionEvent event) {
        try {
            // https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line

            BufferedReader reader = new BufferedReader(new FileReader(App.filename));
            String line = reader.readLine();
            while (line != null) {
                String[] items = line.split(" ");
                if (items[0].equals(username.getText())) {
                    UserRecord record = App.fauth.getUser(items[1]);
                    result.setText("Welcome " + record.getDisplayName());
                    return;
                }
                line = reader.readLine();
            }

            result.setText("That user doesn't exist!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setText("Login failed!");
        }
    }


    public void page(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AccessFBView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("GitGrub");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}

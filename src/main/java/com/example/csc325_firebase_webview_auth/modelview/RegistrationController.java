package com.example.csc325_firebase_webview_auth.modelview;

import com.example.csc325_firebase_webview_auth.App;
import com.google.firebase.auth.FirebaseAuthException;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;


import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField pNum;
    @FXML
    private TextField eMail;
    @FXML
    private TextField uName;
    @FXML
    private TextField pWord;
    @FXML
    private Button register;
    @FXML
    private Button Login;

    public boolean reg(ActionEvent event) {
      //  System.out.println("Register User");
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(eMail.getText())
                .setEmailVerified(false)
                .setPassword(pWord.getText())
                .setPhoneNumber(pNum.getText())
                .setDisplayName(fName.getText()+" "+ lName.getText())
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = App.fauth.createUser(request);
            BufferedWriter output = new BufferedWriter(new FileWriter("users.txt", true));
            output.write(userRecord.getEmail() + " " + userRecord.getUid());
            output.close();
            System.out.println("Successfully created new user: " + userRecord.getUid());
            App.setRoot("AccessFBView.fxml");
            return true;

        } catch (FirebaseAuthException | IOException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
    }

    public void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("GitGrub");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

}

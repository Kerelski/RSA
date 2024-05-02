package org.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RsaViewController {

    @FXML
    private Button exit;

    @FXML
    private TextField keyE;

    @FXML
    private TextField keyD;

    @FXML
    private TextField keyN;

    @FXML
    private Button keyButton;

    @FXML
    private TextField keyFileName;

    @FXML
    private Button keySaveFile;

    @FXML
    private Button keyLoadButton;

    @FXML
    private TextArea cipherArea;

    @FXML
    private Button encodeButton;

    @FXML
    private Button decodeButton;

    @FXML
    private TextArea plainTextArea;

    @FXML
    private TextField plainTextFileInput;

    @FXML
    private TextField cipherFileInput;

    @FXML
    private Button plainTextFileButton;

    @FXML
    private Button cipherFileButton;

    @FXML
    private TextField plainTextSaveInput;

    @FXML
    private Button plainTextSaveButton;

    @FXML
    private TextField cipherSaveInput;

    @FXML
    private Button cipherSaveButton;

    @FXML
    private Button plainTextButton;

    @FXML
    private Button cipherAreaButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void onExitClick(MouseEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

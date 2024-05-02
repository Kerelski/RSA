package org.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.model.*;

import java.math.BigInteger;
import java.security.spec.ECField;

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

    private Keys keys = new Keys();

    BigInteger[] plainText;
    BigInteger cipher;
    public void generateKeys(MouseEvent event){
        keys.generateKeys();
        keyE.setText(keys.getE().toString());
        keyD.setText(keys.getD().toString());
        keyN.setText(keys.getN().toString());

    }

    public void loadKeys(MouseEvent event) throws Exception {
        String fileName = keyFileName.getText();
        FileReader file = new FileReader(fileName);
        file.readKey();
        keys.setE(file.getKeys()[0]);
        keys.setD(file.getKeys()[1]);
        keys.setN(file.getKeys()[2]);
        keyE.setText(keys.getE().toString());
        keyD.setText(keys.getD().toString());
        keyN.setText(keys.getN().toString());

    }

    public void saveKeys(MouseEvent event)throws Exception{
        String fileName = keyFileName.getText();
        FileWriter file = new FileWriter(fileName);
        BigInteger[] keysToSave = new BigInteger[3];
        keysToSave[0] = keys.getE();
        keysToSave[1] = keys.getD();
        keysToSave[2] = keys.getN();
        file.writeKeys(keysToSave);
    }

    public void loadPlainTextFile(MouseEvent e) throws Exception{
        FileReader file = new FileReader(plainTextFileInput.getText());
        file.read(keys.getN().bitLength());
        String text = new String(file.getBytes());
        plainTextArea.setText(text);
        plainText = file.getMessage();
    }

    @FXML
    void onExitClick(MouseEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

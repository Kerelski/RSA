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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    PlainText plainText;
    BigInteger[] cipher;

    public void generateKeys(MouseEvent event){
        keys.generateKeys();
        keyE.setText(keys.getE().toString());
        keyD.setText(keys.getD().toString());
        keyN.setText(keys.getN().toString());

    }

    public void loadKeys(MouseEvent event) throws Exception {
        String fileName = keyFileName.getText();
        FileReader file = new FileReader(fileName);
        file.readBigIntegers();
        keys.setE(file.getBigIntegers().get(0));
        keys.setD(file.getBigIntegers().get(1));
        keys.setN(file.getBigIntegers().get(2));
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
        file.writeBigInteger(keysToSave);
    }

    public void savePlainText(MouseEvent event) throws Exception {
        String fileName = plainTextSaveInput.getText();
        FileWriter file = new FileWriter(fileName);
        file.write(plainText.getMessageInBytes());
    }

    public void saveCipher(MouseEvent e) throws Exception {
        String fileName = cipherSaveInput.getText();
        FileWriter file = new FileWriter(fileName);
        file.writeBigInteger(cipher);
    }

    public void loadCipher(MouseEvent e) throws Exception {
        String fileName = cipherFileInput.getText();
        FileReader file = new FileReader(fileName);
        file.readBigIntegers();
        cipher = file.getBigIntegers().toArray(new BigInteger[0]);
        StringBuilder text = new StringBuilder();
        for(BigInteger bInt : cipher){
            text.append(bInt);
        }
        cipherArea.setText(text.toString());
    }

    public void loadCipherArea(MouseEvent e){
        String encryptedText = cipherArea.getText();
        String[] cipherBlocks = encryptedText.split("\n");

        int length = cipherBlocks.length;
        BigInteger[] cipherTemp = new BigInteger[length];
        for(int i = 0; i<length; i++){
            cipherTemp[i] = new BigInteger(cipherBlocks[i]);
        }
        cipher = cipherTemp;

    }

    public void loadPlainTextFile(MouseEvent e) throws Exception{
        FileReader plainTextFile = new FileReader(plainTextFileInput.getText());
        plainTextFile.read();
        plainText = new PlainText(Base64.getEncoder().encode(plainTextFile.getBytes()), keys.getN().bitLength());
        String text = new String(Base64.getDecoder().decode(plainText.getMessageInBytes()));
        plainTextArea.setText(text);
    }

    public void loadPlainTextArea(MouseEvent e){
        String plaintextInput = plainTextArea.getText();
        byte[] byteArray = plaintextInput.getBytes();
        plainText = new PlainText(Base64.getEncoder().encode(byteArray), keys.getN().bitLength());

    }

    public void encoding(MouseEvent e){
        cipher = RSA.encode(plainText.getMessageInBigIntegers(), keys.getE(), keys.getN());
        StringBuilder text = new StringBuilder();
        for(BigInteger bInt : cipher){
            text.append(bInt);
        }
        cipherArea.setText(text.toString());

    }

    public void decoding(MouseEvent e){
        BigInteger[] bigIntegers = RSA.decode(cipher, keys.getD(), keys.getN());

        byte[] byteArray = bigIntegersToBytes(bigIntegers);

        plainText = new PlainText(Base64.getDecoder().decode(byteArray), keys.getN().bitLength());
        String text = new String(plainText.getMessageInBytes());
        plainTextArea.setText(text);
    }

    @FXML
    void onExitClick(MouseEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    private byte[] bigIntegersToBytes(BigInteger[] message){
        List<Byte> byteList = new ArrayList<>();

        for (BigInteger bigInteger : message) {
            byte[] bytes = bigInteger.toByteArray();
            for (byte b : bytes){
                byteList.add(b);
            }
        }
        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }
        return byteArray;
    }
}

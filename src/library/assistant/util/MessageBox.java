/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.util;

import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class MessageBox {

    public static void showMessage(String title,String message) {

    }

    public static void showAndWaitMessage(String title,String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }

    public static void showConfirmMessage(String title,String message) {

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;

/**
 * FXML Controller class
 *
 * @author 20160606
 */
public class SceneController implements Initializable {

    //übersicht
    @FXML
    private TabPane main_pain;
    @FXML
    private Tab hardware_tab;
    @FXML
    private Tab system_tab;
    
    //hardware
    @FXML
    private TreeView<String> hardware_view;
    
    //system
    @FXML
    private TitledPane verwendeter_lizenzumfang_pane;
    @FXML
    private TabPane benutzer_pane;
    @FXML
    private TitledPane bereiche_pane;
    
    //lizenz
    @FXML
    private TableView<?> lizenz_view;
    
    @FXML
    private TableColumn<?, ?> leistungsmerkmal;
    @FXML
    private TableColumn<?, ?> maximalwert;
    @FXML
    private TableColumn<?, ?> bereits_benutzt;
    
    //benutzer
    @FXML
    private Tab benutzer_tab;
    
    @FXML
    private ListView<?> benutzer_view;
    @FXML
    private Tab allgemeines_tab;
    @FXML
    private PasswordField pin;
    @FXML
    private TextField is_code;
    
    @FXML
    private Tab raum_zeitzonen_tab;
    @FXML
    private CheckBox mo;
    @FXML
    private CheckBox di;
    @FXML
    private CheckBox mi;
    @FXML
    private CheckBox don;
    @FXML
    private CheckBox fr;
    @FXML
    private CheckBox sa;
    @FXML
    private CheckBox so;
    @FXML
    private CheckBox feier;
    
    //bereiche
    @FXML
    private TreeView<?> bereiche_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

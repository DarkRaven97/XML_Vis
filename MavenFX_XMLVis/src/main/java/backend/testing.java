/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20150210 Klaus Gravogel 3AHIF
 */
public class testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            XMLObjects.readAllFromFile(Paths.get("panel.xml")).forEach(System.out::println);
        } catch (IOException ex) {
            Logger.getLogger(testing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

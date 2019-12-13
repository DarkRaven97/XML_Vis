/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.Collection;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class XMLObjects {

    public static IXMLObject testFile(){
        return TestObject.getTestRoot();
    }
    
    public static IXMLObject readFile(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Collection<LicenceLine> readLicence(String licenceFile,String p){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

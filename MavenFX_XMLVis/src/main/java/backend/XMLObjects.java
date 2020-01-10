/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class XMLObjects {

    public static IXMLObject testFile() {
        return TestObject.getTestRoot();
    }

    /**
     * if the panel.xml is not in the Root directory of the programm, please use the readPanel(Path panelPath)
     * @param panelName 
     * @return the root object of the panel.xml (Zentrale)
     */
    public static IXMLObject readPanel(String panelName) {
        return readPanel(Paths.get(panelName));
    }

    /**
     * 
     * @param panelPath
     * @return the root object of the panel.xml (Zentrale)
     */
    public static IXMLObject readPanel(Path panelPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * if the facility.xml is not in the Root directory of the programm, please use the readfacility(Path facilityPath)
     * @param facilityName
     * @return all the root objects of the facility.xml
     */
    public static Collection<IXMLObject> readFacility(String facilityName) {
        return readFacility(Paths.get(facilityName));
    }

    /**
     *
     * @param facilityPath
     * @return all root objects of the facility (Villa)
     */
    public static Collection<IXMLObject> readFacility(Path facilityPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * if the file is not in the Root directory of the Programm, please use the readUser(Path panelPath)
     * @param panelName
     * @return all user data found in the given file
     */
    public static Collection<IXMLObject> readUser(String panelName) {
        return readUser(Paths.get(panelName));
    }

    /**
     * 
     * @param panelPath
     * @return all user data found in the given file
     */
    public static Collection<IXMLObject> readUser(Path panelPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * if the files are not in the root of the programm you should use the
     * readLicence using Paths rather than this one
     *
     * @param licenceFile Filename of the licence.xml file
     * @param facility Filename of the facility.xml file
     * @return all the relevent licence information
     */
    public static Collection<LicenceLine> readLicence(String licenceFile, String facility) {
        return readLicence(Paths.get(licenceFile), Paths.get(facility));
    }

    /**
     *
     * @param licenceFile Path of the licence.xml file
     * @param facility Path of the facility.xml file
     * @return all the relevant licence information
     */
    public static Collection<LicenceLine> readLicence(Path licenceFile, Path facility) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class XMLObjects {

    public static IXMLObject testFile() {
        return TestObject.getTestRoot();
    }

    /**
     * if the panel.xml is not in the Root directory of the programm, please use
     * the readPanel(Path panelPath)
     *
     * @param panelName
     * @return the root object of the panel.xml (Zentrale)
     */
    public static IXMLObject readPanel(String panelName) throws IOException{
        return readPanel(Paths.get(panelName));
    }

    /**
     *
     * @param panelPath
     * @return the root object of the panel.xml (Zentrale)
     */
    public static IXMLObject readPanel(Path panelPath) throws IOException{
        return (IXMLObject) createTree(panelPath, true, "User").toArray()[0];
    }

    /**
     * if the facility.xml is not in the Root directory of the programm, please
     * use the readfacility(Path facilityPath)
     *
     * @param facilityName
     * @return all the root objects of the facility.xml
     */
    public static Collection<IXMLObject> readFacility(String facilityName) throws IOException{
        return readFacility(Paths.get(facilityName));
    }

    /**
     *
     * @param facilityPath
     * @return all root objects of the facility (Villa)
     */
    public static Collection<IXMLObject> readFacility(Path facilityPath) throws IOException{
        return createTree(facilityPath, true, "User");
    }

    /**
     * if the file is not in the Root directory of the Programm, please use the
     * readUser(Path panelPath)
     *
     * @param panelName
     * @return all user data found in the given file
     */
    public static Collection<IXMLObject> readUser(String panelName) throws IOException {
        return readUser(Paths.get(panelName));
    }

    /**
     *
     * @param panelPath
     * @return all user data found in the given file
     */
    public static Collection<IXMLObject> readUser(Path panelPath) throws IOException{
        return createTree(panelPath, false, "User");
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

    /**
     *
     * @param path
     * @return
     * @throws backend.exceptions.ParseException
     */
    public static Collection<IXMLObject> readAllFromFile(Path path) throws IOException {
        try {
            return collect(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path.toFile()).getChildNodes());
        } catch (ParserConfigurationException | SAXException ex) {
            throw new IOException(ex);
        }
    }

    private static Collection<IXMLObject> collect(NodeList nl) {
//        System.out.println("<---------------------------------------------------------------\n");
        Collection<IXMLObject> erg = new HashSet<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node h = nl.item(i);
            if (childs(h.getChildNodes())) {
                erg.addAll(collect(h.getChildNodes()));
            } else if (h.hasChildNodes()) {
                XMLObject xo = new XMLObject(h.getNodeName());
                erg.add(xo);
//                System.out.println(h.getParentNode() + " " + h + " " + h.getChildNodes().item(0));
                String tagName = cutStrings(h.toString());
                String tagValue = cutStringValue(h.getChildNodes().item(0).toString());
                xo.addTag(tagName, tagValue);

//                String bonusTag = XSL.get(tagValue);
//                if (Objects.nonNull(bonusTag)) {
//                    xo.addTag("XSLString", bonusTag);
//                }
            }
        }
//        System.out.println("--------------------------------------------------------------->\n");
//        other.addAll(erg);
//        names.addAll(erg.stream().filter(a -> a.isNameObject()).collect(Collectors.toList()));
//        other.retainAll(names);
        return erg;
    }

    private static boolean childs(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).hasChildNodes()) {
                return true;
            }
        }
        return false;
    }

    private static String cutStringValue(String s) {
        return s.substring(s.indexOf(' ') + 1, s.length() - 1);
    }

    private static String cutStrings(String s) {
        return s.substring(1, s.indexOf(' ') - 1);
    }

    /**
     * Creates a tree structure from unstructured IXMLObjects
     *
     * @param objects
     * @return a collection of the root Objects
     */
    public static Collection<IXMLObject> createTree(Collection<IXMLObject> objects) {
        objects.forEach((object) -> {
            getChildren(objects, object);
        });
        return (Collection<IXMLObject>) objects.parallelStream().filter(t -> ((IXMLObject) t).isRoot()).collect(Collectors.toSet());
    }

    /**
     * Creates a tree structure from unstructured IXMLObjects
     *
     * @param objects
     * @param ignoreTypes True-> ignores all types given in the types String
     * array False-> ignores all types except types given types String array
     * @param types
     * @return a collection of the root Objects
     */
    public static Collection<IXMLObject> createTree(Collection<IXMLObject> objects, boolean ignoreTypes, String... types) {
        Collection<String> t = Arrays.asList(types);
        return createTree(objects.parallelStream().filter(a -> ignoreTypes ? !t.contains(a.getType()) : t.contains(a.getType())).collect(Collectors.toSet()));
    }

    /**
     * creates a tree structure from a file
     *
     * @param path
     * @return a collection of the root Objects
     * @throws java.io.IOException
     */
    public static Collection<IXMLObject> createTree(Path path) throws IOException {
        return createTree(readAllFromFile(path));
    }

    /**
     * Creates a tree structure from file
     *
     * @param path
     * @param ignoreTypes True-> ignores all types given in the types String
     * array False-> ignores all types except types given types String array
     * @param types
     * @return a collection of the root Objects
     */
    public static Collection<IXMLObject> createTree(Path path, boolean ignoreTypes, String... types) throws IOException {
        return createTree(readAllFromFile(path), ignoreTypes, types);
    }

    public static Collection<IXMLObject> getChildren(Collection<IXMLObject> objects, IXMLObject parent) {
        objects.forEach((object) -> {
            parent.getTags().values().parallelStream()
                    .filter((value) -> (object.getUID().toString().equals(value)))
                    .forEachOrdered((item) -> {
                        parent.addChildren(object);
                    });
        });
        return parent.getChildren();
    }
}

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
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NamedNodeMap;
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
    public static IXMLObject readPanel(String panelName) throws IOException {
        return readPanel(Paths.get(panelName));
    }

    /**
     *
     * @param panelPath
     * @return the root object of the panel.xml (Zentrale)
     */
    public static IXMLObject readPanel(Path panelPath) throws IOException {
        return (IXMLObject) createTree(panelPath, true, "User").toArray()[0];
    }

    /**
     * if the facility.xml is not in the Root directory of the programm, please
     * use the readfacility(Path facilityPath)
     *
     * @param facilityName
     * @return all the root objects of the facility.xml
     */
    public static Collection<IXMLObject> readFacility(String facilityName) throws IOException {
        return readFacility(Paths.get(facilityName));
    }

    /**
     *
     * @param facilityPath
     * @return all root objects of the facility (Villa)
     */
    public static Collection<IXMLObject> readFacility(Path facilityPath) throws IOException {
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
    public static Collection<IXMLObject> readUser(Path panelPath) throws IOException {
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
     */
    public static Collection<IXMLObject> readAllFromFile(Path path) throws IOException {
        try {
            return collect(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path.toFile()));
        } catch (ParserConfigurationException | SAXException ex) {
            throw new IOException(ex);
        }
    }
private static final void textOutput(String what,String out){
    out = out.replaceAll("\\\\", "\\\\\\\\");
    System.out.println(what+": "+out);
}
    private static Collection<Node> out(Node parent) {
        System.out.println("Starting out");
        Collection<Node> erg = new LinkedList();
        try{
        NodeList nl = parent.getChildNodes();
        for (int i = 0; i <= nl.getLength(); i++) {
            System.out.println("Start of Loop "+i);
            Node n = nl.item(i);
            
            textOutput("Node", n.toString());
            textOutput("Type", ""+n.getNodeType());
            if (n.hasChildNodes()) {
               erg.addAll(out(n));
            } else if(!n.getNodeValue().startsWith("\n")){
                erg.add(n);
                System.out.println("Adding "+n);
            }
            System.out.println("End of Loop "+i);
        }
        } catch(NullPointerException npe){
            System.out.println("NullPointerException "+npe.getMessage());
        }
        return erg;
    }

    /*
    private static Collection<IXMLObject> collect(Node nf){
        for (int i = 0; i < nf.; i++) {
            nf.normalize();
        }
    }
     */
    private static Collection<IXMLObject> collect(Node nf) {
        NodeList nl = nf.getChildNodes();
        Collection<Node> c = out(nf);
        System.out.println(c.size());
//        System.out.println("<---------------------------------------------------------------\n");
        Collection<IXMLObject> erg = new TreeSet<>();

        for (int i = 0; i <= nl.getLength(); i++) {
            Node n = nl.item(i);
            if (Objects.isNull(n)) {
                continue;
            } else if (!isObject(n)) {
                erg.addAll(collect(n));
            } else {
                XMLObject xo = new XMLObject(n.getNodeName());
                erg.add(xo);
                for (int j = 1; j <= n.getChildNodes().getLength(); j++) {
                    Node h2 = n.getChildNodes().item(j);
                    String tagName = h2.getNodeName();
                    String tagValue = h2.getFirstChild().getNodeValue();
                    xo.addTag(tagName, tagValue);
                }

//                System.out.println(h.getParentNode() + " " + h + " " + h.getChildNodes().item(0));
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

    private static boolean isLine(Node n) {
        if (Objects.isNull(n)) {
            return false;
        }
        Node h = n.getFirstChild();
//        if (Objects.nonNull(h) && Objects.nonNull(h.getNodeValue()) && h.getNodeValue().startsWith("\n")) {
//            return isLine(h.getNextSibling());
//        }
        return h.hasAttributes();

    }

    private static boolean isObject(Node n) {
       return n.getTextContent().contains("");
    }

    private static boolean childs(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).hasChildNodes()) {
                return true;
            }
        }
        return false;
    }

    private static boolean childs2(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            if (childs(nl.item(i).getChildNodes())) {
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
        objects.parallelStream().forEach(object -> {

        });
        return parent.getChildren();
    }
}

interface testNode {
    boolean test(Node n);
}

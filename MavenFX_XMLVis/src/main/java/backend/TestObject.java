/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import javafx.scene.image.Image;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class TestObject implements IXMLObject {

    public static TestObject getTestRoot() {
        TestObject erg = new TestObject("Villa", "Root", 1, new Image(Paths.get("TestIcons", "split_0.jpg").toString()));
        TestObject bus1 = new TestObject("bus1", "bus", 20, new Image(Paths.get("TestIcons", "split_1.jpg").toString()));
        TestObject bus2 = new TestObject("bus2", "bus", 32, new Image(Paths.get("TestIcons", "split_1.jpg").toString()));
        TestObject bus3 = new TestObject("bus3", "bus", 46, new Image(Paths.get("TestIcons", "split_1.jpg").toString()));
        TestObject bus4 = new TestObject("bus4", "bus", 5, new Image(Paths.get("TestIcons", "split_1.jpg").toString()));
        TestObject keypad1 = new TestObject("keypad1", "keypad", 496843, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        TestObject keypad2 = new TestObject("keypad2", "keypad", 987654, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        TestObject keypad3 = new TestObject("keypad3", "keypad", 135873, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        TestObject keypad4 = new TestObject("keypad4", "keypad", 987535, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        TestObject keypad5 = new TestObject("keypad5", "keypad", 987465, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        TestObject keypad6 = new TestObject("keypad6", "keypad", 321654, new Image(Paths.get("TestIcons", "split_2.jpg").toString()));
        erg.addChildren(bus1,bus2,bus3,bus4);
        bus1.addChildren(keypad1,keypad2);
        bus2.addChildren(keypad3);
        bus3.addChildren(keypad6);
        bus3.addChildren(keypad4,keypad5);
        putTestTags(erg);
        return erg;
    }
    private String name;
    private String type;
    private Integer uID;
    private Map<String, String> tags;
    private Collection<IXMLObject> children;
    private Image icon;
    private TestObject parent;
    private Collection<Integer> fk;

    private static void putTestTags(TestObject to) {
        to.addTag("test", "testValue");
        to.addTag("TagName", "TagValue");
        to.addTag("Something", "else");
        if (to.hasChildren()) {
            to.getChildren().forEach(new Consumer() {
                @Override
                public void accept(Object t) {
                    putTestTags(t);
                }
            });
        }
    }

    public TestObject() {
    }

    public TestObject(String name, String type, Integer uID, Map<String, String> tags, Collection<IXMLObject> children, Image icon, TestObject parent, Collection<Integer> fk) {
        this.name = name;
        this.type = type;
        this.uID = uID;
        this.tags = tags;
        this.children = children;
        this.icon = icon;
        this.parent = parent;
        this.fk = fk;
    }

    public TestObject(String name, String type, Integer uID, Image icon, TestObject parent) {
        this.name = name;
        this.type = type;
        this.uID = uID;
        this.icon = icon;
        this.parent = parent;
    }

    public TestObject(String name, String type, Integer uID, Image icon) {
        this.name = name;
        this.type = type;
        this.uID = uID;
        this.icon = icon;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Integer getUID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    @Override
    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public Collection<IXMLObject> getChildren() {
        return children;
    }

    public void setChildren(Collection<IXMLObject> children) {
        this.children = children;
    }

    @Override
    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    @Override
    public TestObject getParent() {
        return parent;
    }

    public void setParent(TestObject parent) {
        this.parent = parent;
    }

    @Override
    public Collection<Integer> getForeignKeys() {
        return fk;
    }

    @Override
    public void addTag(String tagName, String tagValue) {
        tags.put(tagName, tagValue);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public void addChildren(IXMLObject child) {
        child.setParent(this);
        children.add(child);
    }

    public void setFk(Collection<Integer> fk) {
        this.fk = fk;
    }

    public void addFk(Integer n) {
        fk.add(n);
    }

    @Override
    public void addChildren(IXMLObject... children) {
        for (IXMLObject iXMLObject : children) {
            iXMLObject.setParent(this);
        }
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public void setParent(IXMLObject parent) {
    this.parent=(TestObject) parent;
    }
}

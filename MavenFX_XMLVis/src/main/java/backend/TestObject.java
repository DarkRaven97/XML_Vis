/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.Collection;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class TestObject implements IXMLObject {

    private String name;
    private String type;
    private Integer uID;
    private Map<String, String> tags;
    private Collection<IXMLObject> children;
    private Image icon;
    private TestObject parent;
    private Collection<Integer> fk;

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
        children.add(child);
    }

    public void setFk(Collection<Integer> fk) {
        this.fk = fk;
    }

    public void addFk(Integer n){
        fk.add(n);
    }
}

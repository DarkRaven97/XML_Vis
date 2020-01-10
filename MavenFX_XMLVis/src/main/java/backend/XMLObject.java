/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author 20150210 Gravogel Klaus 3AHIF
 */
public class XMLObject implements IXMLObject {

    private final String tagName;
    private Integer uid;
    private String name;
    private String type;
    private final Map<String, String> tags = new HashMap<>();
    private final Collection<IXMLObject> children = new HashSet<>();
    private Collection<Integer> foreignKeys = new HashSet<>();
    private Image icon;

    public XMLObject(String tagName) {
        this.tagName = tagName;
    }

    public XMLObject(String tagName, Integer uid) {
        this.tagName = tagName;
        this.uid = uid;
    }

    public XMLObject(String tagName, Integer uid, String name) {
        this.tagName = tagName;
        this.uid = uid;
        this.name = name;
    }

    public XMLObject(String tagName, Integer uid, String name, String type) {
        this.tagName = tagName;
        this.uid = uid;
        this.name = name;
        this.type = type;
    }

    public XMLObject(String tagName, Integer uid, String name, String type, Image icon) {
        this.tagName = tagName;
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.icon = icon;
    }

    public String getTagName() {
        return tagName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    @Override
    public Collection<Integer> getForeignKeys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> getTags() {
        return this.tags;
    }

    @Override
    public void addTag(String tagName, String tagValue) {
        if (tagName.equalsIgnoreCase("UID")) {
            uid = Integer.parseInt(tagValue);
        } else {
            try {
                int h = Integer.parseInt(tagValue);
                foreignKeys.add(h);
            } catch (NumberFormatException nfe) {

            }
        }
        tags.put(tagName, tagValue);
    }

    @Override
    public boolean hasChildren() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<IXMLObject> getChildren() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChildren(IXMLObject child) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChildren(IXMLObject... children) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IXMLObject getParent() throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParent(IXMLObject parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.tagName);
        hash = 17 * hash + Objects.hashCode(this.uid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XMLObject other = (XMLObject) obj;
        if (!Objects.equals(this.tagName, other.tagName)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        return true;
    }

    @Override
    public Integer getUID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

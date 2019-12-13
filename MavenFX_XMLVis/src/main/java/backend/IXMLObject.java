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
 * @author 20150210
 */
public interface IXMLObject {
    /**
     * 
     * @return 
     */
    public String getName();
    
    /**
     * 
     * @return 
     */
    public String getType();
    
    /**
     * 
     * @return 
     */
    public Integer getUID();
    
    /**
     * 
     * @return 
     */
    public Collection<Integer> getForeignKeys();
    
    /**
     * Get all tags from the XML object that can't be Identified as Name or ID
     * @return a Map containing <Tagname,TagValue>
     */
    public Map<String,String> getTags();
    
    /**
     * 
     * @param tagName
     * @param tagValue 
     */
    public void addTag(String tagName,String tagValue);
    
    /**
     * 
     * @return weather or not this object has any Child-Objects in the Hirachy
     */
    public boolean hasChildren();
    
    /**
     * 
     * @return the Child-Objects in the Hirachy, or an empty collection if the Object doesn't have any children
     */
    public Collection<IXMLObject> getChildren();
    
    /**
     * 
     * @param child 
     */
    public void addChildren(IXMLObject child);
    
    /**
     * 
     * @return Parent-Object in the Hirachy
     * @throws IllegalStateException if it's a root Object
     */
    public IXMLObject getParent() throws IllegalStateException;
    
    /**
     * 
     * @return an 255x255 Icon to represent the Object
     */
    public Image getIcon();
}

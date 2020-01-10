/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author dark
 */
public interface LicenceLine {

    Integer getMax();

    Integer getUsed();

    /**
     *
     * @return if the Licence has a boolean answer, if this is true treat the
     * return value of getMax and getUsed as boolean (0=>false,anything else =>
     * true)
     */
    boolean getBool();

    String getName();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hut.plugin.log;

/**
 * Cho phép dùng phương thức static lấy được tên của một class.
 * @author DatTT 
 */
public class CurrentClassGetter extends SecurityManager {

    /**
     * Lấy tên class
     * @return Trả về tên class
     */
    public String getClassName() {
        return getClassContext()[1].getName();
    }
}

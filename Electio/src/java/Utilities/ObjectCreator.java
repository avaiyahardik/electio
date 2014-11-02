/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Controller.*;

/**
 *
 * @author Hardik
 */
public class ObjectCreator {

    public static Object createObject(String class_name) {
        Object object = null;
        try {
            Class name = Class.forName(class_name);
            object = name.newInstance();
        } catch (Exception e) {
            System.out.println("Object Creator Error: " + e);
        }

        return object;
    }

}

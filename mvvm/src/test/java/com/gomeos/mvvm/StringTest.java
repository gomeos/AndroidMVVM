package com.gomeos.mvvm;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by wwish on 16/9/6.
 */
public class StringTest {
    @Test
    public void getInt(){
        System.out.println(10 >>> 3);

        System.out.println(10 >> 3);
        System.out.println("-----------");
        System.out.println(-10 >>> 3);
        System.out.println(-10 >> 3);
        System.out.println("-----------");
        System.out.println(MyRunner.class.getName());
        String s = MyRunner.class.getName();
        Class<?> test = null;
        try {
            test = Class.forName(s);
            System.out.println(test.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            MyRunner mr = (MyRunner) test.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        File f = new File("fruit.properties");
        if (f.exists()) {
            pro.load(new FileInputStream(f));
        } else {
            pro.setProperty("apple", "Reflect.Apple");
            pro.setProperty("orange", "Reflect.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }

}

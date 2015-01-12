/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcdscodes.gui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import vcdscodes.core.VCDSCache;
import vcdscodes.core.VCDSContentProvider;
import vcdscodes.core.constants.VCDSConstants;

/**
 *
 * @author ChillToucH
 */
public class ActionListener implements java.awt.event.ActionListener {

    private static ActionListener instance;

    private ActionListener() {
    }

    public static ActionListener getInstance() {
        if (instance == null) {
            instance = new ActionListener();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        if (but.getText().equals(VCDSConstants.SEARCH_BUTTON_TITLE)) {
            String errorCode = MenuModule.getInstance().getMainMenu().getErrorCodeText();
            if(errorCode == null || errorCode.equals("")) {
                errorCode = "No error code";
            } else {
//                errorCode += ".txt";
            }
            MenuModule.getInstance().getMainMenu().setResult(VCDSContentProvider.getData(errorCode));
            System.out.println("Error code: " + errorCode + " data from cache: " + VCDSContentProvider.getData(errorCode));
            JOptionPane.showMessageDialog(null, errorCode);
        }
        else if (but.getText().equals(VCDSConstants.CLEAN_HTML_BUTTON)) {
            try {
                openFiles();
                VCDSCache.loadCache();
            } catch (Exception ex) {
                Logger.getLogger(ActionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void openFiles() throws Exception {
        File folder = new File(VCDSConstants.CURRENT_RES_DIR);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            throw new Exception("No data found!");
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    String text = new Scanner(new File(file.getAbsolutePath())).useDelimiter("\\A").next();
                    String data = Jsoup.parse(text).text();
                    String fileName = file.getName();
                    file.delete();
                    System.out.println(data);
                    save(fileName, data);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        VCDSConstants.CURRENT_RES_DIR = System.getProperty("user.dir") + "\\resourses\\";
        VCDSCache.updatePropertie("formated", "true");
    }
    
    public static void save(String errorCode, String data) {
        PrintWriter out = null;
        VCDSConstants.CURRENT_RES_DIR = System.getProperty("user.dir") + "\\resourses\\";
        try {
            out = new PrintWriter(VCDSConstants.CURRENT_RES_DIR);
            out.println(data);
            out.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ActionListener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             out.close();
        }
        VCDSConstants.CURRENT_RES_DIR = System.getProperty("user.dir") + "\\res\\";
    }

}

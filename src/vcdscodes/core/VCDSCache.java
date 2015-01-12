package vcdscodes.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vcdscodes.core.constants.VCDSConstants;

/**
 *
 * @author ChillToucH
 */
public class VCDSCache  {

    private static VCDSCache instance;
    private static HashMap<String, String> cache;
    private static HashMap<String, Object> settings;
    
    public static volatile boolean isLoaded = false;
    private static Object properties;
    
    private VCDSCache() {
    }

    public static VCDSCache getInstance() {
        if (instance == null) {
            instance = new VCDSCache();
            isLoaded = false;
        }
        return instance;
    }

    public static void loadCache() throws Exception {
        isLoaded = false;
        cache = new HashMap<>();
        File folder = new File(VCDSConstants.CURRENT_RES_DIR);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles == null) {
            throw new Exception("No data found! App path: " + System.getProperty("user.dir"));
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    String text = new Scanner( new File(file.getAbsolutePath()) ).useDelimiter("\\A").next();
                    if(!isCached(file.getName())) {
                        putInCache(file.getName().toLowerCase(), text);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VCDSCache.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        isLoaded = true;
    }
    private static String path = System.getProperty("user.dir") + "\\settings\\settings.properties";
    public static void loadSettings() {
        try {
            settings = new HashMap<>();
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
            Enumeration e = properties.propertyNames();
            while(e.hasMoreElements()) {
                String key = (String) e.nextElement();
                settings.put(key, properties.getProperty(key));
                System.out.println("Propertie for key: " + key + " value: " + settings.get(key));
            }
        } catch (IOException ex) {
            Logger.getLogger(VCDSCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updatePropertie(String key, Object value) {
        FileOutputStream out = null;
        try {
            Properties props = null;
            out = new FileOutputStream(path);
            props.setProperty(key, (String) value);
            props.store(out, null);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VCDSCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VCDSCache.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(VCDSCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Object getSettingValue(String key) {
        System.out.println("Key: " + key + " value: " + settings.get(key));
        return settings.get(key);
    }
    
    public static String getDataFromCache(String key) {
        return cache.get(key.toLowerCase());
    }
    
    public static boolean isCached(String key) {
        return cache.containsKey(key);
    }
    
    protected static void putInCache(String key, String data) {
        cache.put(key.toLowerCase(), data);
    }
}

package vcdscodes.core;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ChillToucH
 */
public class VCDSParser extends Thread{
    private static VCDSParser instance;
    private VCDSParser(){}
    private String stringToParse;
    
    public static VCDSParser getInstance() {
        if(instance == null)
            instance = new VCDSParser();
        return instance;
    }

    public void setStringToParse(String str) {
        this.stringToParse = str;
    }
    
    @Override
    public void run() {
        if(stringToParse == null)
            stopThread(this);

    }
    
    public void stopThread(Thread thread){
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(VCDSParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

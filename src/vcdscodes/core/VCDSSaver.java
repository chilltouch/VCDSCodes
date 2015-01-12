package vcdscodes.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import vcdscodes.core.constants.VCDSConstants;

/**
 *
 * @author ChillToucH
 */
public class VCDSSaver {
    public static void save(String errorCode, String data) {
        try {
            PrintWriter out = new PrintWriter(VCDSConstants.CURRENT_RES_DIR + errorCode);
            out.println(data);
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VCDSSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package vcdscodes;

import javax.swing.JOptionPane;
import vcdscodes.core.VCDSCache;
import vcdscodes.core.VCDSDownload;
import vcdscodes.core.constants.VCDSConstants;
import vcdscodes.gui.MenuModule;

/**
 *
 * @author ChillToucH
 */
public class VCDSCodes {

    public static void main(String[] args) {
//        System.out.println(VCDSDownload.getParsedContent());
        VCDSCache.loadSettings();
        String path;
        String formated = (String) VCDSCache.getSettingValue("formated");
        boolean isFormated = formated.equalsIgnoreCase("true");
        if (isFormated) {
            path = System.getProperty("user.dir") + "\\resourses\\";
        } else {
            path = System.getProperty("user.dir") + "\\res\\";
        }
        VCDSConstants.CURRENT_RES_DIR = path;
        try {
            VCDSCache.loadCache();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        MenuModule menu = MenuModule.getInstance();
        menu.start();
    }

}

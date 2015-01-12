package vcdscodes.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import vcdscodes.core.constants.VCDSConstants;

public class VCDSDownload {
    
    private static HashMap<String, String> errorCodesInfo = new HashMap<>();

    public static String getWebContentFromUrl(String urlStringLink) {
        String result = "";
        try {
            URL url = new URL(urlStringLink);
            java.util.Scanner s = new java.util.Scanner(url.openStream()).useDelimiter("\\A");
            result = s.next();
        } catch (MalformedURLException ex) {
            Logger.getLogger(VCDSDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VCDSDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static String getWebContent() {
        String result = null;
        try {
            URL url;
            for(int i = 0; i < 4; i++){
                if(i == 0)
                    url = new URL(VCDSConstants.MAIN_URL_LINK + VCDSConstants.SUFFIX_FIRST_CODES_URL_LINL);
                else if (i == 1)
                    url = new URL(VCDSConstants.MAIN_URL_LINK + VCDSConstants.SUFFIX_SECOND_MAIN_URL_LINK);
                else if (i == 2)
                    url = new URL(VCDSConstants.MAIN_URL_LINK + VCDSConstants.SUFFIX_THIRD_MAIN_URL_LINK);
                else if (i == 3)
                    url = new URL(VCDSConstants.MAIN_URL_LINK + VCDSConstants.SUFFIX_FOURTH_MAIN_URL_LINK);
                else if (i == 4)
                    url = new URL(VCDSConstants.MAIN_URL_LINK + VCDSConstants.SUFFIX_THIRD_MAIN_URL_LINK);
                else 
                    url = null;
                java.util.Scanner s = new java.util.Scanner(url.openStream()).useDelimiter("\\A");
                result += s.next();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(VCDSDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VCDSDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static String parseUrlContent(String urlContent) {
        StringBuilder sb = new StringBuilder();
        System.out.println(urlContent);
        String[] splitedString = urlContent.split("<li>");
        String buffer = "";
        StringBuilder sbErrorCodesInfo = new StringBuilder();
        String currentString = "";
        String errorCode = "";
        for (String splitedString1 : splitedString) {
            if (splitedString1.startsWith(VCDSConstants.PREFIX_CUT_STRING)) {
                buffer = splitedString1.substring(25, 25+6);
                System.out.println("Error code: " + buffer);
                if(buffer.endsWith("\"")) {
                    errorCode = buffer.substring(0, 5);
                    currentString = VCDSConstants.MAIN_URL_LINK + "/" + errorCode + "\n";
                    
                } else {
                    errorCode = buffer;
                    currentString = VCDSConstants.MAIN_URL_LINK + "/" + errorCode + "\n";
                }
                errorCodesInfo.put(errorCode, getWebContentFromUrl(currentString));
                sb.append(currentString);
                VCDSSaver.save(errorCode, errorCodesInfo.get(errorCode));
            }
        }
        return sb.toString();
    }


    public static String getParsedContent() {
        String unparsedContent = getWebContent();
        return parseUrlContent(unparsedContent);
    }
    
    public static String getErrorCodesInfo()
    {
        StringBuilder result = new StringBuilder();
        Collection allcodes = errorCodesInfo.values();
        for(Object code : allcodes) {
            result.append(code);
        }
        return result.toString();
    }
}

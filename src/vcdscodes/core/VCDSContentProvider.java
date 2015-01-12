/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcdscodes.core;

/**
 *
 * @author ChillToucH
 */
public class VCDSContentProvider {
    private static VCDSContentProvider instance;
    private VCDSContentProvider(){}
    public static VCDSContentProvider getInstance() {
        if(instance == null)
            instance = new VCDSContentProvider();
        return instance;
    }
    
    public static String getData(String error) {
        return VCDSCache.getDataFromCache(error);
    }
}

package vcdscodes.core;

import javax.swing.JProgressBar;

/**
 *
 * @author ChillToucH
 */
public class VCDSProggresMenu extends Thread{

    private JProgressBar progress;
    private boolean isFinished;
    
    private void init() {
        progress = new JProgressBar();
        isFinished = false;
    }
    
    
    
}

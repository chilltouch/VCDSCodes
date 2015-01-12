package vcdscodes.gui;

/**
 * This is main handler of main menu
 * It's thread save!
 * @author ChillToucH
 */
public class MenuModule extends Thread {
    private static MenuModule instance;
    private MainMenu mainMenu;
    
    private MenuModule(){}
    
    public static MenuModule getInstance() {
        if(instance == null)
            instance = new MenuModule();
        return instance;
    }
    
    @Override
    public void run() {
        initiateMenu();
        mainMenu.start();
    }

    private void initiateMenu() {
        mainMenu = MainMenu.getInstance();
    }
    
    public MainMenu getMainMenu() {
        return mainMenu;
    }
}

package util.utility;

import view.home.HomeController;

public class MenuBarController {
    private static int menuNumber = 0;
    private static HomeController homeController = null;

    public static HomeController getHomeController(){
        return homeController;
    }

    public static void setHomeController(HomeController desktopStageController){
        MenuBarController.homeController = desktopStageController;
    }

    public static int getMenuNumber(){
        return menuNumber;
    }

    public static void setMenuNumber(int menuNumber){
        MenuBarController.menuNumber = menuNumber;
    }
}

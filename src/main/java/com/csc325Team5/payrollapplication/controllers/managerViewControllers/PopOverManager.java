package com.csc325Team5.payrollapplication.controllers.managerViewControllers;

public class PopOverManager {
    private static PopOverController pop = null;

    public static PopOverController getPop() {
        return pop;
    }

    public static void setPop(PopOverController controller) {
        pop = controller;
    }

}

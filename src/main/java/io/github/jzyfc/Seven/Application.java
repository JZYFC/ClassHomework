package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.ui.MainWindow;

public class Application {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        MainWindow mainWindow = new MainWindow();
    }
}

package lbuang;

import lbuang.Read_n_Write.WriteTXT;
import lbuang.Views.ConsoleView;
import lbuang.Views.GuiView;

public class main {
    public static void main(String[] args) {
        try {
            WriteTXT.createFile();
            if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))){
                System.out.println("Program arguments: You want to run 'console' or 'gui'?");
                System.exit(1);
            }
            if (args[0].equals("console")) {
                ConsoleView.begin();
            }

            else {
                GuiView gui = new GuiView();
                gui.welcomeToGui();
            }
        }
        finally {
            WriteTXT.closeFile();
        }
    }
}
package view;

import javax.swing.JInternalFrame;

public class InterfaceUtils {

    public static void preparaTela(JInternalFrame jf) {
        jf.setClosable(true);
        Start.addFrame(jf);
    }
}

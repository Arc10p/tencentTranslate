import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
public class EroTranslate {
    public static void main(String[] args)  {
        String text = getClipboardString();
        double len=Math.ceil(text.length()/1900.0);
        String[] s=new String[(int)len];
        int left=0,right=1900;
        for (int i = 0; i < (int)len; i++) {
            if (right>text.length())
                right=text.length();
            s[i]=text.substring(left,right);
            left=right;
            right+=1900;
        }
        translateRunnable runnable=new translateRunnable(s);
        Thread t = new Thread(runnable);
        t.start();
    }

    public static String getClipboardString() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


}
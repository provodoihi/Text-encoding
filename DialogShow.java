package BI9202;
import javax.swing.*;
// for .jar app use
public class DialogShow {

    // welcome message
    public void DialogShow() {
        JFrame j = new JFrame();
        JOptionPane.showMessageDialog(j, "Welcome to the small text encoding program\nThis program support encode text file to binary file\nPlease press OK to continue");
    }

    // encode success message
    public void DialogShow2(){
        JFrame k = new JFrame();
        JOptionPane.showMessageDialog(k, "Encoding successfully from text file to binary file: DiscreteMath.bin");
    }

    // cancel encode message
    public void DialogShow3(){
        JFrame l = new JFrame();
        JOptionPane.showMessageDialog(l, "You cancel to choose a text file\nPress OK to close the program");
    }

    // finish message
    public void DialogShow4(){
        JFrame m = new JFrame();
        JOptionPane.showMessageDialog(m, "Thank you for using this program\nPress OK to close the program");
    }
}


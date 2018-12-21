
package j48;

/**
 * @author Tri Wicaksono
 */
public class Main extends Algoritma{

    
    public static void main(String[] args) throws Exception {
        Algoritma Tree = new Algoritma();
        Tree.j48Tree();
        new J48_Ui().setVisible(true);
    }
    
}

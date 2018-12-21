
package j48;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
/**
 * @author OldCamel
 */
public class Algoritma {
     
    public void j48Tree() throws FileNotFoundException, IOException, Exception {
        BufferedReader breader =null;
        
       //hasill nya ada atau yangsudah di training
        breader = new BufferedReader(new FileReader("E:\\Java Project\\J48\\Data\\HTRU_2.arff"));
        Instances train = new Instances (breader);
        train.setClassIndex(train.numAttributes()-1);
        
         //hasil nya ga ada atau belum di training
        breader = new BufferedReader(new FileReader("E:\\Java Project\\J48\\Data\\HTRU_test.arff"));
        Instances test = new Instances (breader);
        test.setClassIndex(train.numAttributes()-1);
            
        breader.close();
        
        //instance untuk methode three J48
        J48 tree = new J48() ;
        //membuat classifier
        tree.buildClassifier(train);
        Instances labeled = new Instances(test);     
        //label instansi
        for (int i = 0; i < test.numInstances(); i++) {
            double clsLabel = tree.classifyInstance(test.instance(i));
            labeled.instance(i).setClassValue(clsLabel); 
        }

        // save labeled data
        BufferedWriter write =new BufferedWriter( new FileWriter("E:\\Java Project\\J48\\Data\\HTRU_hasil.arff"));//mencetak baru label
        write.write(labeled.toString());
        write.newLine();
        write.flush();
        write.close();
        
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(tree, train, 10,  new Random(1));
        System.out.println(eval.toSummaryString("Statistik hasil perhitungan \n================================\n", true));
        System.out.println("=================================");
        System.out.println("- Hasil Ukuran F Akurasi Data Training :"+ eval.fMeasure(1) + "\n- Tingkat Ketelitian Data : "+ eval.precision(1)+ "\n- Tingkat Recall Pada Suatu Data : " + eval.recall(1));
    }
}

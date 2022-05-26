/*The following class determines ordering of a list of Polynomial
 *objects based on strong and weak ordering.
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.filechooser.*;

import javax.swing.*;

public class PolynomialMain {
	public static void main (String[] args) {
    
    	//Get a file
    	File polyFile = null;
    	JFileChooser fileChooser = new JFileChooser();
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Polynomial File", "txt");
    	fileChooser.setFileFilter(filter);
    	int returnValue = fileChooser.showOpenDialog(new JPanel());
    	if(returnValue == JFileChooser.APPROVE_OPTION) {
        	polyFile = fileChooser.getSelectedFile();
    	}
    	if(returnValue == JFileChooser.CANCEL_OPTION) {
    		System.exit(0);
    	}
    	//Read that file line by line into an array list of Polynomials
    	ArrayList<Polynomial> list = new ArrayList<Polynomial>();
    	try {
    		Scanner scan = new Scanner(polyFile);
    		while (scan.hasNextLine()) {
    			String strNextPoly = scan.nextLine();
    			Polynomial nextPoly = new Polynomial(strNextPoly);
    			list.add(nextPoly);
    			System.out.println(nextPoly.toString());
    		}
    		scan.close();
    	} catch(Exception e) {
            JOptionPane.showMessageDialog(null, "InvalidPolynomialSyntax: "+e.getMessage());
            System.exit(0);
    	}
    	//Check strong order with compareTo method
    	boolean isSorted = true;
    	for( int i = 1; i < list.size(); i++) {
    		Polynomial current = list.get(i);
    		Polynomial next = list.get(i-1);
    		if (next == null) {
    			 break;
    		}
    		
    		int intSorted = next.compareTo(current);
    		if(intSorted < 0) {
    			isSorted = false;
    		}
    		
    	}
    	if (isSorted) {
    		JOptionPane.showMessageDialog(null, "This file was sorted with strong order.");
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "This file was not sorted with strong order.");
    	}
    	
    	//Check weak order with OrderedList.checkSorted()
    	Comparator<Polynomial> comparator = (p1, p2) -> p1.compareTo(p2);
    	
    	isSorted = OrderedList.checkSorted(list, comparator);
    	if (isSorted) {
    		JOptionPane.showMessageDialog(null, "This file was sorted with weak order.");
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "This file was not sorted with weak order.");
    	}
	
    }
}   

/* This code gets a file from the user, creates a DirectedGraph, then
 * displays two representations of the directedGraph as well as 
 * indicates whether any vertices were not able to be accessed.
 * 
 * Chloe Ingram
 * 09 May 2022
 * CMSC 350 -7380
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Project8Main {
	
	public static void main(String[] args) {
		File graphFile = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graph File", "txt");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(new JPanel());
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			graphFile = fileChooser.getSelectedFile();
		}
		if(returnValue == JFileChooser.CANCEL_OPTION) {
			System.exit(0);
		}
		
		DirectedGraph<String> graph = new DirectedGraph<String>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(graphFile));
			String line = reader.readLine();
			while (line != null) {
				String[] splitLine = line.split("\\s+");
				int index = 1;
				graph.findAddVertex(splitLine[0]);
				while (index != splitLine.length) {
					graph.addEdge(splitLine[0], splitLine[index]);
					index++;
				}
				line = reader.readLine();
			}
			reader.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		DFSActions<String> parenthesizedList = new ParenthesizedList();
		DFSActions<String> hierarchy = new Hierarchy();
		graph.findUnreachable(parenthesizedList);
		System.out.println(parenthesizedList.toString());
		graph.findUnreachable(hierarchy);
		System.out.println(hierarchy.toString());
	}
	
	
	
}

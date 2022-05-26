/*The following code generates a GUI for converting
 * both from prefix-to-postfix notation and postfix-to-prefix
 * notation. 
 *
 *
 *Name: Ingram, Chloe P		Date: 24 March 2022		Class: CMSC 350 - 7380
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterGUI extends JFrame{
	 private JTextField input = new JTextField(20), output = new JTextField(20);
	 private final JButton preToPost = new JButton("Prefix to Postfix");
	 private final JButton postToPre = new JButton("Postfix to Prefix");

	 //The following method was adapted from Dr. Jarc, 
	 //and is used with their recommendation. Changes have been
	 //made to accommodate an additional button, as
	 //well as adding two actionListener objects
	 public ConverterGUI() {
	     super("Expression Converter");
	     setSize(400, 150);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLayout(new GridLayout(3, 1));
	     JComponent[] inputComponents = {new JLabel("Enter Expression"),
	             input};
	     makeFlowPanel(inputComponents);
	     JComponent[] buttonComponents = {preToPost, postToPre};
	     makeFlowPanel(buttonComponents);
	     JComponent[] outputComponents = {new JLabel("Result"), output};
	     makeFlowPanel(outputComponents);
	     output.setEditable(false);
	     preToPost.addActionListener(preToPostListener);
	     postToPre.addActionListener(postToPreListener);
	 }
	 
	 //The following method calls a method to convert from 
	 //prefix notation to postfix notation.
	 private final ActionListener preToPostListener = event -> 
	 {
		 try {
	            String value = Converter2.prefixToPostfixConverter(input.getText());
	            output.setText("" + value);
	        } catch (SyntaxError e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	 };
	 
	 //The following method calls a method to convert from 
	 //postfix to prefix notation.
	 private final ActionListener postToPreListener = event -> 
	 {
		 try {
	            String value = Converter2.postfixToPrefixConverter(input.getText());
	            output.setText("" + value);
	        } catch (SyntaxError e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	 };
	 
	 //The following method was generated by Dr. Jarc, 
	 //and is used with their recommendation.
	 //It functions to create a FlowLayout-style GUI.
	 private void makeFlowPanel(JComponent[] components) {
	     JPanel panel = new JPanel();
	     panel.setLayout(new FlowLayout());
	     for (Component component : components)
	         panel.add(component);
	     add(panel);
	 }

	 //The main() method creates a new ConverterGUI.
	 public static void main(String[] args) {
	     ConverterGUI frame = new ConverterGUI();
	     frame.setVisible(true);
	     
	 }

}


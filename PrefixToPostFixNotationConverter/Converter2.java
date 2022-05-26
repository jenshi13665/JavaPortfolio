/*The following class describes the methods associated with converting
 *from prefix and postfix notations.
 *
 *Name: Ingram, Chloe P		Date: 24 March 2022		Class: CMSC 350 - 7380
 */

import java.util.*;

public class Converter2 {
	
	private static Stack<String> operandStack = new Stack<String>();
	private static Stack<String> reversalStack = new Stack<String>();
	
	//The following method converts a user-provided expression
	//from postfix notation to prefix notation.
	public static String postfixToPrefixConverter(String expression) throws SyntaxError {
		StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/ ", true);
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			char charToken = token.charAt(0);
			if (token.equals(" ")) {
				continue;
			}
			else if ((token.chars().allMatch( Character::isDigit))) {
					operandStack.push(token);
				}
			else if (isOperator(charToken)) {
				if (operandStack.empty()) {
					throw new SyntaxError ("Empty operand stack.");
				}
				String operand2 = operandStack.pop();
				String operand1 = operandStack.pop();
				String out = token + ' ' + operand1 + ' ' + operand2 + ' ';
				operandStack.push(out);
			}
			else {
				throw new SyntaxError("Invalid operator.");
			}
		}
		String finalExpression = operandStack.pop();
		if (!operandStack.empty()) {
			operandStack.clear();
			throw new SyntaxError("Stack not empty.");
		}
		return finalExpression;
	}
	
	//The following method converts a user-provided expression
	//from prefix notation to postfix notation.
	public static String prefixToPostfixConverter(String expression) throws SyntaxError {
		StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/ ", true);
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			System.out.println(token);
			char charToken = token.charAt(0);
			if (!token.equals(" ")) {
				if ((token.chars().allMatch( Character::isDigit)) || (isOperator(charToken))) {
					reversalStack.push(token);
				}
			}
		}
		while(!reversalStack.empty()) {
			String nextToken = reversalStack.pop();
			System.out.println(nextToken);
			char nextCharToken = nextToken.charAt(0);
			if (!isOperator(nextCharToken)) {
				operandStack.push(nextToken);
			}
			else {
				if (operandStack.empty()) {
					throw new SyntaxError("Empty operand stack.");
				}
				String operand1 = operandStack.pop();
				String operand2 = operandStack.pop();
				String newExpression = operand1 + ' ' + operand2 + ' ' + nextToken + ' ' ;
				operandStack.push(newExpression);
			}
		}
		String finalExpression = operandStack.pop();
		if (!operandStack.empty()) {
			operandStack.clear();
			throw new SyntaxError("Stack not empty.");
		}
		return finalExpression;
	}

	//The following method checks if the provided character
	//is a valid operator.
	private static boolean isOperator(char ch) {
		
		switch(ch) {
				
		case '+':
		case '/':
		case '*':
		case '-':
			return true;
		default:
			return false;
		}	
	}
}

/*The following class describes a binary tree and methods for categorizing it.
 * 
 * *Name: Ingram, Chloe P		Date: 23 April 2022		Class: CMSC 350 - 7380
 */

import java.lang.Math;

public class BinaryTree {
	
	static class Node {
		
		protected String element;
		protected Node left, right;
		
		Node(String element) {
			this.element = element;
		}
	}
	
	//The following method creates nodes for an entire
	//binary tree. Called during BinaryTree constructor.
	public Node makeNode() throws InvalidTreeSyntax {
		if(!(Character.isLetterOrDigit(tree[++index]))) {
			throw new InvalidTreeSyntax("Must input letters and numbers as nodes.");
		}
		Node node = new Node(Character.toString(tree[index]));
		index++;
		if(tree[index] == '(') {
			node.left = makeNode();
			index++;
		}
		if(tree[index] == ')') {
			return node;
		}
		if(tree[index] == '(') {
			node.right = makeNode();
			index++;
		}
		if(tree[index] == ')') {
			return node;
		}
		if(tree[++index] == ')') {
			return node;
		}
		throw new InvalidTreeSyntax("Too many children.");
	}
	
	//class variables
	protected Node root;
	protected char [] tree;
	protected int index;
	protected int height;
	
	//constructor
	public BinaryTree(String data) throws InvalidTreeSyntax {
		this.tree = data.toCharArray();
		this.root = makeNode();
		this.height = heightCheck(root);
	}
	
	//This updated method was pulled from BinaryTree.java
	//created by Professor Jarc.
	public StringBuilder inorder() {
        StringBuilder strInorder = new StringBuilder();
        inorder(root, strInorder);
        return strInorder;
    }

    protected void inorder(Node node, StringBuilder strInorder) {
        if (node == null)
            return;
        strInorder.append("(");
        inorder(node.left, strInorder);
        strInorder.append(node.element);
        strInorder.append(")");
        inorder(node.right, strInorder);
    }

    //The following method checks if the binary tree is balanced.
    public Boolean balanceCheck() {
    	int leftSubHeight = 0, rightSubHeight = 0, balanceCheck = 0;
    	if(root.left != null) {
    		leftSubHeight = heightCheck(root.left);
    	}
    	if(root.right != null) {
        	rightSubHeight = heightCheck(root.right);
    	}
    	balanceCheck = leftSubHeight - rightSubHeight;
    	
    	if (Math.abs(balanceCheck) > 1) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    //The following method checks if the binary tree is full.
    public Boolean fullCheck() {
    	int maxFull = 0;
    	for(int i = 0; i < this.height; i++) {
    		int val = (int)Math.pow(2, i);
    		maxFull+=val;
    	}
    	int nodesNum = nodesCheck(root);
    	if(nodesNum < maxFull) {
    		return false;
    	}
    	return true;
    }
    
    //The following method checks the height/depth of the binary tree.
    public int heightCheck(Node node) {
    	int leftHeight = 0, rightHeight = 0, height = 0;
    	
    	if (node.left != null) {
    		leftHeight = heightCheck(node.left);
    	}
    	
    	if(node.right != null) {
    		rightHeight = heightCheck(node.right);
    	}
    	
    	if(leftHeight > rightHeight) {
    		height = leftHeight;
    	}
    	else {
    		height = rightHeight;
    	}
    	return height+1;
    }
    
    //The following method checks whether the binary tree is proper.
    public Boolean properCheck(Node node) {
    	//If there is no left/right (leaf node), then subtree ends.
    	if ((node.left == null) && (node.right == null)) {
    		return true;
    	}
    	//If there is data in both the left and right nodes,
    	//recursively check again for those nodes' left and right nodes.
    	//To be proper, both left and right must return true/contain data.
    	if((node.left != null) && (node.right != null)) {
    		Boolean checker = (properCheck(node.left)) && (properCheck(node.right));
    		return checker;
    	}
    	return false;
    }
    
    //The following method calculates the number of nodes in the binary tree.
    public int nodesCheck(Node node) {
    	if(node == null) {
    		return 0;
    	}
    	int numOfNodes = nodesCheck(node.left) + nodesCheck(node.right) + 1;
    	return numOfNodes;
    }
    
    
    
    
    

}

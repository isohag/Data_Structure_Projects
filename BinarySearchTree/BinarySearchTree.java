/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> implements BinaryTree<E> {

	public static void main(String[] args) {
		Comparator<String> comp = new Comparator<String>();
		BinarySearchTree<String> tree = new BinarySearchTree<String>(comp);
		Scanner stdin = new Scanner(System.in);
		System.out.println("Strings(comma-seperated): ");
		String input = stdin.nextLine();
		String[] arr = input.split(",");
		for (String x : arr)
			tree.add(x);
		//tree.printTree(tree.root);	
		System.out.println("Search: ");
		String search = stdin.nextLine();
		while (!search.equals("quit"))
		{
			int level = tree.level(tree.root, search, 0);
			if (tree.contains(search)) 
				System.out.println("String \"" + search + "\" found in " + level + " Steps");
			else
				System.out.println("String \"" + search + "\" NOT found in " + level + " Steps");
			
			System.out.println("Search: ");
			search = stdin.nextLine();
		}
		
	}
	
	private int level(BinaryTreeNode<E> head, E element, int l ) {
		int i = comp.compare(element, head.getData());
		if (i == 0)
			return l;
		else if (i>0) {
			if (head.getRight() != null)	// right exists
				return level(head.getRight(), element, l+1);
			else
				return l;
		}
		else  {
			if (head.getLeft() != null)		// Left exists
				return level(head.getLeft(), element, l+1);
			else
				return l;
		}
	}

	private BinaryTreeNode<E> root;
	private Comparator<E> comp;

	public BinarySearchTree(Comparator<E> comp) {
		this.comp = comp;
	}

	@Override
	// Verified
	public void add(E element) {
		if (root == null) {
			root = new BinaryTreeNodeImpl<E>(element, null, null, null);
			return;
		}

		// Find the node to add to recursively
		if (!this.contains(element)) {
			BinaryTreeNode<E> leaf = insert(element, this.root);
			// create a new node with leaf as the parent and element as the data
			BinaryTreeNode<E> node = new BinaryTreeNodeImpl<E>(element, leaf,
					null, null);
			int i = comp.compare(element, leaf.getData());
			if (i > 0)
				leaf.setRight(node);
			else
				leaf.setLeft(node);
		}
	}

	// Verified
	private BinaryTreeNode<E> insert(E element, BinaryTreeNode<E> node) {
		int i = comp.compare(element, node.getData()); // If element is greater
														// than head return 1
		if (i > 0) {
			if (node.getRight() != null)
				return insert(element, node.getRight());
		}

		else if (i < 0) {
			if (node.getLeft() != null) // Left exists
				return insert(element, node.getLeft());

		}
		return node;
	}

	@Override
	public void remove(E element) {
		if (!this.contains(element)) // Duplicates
			return;

		// Find the node to be deleted
		BinaryTreeNode<E> delete = insert(element, this.root);
		// Number of Children
		int children = 0;
		if (delete.getLeft() != null)
			children++;
		if (delete.getRight() != null)
			children++;
		switch (children) {
		case 0:
			if (delete == root)
				root = null;
			else if (delete.getParent().getLeft() == this) {	//	Left child
				delete.getParent().setLeft(null);
				delete.setParent(null);
			}
			else {												//	Right Child
				delete.getParent().setRight(null);
				delete.setParent(null);
			}
			break;
		case 1:													//	Just SWAP
			if (delete.getParent().getRight() == delete)	{					// Right child of it's parent
				if (delete.getRight() != null) 	// Has Right child
					delete.getParent().setRight(delete.getRight());
				else 							// Has Left child
					delete.getParent().setRight(delete.getLeft());
			}
			else {	// Left child of it's parent
				if (delete.getRight() != null) // Has right child
					delete.getParent().setLeft(delete.getRight());
				else
					delete.getParent().setLeft(delete.getLeft());
			}
			break;
		case 2:
			// find the Lowest in the right subtree.
			BinaryTreeNode<E> rSubTree = delete.getRight();
			while (rSubTree.getLeft() != null) {
				rSubTree = rSubTree.getLeft();
			}
			delete.setData(rSubTree.getData()); // Swap data
			rSubTree.getParent().setLeft(null);
			rSubTree.setParent(null);
			break;
		}
	}

	@Override
	// Verified
	public boolean contains(E element) {
		return exists(element, this.root);
	}

	private boolean exists(E element, BinaryTreeNode<E> node) {
		int i = comp.compare(element, node.getData());
		if (i == 0) {
			return true;
		} else if (i > 0) {
			if (node.getRight() != null) // Right node exists?
				return exists(element, node.getRight());
			else
				return false;
		} else {
			if (node.getLeft() != null) // Left node Exists?
				return exists(element, node.getLeft());
			else
				return false;
		}

	}

	@Override
	public E min() {
		return getMin(this.root);
	}

	private E getMin(BinaryTreeNode<E> node) {
		if (node.getLeft() != null)
			return getMin(node.getLeft());
		else
			return node.getData();
	}

	@Override
	public E max() {
		// TODO Auto-generated method stub
		return getMax(root);
	}

	private E getMax(BinaryTreeNode<E> node) {
		if (node.getRight() != null)
			return getMax(node.getRight());
		else
			return node.getData();
	}
	
	private void printTree(BinaryTreeNode<E> node) {
		System.out.print(node.getData());
		System.out.println();
		if (node.getLeft() != null)
			printTree(node.getLeft());
		else if (node.getRight() != null)
			printTree(node.getRight());
		else
			return;
	}

	private static class Comparator<E extends Comparable<E>> {

		public int compare(E element, E something) {
			return element.compareTo(something);
		}
	}

}

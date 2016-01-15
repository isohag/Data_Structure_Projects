/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */


public interface BinaryTreeNode<E> {
	E getData ();
	BinaryTreeNode<E> getParent ();
	void setData(E data);
	void setParent (BinaryTreeNode<E> parent); 
	BinaryTreeNode<E> getLeft ();
	BinaryTreeNode<E> getRight ();
	void setLeft (BinaryTreeNode<E> node);
	void setRight(BinaryTreeNode<E> node);
	void removeFromParent ();
	
}

/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */


public class BinaryTreeNodeImpl<E> implements BinaryTreeNode<E>{
	BinaryTreeNode<E> left, right, parent;
	private E data;
	
	public BinaryTreeNodeImpl(E data, BinaryTreeNode<E> parent, 
			BinaryTreeNode<E> left, BinaryTreeNode<E> right){
		this.data = data;
		this.parent = parent;
		this.right = right;
		this.left = left;
	}

	@Override
	public E getData() {
		return data;
	}

	@Override
	public void setData (E data) {
		this.data = data;
	}

	@Override
	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	@Override
	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}

	@Override
	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	@Override
	public BinaryTreeNode<E> getRight() {
		return right;
	}

	@Override
	public void setLeft(BinaryTreeNode<E> node) {
		this.left = node;
	}

	@Override
	public void setRight(BinaryTreeNode<E> node) {
		this.right = node;
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		if (this.parent.getLeft() == this) // if this is a left child
			this.parent.setLeft(null);
		if (this.parent.getRight() == this) // if this is a Right child
			this.parent.setRight(null);
		this.parent = null;
	}
	
}

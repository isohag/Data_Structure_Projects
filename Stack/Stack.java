/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (2)
 * TA: Rathish Das
 */

import java.util.*;

public class Stack<E> {
	
	private int size;
	private Node<E> top;
	
	public Stack() {
		size = 0;
		top = new Node<E> (null, null);
	}
	
	public boolean isEmpty() { 
		return top.getData() == null; 
	}
	
	public E peek() {
		if (size == 0)
			throw new EmptyStackException();
		return top.getData();
	}
	
	public E pop(){
		if (size == 0)
			throw new EmptyStackException();
		E data = top.getData();
		
		if (size!=1) {
		top.getPrevious().setNext(null);
		top = top.getPrevious();
		}
		else
			top.setData(null);
		
		size--;
		return data;
	}
	
	public boolean push(E data) {
		if (size==0)
			top.setData(data);
		else {
			Node<E> next = new Node<E>(data, null);
			next.setPrevious(top);
			top.setNext(next);
			top = next;
		}
		
		size++;
		return true;
	}
	
	public int size() {return size;}
	
	public String toString() {
		String result = "";
		Node<E> cursor = top;
		for (int i=0;i<size;i++){
			result += " <- " + cursor.getData();
			cursor = cursor.getPrevious();
		}
		return result;
	}
	
	
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> previous;
		public Node(E data, Node<E> next){
			this.data = data;
			this.next = next;
		}
		
		public E getData(){return this.data;}
		public void setData(E data) { this.data = data;}
		public void setNext(Node<E> next) { this.next = next;}
		public Node<E> getNext() {return this.next;}
		public Node<E> getPrevious() {return previous;}
		public void setPrevious(Node<E> previous) {this.previous = previous;}
	}
}

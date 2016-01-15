//	Created by Rashedul Khan (108921821)
//	CSE 214 HW #1

public class UniLinkedList<E> {
	//	Data
	private int size;
	private Node<E> head;
	private Cursor<E> cursor;
	
	public UniLinkedList() {
		head = new Node<E>(null, null);
		cursor = new Cursor<E> (this);
	}
	
	public Cursor<E> getCursor(){
		return cursor;
	}
	
	public void setCursor(Node<E> cursor) {this.cursor.setCursor(cursor);}
	
	public Node<E> getHead() {return head;}
	
	public void setHead(Node<E> head) {this.head.setNext(head);}

	@Override public String toString() { 
		String list = "(";
		if (this.isEmpty())
			return "()";
		for (cursor.setCursor(head.getNext()); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			list += cursor.getCursor().getData();
			if (cursor.getCursor().getNext() != null)
				list += " -> ";
		}
		list += cursor.getCursor().getData();
		list += ")";
		return list;
	}
	
	//////////////////////////////////
	public boolean equals(Object obj) {
		UniLinkedList list1 = (UniLinkedList) obj;
		int i = 0;
		if (this.size != list1.size())
			return false;
		cursor.setCursor(head);
		for (cursor.setCursor(cursor.getCursor().getNext()); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			if (cursor.getCursor().getData() != list1.get(i++))
				return false;
		}

		if (cursor.getCursor().getData() != list1.get(i))
			return false;
		return true;
	}
	
	public boolean isEmpty() { return head.getNext() == null;}
	
	public void clear() { head.setNext(null);}
	
	public int size() { return size;}
	
	public int indexOf(E element) {
		if (this.isEmpty())
			return -1;
		int i = 0;
		cursor.setCursor(head);
		for (cursor.setCursor(cursor.getCursor().getNext()); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			if (cursor.getCursor().getData() == element)
				return i;
			i++;
		}
		if (cursor.getCursor().getData() == element)
			return i;
		return -1;
	}
	
	public boolean contains (E element) {
		for (int i=0; i<this.size ; i++) {
			if (this.get(i) == element)
				return true;
		}
		
		return false;
	}
	
	public boolean add(E element) {
		Node<E> data = new Node<E>(element, null);
		while (cursor.hasNext()) {
			cursor.setCursor(cursor.getCursor().getNext());
		}
		cursor.getCursor().setNext(data);
		size++;
		return true; // Temp....................................
	}
	
	public boolean addAfter(E mark, E elementToAdd) {
		if (!this.contains(mark))
			throw new IndexOutOfBoundsException();

		this.indexOf(mark);
		Node<E> newNode = new Node<E> (elementToAdd, null);
		newNode.setNext(cursor.getCursor().getNext());
		cursor.getCursor().setNext(newNode);

		return true;
	}
	public boolean addFirst(E element) {
		cursor.setCursor(head);
		Node<E> newNode = new Node<E> (element, null);
		newNode.setNext(cursor.getCursor().getNext());
		head.setNext(newNode);
		return true;
	}
	public E head() {return head.getNext().getData();}
	
	public boolean remove (E element) {
		
		for (cursor.setCursor(head); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			if (cursor.getCursor().getNext().getData() == element)
				break;
		}
		cursor.getCursor().setNext(cursor.getCursor().getNext().getNext());
		
		return true;
	}
	
	public boolean removeAll(E element) {
		for (cursor.setCursor(head.getNext()); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())) {
			if (cursor.getCursor().getData() == element)
				this.remove(element);
		}
		if (cursor.getCursor().getData() == element)
			this.remove(element);
		return true;
	}
	public void deduplicate() {
		for (cursor.setCursor(head); cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			
			Node<E> node = cursor.getCursor();
			Node<E> nextNode = cursor.getCursor().getNext();
			this.removeAll(nextNode.getData());
			if (node.getData() != null)
				this.addAfter(node.getData(), nextNode.getData());
			else
				this.addFirst(nextNode.getData());
		}
		
		
	}
	public E get(int i) {
		if (i>=size || i<0)
			throw new IndexOutOfBoundsException();
		int k = 0;
		for (cursor.setCursor(head.getNext()) ; cursor.hasNext(); cursor.setCursor(cursor.getCursor().getNext())){
			if (k==i)
				break;
			k++;
		}
		
		return cursor.getCursor().getData();
	}
	
	
	@Override public int h​ashCode(){
	r​eturn h​ead !​= n​ull ? ​h​ead.​hashCode() : 0​;​ }
	
	private static class Node<E> {
		//	Data
		private E data;
		private Node<E> next;
		public Node(E data, Node<E> next){
			this.data = data;
			this.next = next;
		}
		public E getData(){return this.data;}
		public void setData(E data) { this.data = data;}
		public void setNext(Node<E> next) {this.next = next;}
		public Node<E> getNext() {return this.next;}
		
		//	Methods
		@Override
		publicinth​ashCode(){ i​ntr​esult=d​ata!​=n​ull?​d​ata.​hashCode():0​;​
		r​eturn​3​1*​result+(n​ext!​=n​ull?​n​ext.​hashCode():0​)​; }
	}
	
	public static class Cursor<E> {
		private Node<E> position;
		private Cursor(UniLinkedList list){ position = list.head;}

		public boolean hasNext(){ return position.getNext() != null;}
		public E next(){ return position.getNext().getData();}
		public void setCursor(Node<E> cursor) { this.position = cursor; }
		public Node<E> getCursor(){ return position;}
		
	}
}

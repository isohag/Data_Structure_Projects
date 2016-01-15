//	Created by Rashedul Khan (108921821)
//	CSE 214 HW #1

import java.util.Scanner;

public class Point2D {
	
	public static void main(String[] args) {
		UniLinkedList<OrderedDoublePair> list = new UniLinkedList<OrderedDoublePair>();
		Scanner stdin = new Scanner(System.in);
		String input;
		do {
			input = stdin.nextLine();
			input.trim();
			if (input.equals("done")) 
				break;
			String[] xy = input.split(" ");
			try {
			double x = Double.parseDouble(xy[0]);
			double y = Double.parseDouble(xy[1]);
			list.add(new OrderedDoublePair(x,y));
			} catch (Exception e) {System.out.println("Incorrect format. 'x y' is the correct format, numbers only");}
		} while (!input.equals("done"));
		System.out.println(list);
		System.out.println("Centroid = " +centroid(list)); 
		list.deduplicate();
		System.out.println("Centroid of unique points = " +centroid(list)); 
		System.out.println("Point closest to the origin is "+ smallest(list));
		System.out.println("Point farthest from the origin is "+ largest(list));
		for (int i=0; i<list.size(); i++) {
			if ((list.get(i).getX() + list.get(i).getY())%2 == 0)
				list.remove(list.get(i));
		}
		
		System.out.println("After filtering out even-summed points, the list is " + list);
		double smallc = distance(centroid(list), list.get(0));
		OrderedDoublePair cnode = list.get(0);
		for (int i=1;i<list.size();i++){
			if (distance(centroid(list), list.get(i)) < smallc) {
				smallc = distance(centroid(list), list.get(i));
				cnode = list.get(i);
			}
		}
		System.out.println("New Centroid = " +centroid(list)); 
		System.out.println("Point closest to the centroid (after deduplication of list data) is " + cnode);
	}
	
	public static double distance(OrderedDoublePair p, OrderedDoublePair q) {	
		return Math.sqrt( Math.pow(q.getX()-p.getX(),2) + Math.pow(q.getY()-p.getY(), 2) );
	}
	
	public static UniLinkedList<OrderedDoublePair> fromArray(OrderedDoublePair[] points) {
		UniLinkedList<OrderedDoublePair> list = new UniLinkedList<OrderedDoublePair>();
		for (int i=0; i<points.length; i++) {
			list.add(points[i]);
		}
		return list;
	}
	
	public static UniLinkedList<OrderedDoublePair> from2DArray(double[][] points) {
		if (points[0].length>2)
			throw new IndexOutOfBoundsException();
		UniLinkedList<OrderedDoublePair> list = new UniLinkedList<OrderedDoublePair>();
		for (int i=0; i< points.length; i++) {
			OrderedDoublePair pair = new OrderedDoublePair(points[i][0], points[i][1]);
			list.add(pair);
		}
		return list;
	}
	
	public static OrderedDoublePair centroid(UniLinkedList<OrderedDoublePair> points) {
		int n = points.size();
		double xi = 0;
		double yi = 0;
		for (int i=0; i<n; i++){
			xi += points.get(i).getX();
			yi += points.get(i).getY();
		}
		return new OrderedDoublePair(xi/n, yi/n);
	}
	
	public static OrderedDoublePair smallest(UniLinkedList<OrderedDoublePair> points) {
		double distance = distance(points.get(0), OrderedDoublePair.ORIGIN);
		int n = points.size();
		OrderedDoublePair small = points.get(0);
		for (int i=1; i<n; i++){
			if (distance(points.get(i), OrderedDoublePair.ORIGIN) < distance ) {
				small = points.get(i);
				distance = distance(points.get(i), OrderedDoublePair.ORIGIN);
			}
		}
		return small;
	}
	
	public static OrderedDoublePair largest(UniLinkedList<OrderedDoublePair> points) {
		double distance = distance(points.get(0), OrderedDoublePair.ORIGIN);
		int n = points.size();
		OrderedDoublePair large = points.get(0);
		for (int i=1; i<n; i++){
			if (distance(points.get(i), OrderedDoublePair.ORIGIN) > distance ) {
				large = points.get(i);
				distance = distance(points.get(i), OrderedDoublePair.ORIGIN);
			}
		}
		return large;
	}

}

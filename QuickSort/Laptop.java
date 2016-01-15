//	CSE 214 HW # 4
//	By: Rashedul Khan (108921821)
//

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class Laptop {
	private String brand;
	private Double procSpeed;
	private int memory;
	private int hdd;
	
	public Laptop(String brand, Double procSpeed, int memory, int hdd) {
		this.brand = brand;
		this.procSpeed = procSpeed;
		this.memory = memory;
		this.hdd = hdd;
	}
	
	public String getBrand() {return brand;}
	public Double getProc() {return procSpeed;}
	public int getMemory() {return memory;}
	public int getHdd() {return hdd;}
	
	public String toString() {
		return ("{"+brand +": "+ procSpeed +" processor, "+ memory +"GB RAM, "+ hdd +"GB HDD"+"}");
	}
	
	//	Main variables
	private static Comparator<Laptop> brandComparator = new brandComp();
	private static Comparator<Laptop> processorComparator  = new proceComp();
	private static Comparator<Laptop> memoryComparator = new memComp();
	private static Comparator<Laptop> hddComparator = new hddComp();
	private static Comparator<Integer> intComparator = new intComp();
	
	private static class brandComp implements Comparator<Laptop> {
		public int compare(Laptop o1, Laptop o2) {
			return o1.getBrand().compareTo(o2.getBrand());
		}
	}
	
	private static class proceComp implements Comparator<Laptop> {
		public int compare(Laptop o1, Laptop o2) {
			return o1.getProc().compareTo(o2.getProc());
		}
	}
	
	private static class memComp implements Comparator<Laptop> {
		public int compare(Laptop o1, Laptop o2) {
			if (o1.getMemory() > o2.getMemory())
				return 1;
			else if (o1.getMemory() < o2.getMemory())
				return -1;
			else
				return 0;
		}
	}
	
	private static class hddComp implements Comparator<Laptop>{
		public int compare(Laptop o1, Laptop o2) {
			if (o1.getHdd() > o2.getHdd())
				return 1;
			else if (o1.getHdd() < o2.getHdd())
				return -1;
			else
				return 0;
		}
	}
	
	private static class intComp implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
		
	}
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input data type (must be ’int’ or ’laptop’):");
        String inputDataType = scanner.nextLine().trim();
        if (!inputDataType.equals("int") && !inputDataType.equals("laptop"))
            throw new IllegalArgumentException("Invalid data type specified.");
        switch (inputDataType) {
            case "int":
                ArrayList<Integer> integers = readIntegerInputs(scanner);
                Sorter<Integer> intSorter = new Quicksorter<Integer>(intComparator, integers);
                intSorter.sort();
                System.out.println(getStringJoinedBy(integers, ", "));
                break;
            case "laptop":
                ArrayList<Laptop> laptops = readLaptopInputs(scanner);
                Sorter<Laptop> laptopSorter = new Quicksorter<Laptop>(brandComparator, laptops);
                //	Brand
                laptopSorter.sort();
                System.out.print("Sorted by brand name:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                // Processor 
                laptopSorter.setComparator(processorComparator);
                laptopSorter.sort();
                System.out.print("Sorted by processor speed:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                //	RAM
                laptopSorter.setComparator(memoryComparator);
                laptopSorter.sort();
                System.out.print("Sorted by RAM:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                System.out.println();
                //	HDD
                laptopSorter.setComparator(hddComparator);
                laptopSorter.sort();
                System.out.print("Sorted by hard disk capacity:\n\t");
                System.out.println(getStringJoinedBy(laptops, "\n\t"));
                break;
            default:
                throw new IllegalArgumentException("Invalid data type specified.");
        }
	}

	private static String getStringJoinedBy(ArrayList laptops, String del) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<laptops.size(); i++) {
			sb.append(laptops.get(i).toString());
			sb.append(del);
		}
		return sb.toString();
	}

	private static ArrayList<Laptop> readLaptopInputs(Scanner scanner) {
		ArrayList<Laptop> list = new ArrayList<Laptop>();
		System.out.println("Laptops (format: \"brand,processor,memory,hdd\". end to finish): ");
		while (scanner.hasNext()) {
			String line = scanner.next();
			if (line.equals("end"))
				break;
			try {
				String[] detail = line.split(",");
				String brand = detail[0];
				Double proc = Double.parseDouble(detail[1]);
				int ram = Integer.parseInt(detail[2]);
				int hdd = Integer.parseInt(detail[3]);
				Laptop lap = new Laptop(brand, proc, ram, hdd);
				list.add(lap);
			} catch(NumberFormatException ex) {System.out.println("Stop being stupid. Try again!");}
		}
		return list;
	}

	private static ArrayList<Integer> readIntegerInputs(Scanner scanner) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("Provide integers(one per line) [type 'end' to finish list]: ");
		while (scanner.hasNext()) {
			String line = scanner.next();
			if (line.equals("end"))
				break;
			try {
				list.add(Integer.parseInt(line));
			} catch(NumberFormatException ex) {System.out.println("Stop being stupid. Try again!");}
		}
		return list;
	}
}

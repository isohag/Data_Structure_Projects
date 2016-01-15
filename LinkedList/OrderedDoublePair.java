//	Created by Rashedul Khan (108921821)
//	CSE 214 HW #1

public class OrderedDoublePair {
	
	//*	Data	*//
	public static final OrderedDoublePair ORIGIN = new OrderedDoublePair(0,0);
	private double x, y;
	
	//*	Constructor	*//
	
	public OrderedDoublePair(){
		this.x = 0;
		this.y = 0;
	}
	public OrderedDoublePair(int x, int y){
		this.x = x;
		this.y = y;
	}
	public OrderedDoublePair(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//*	 Accessor/Mutator	*//
	
	public double getX() { return this.x;}
	public double getY() {return this.y;}
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	
	//*		Methods		*//
	
	@Override public boolean equals(Object pair2){
		OrderedDoublePair p2 = (OrderedDoublePair) pair2;
		return this.x == p2.getX() && this.y == p2.getY();
	}
	
	public boolean equalsIgnoreOrder(Object pair2){
		OrderedDoublePair p2 = (OrderedDoublePair) pair2;
		if (this.x == p2.getX()) {
			if (this.y == p2.getY())
				return true;
		}
		else {
			if (this.x == p2.getY()){
				if (this.y == p2.getX())
					return true;
			}			
		}
		return false;
	}
	
	@Override public String toString() {
		return "(" +this.getX()+ "," + this.getY() + ")";
	}
	
	@Override 
	public int h​ashCode() { 
	i​nt r​esult;
	l​ong t​emp = Double.d​oubleToLongBits​(x​)​;
	result = (i​nt)​ (temp ^ (temp >>> ​32)​);
	temp = Double.d​oubleToLongBits​(y​)​;
	r​eturn 3​1 *​ result + (i​nt​) (temp ^ (temp >>> ​32​));
	}

}

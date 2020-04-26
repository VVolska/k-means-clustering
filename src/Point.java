import java.awt.Color;

public class Point {

	double x, y;
	Centroid theClosest;
	Color c;
	
	public Point(int x, int y, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
	}
	double getX(){
		return x;
	}
	
	double getY(){
		return y;
	}
	
	Color getColor(){
		return c;
	}
	void setC(Color nowy){
		this.c = nowy;
	}
	void setTheClosest(Centroid centroid){
		this.theClosest = centroid;
	}
	Centroid getTheClosest(){
		return theClosest;
	}
}

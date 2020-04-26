import java.awt.Color;
import java.util.ArrayList;

public class Centroid extends Point {
	
	ArrayList<Point> theClosestList;
	double oldX, oldY;

	public Centroid(int x, int y, Color c) {
		super(x, y, c);
		this.theClosestList = new ArrayList<>();
	}
	
	void setOldX(double d){
		this.oldX = d;
	}
	double getOldX(){
		return oldX;
	}
	void setOldY(double y){
		this.oldY = y;
	}
	double getOldY(){
		return oldY;
	}
	void setNewX(double tmp){
		this.x = tmp;
	}
	void setNewY(double tmp){
		this.y = tmp;
	}
}


import java.util.ArrayList;
import java.util.List;

public class Equation {
	ArrayList<Point> points;
	ArrayList<Centroid> centroids;

	public Equation(ArrayList<Point> points, ArrayList<Centroid> centroids) {
		this.points = points;
		this.centroids = centroids;
	}

	void euklidesowa() {
		double odleglosc;
		for (Point p : points) {
			odleglosc = 10000000;
			Centroid tmp = null;
			for (Centroid c : centroids) {
				double odl = Math.sqrt(Math.pow(c.getX() - p.getX(), 2) + Math.pow(c.getY() - p.getY(), 2));
				if (odl < odleglosc) {
					odleglosc = odl;
					tmp = c;
					
				}
			}
			p.setC(tmp.getColor());
			tmp.theClosestList.add(p);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wyliczanie srodka grupy
		for (Centroid c : centroids) {
			double [] tmp = wyliczSrodekGrupy(c);
			c.theClosestList.clear();
			c.setOldX(c.getX());
			c.setOldY(c.getY());
			c.setNewX(tmp[0]);
			c.setNewY(tmp[1]);
			
		}
	}
	
	void miastowa(){
		double odleglosc;
		for (Point p : points) {
			odleglosc = 10000000;
			Centroid tmp = null;
			for (Centroid c : centroids) {
				double odl = modul(c.getX() - p.getX()) + modul(c.getY() - p.getY());
				if (odl < odleglosc) {
					odleglosc = odl;
					tmp = c;
					
				}
			}
			p.setC(tmp.getColor());
			tmp.theClosestList.add(p);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wyliczanie srodka grupy
		for (Centroid c : centroids) {
			double [] tmp = wyliczSrodekGrupy(c);
			c.theClosestList.clear();
			c.setOldX(c.getX());
			c.setOldY(c.getY());
			c.setNewX(tmp[0]);
			c.setNewY(tmp[1]);
			
		}
	}
	
	void chebysheva(){
		double odleglosc;
		for (Point p : points) {
			odleglosc = 10000000;
			Centroid tmp = null;
			for (Centroid c : centroids) {
				double odl = Math.max(modul(c.getX() - p.getX()),modul(c.getY() - p.getY()));
				if (odl < odleglosc) {
					odleglosc = odl;
					tmp = c;
					
				}
			}
			p.setC(tmp.getColor());
			tmp.theClosestList.add(p);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wyliczanie srodka grupy
		for (Centroid c : centroids) {
			double [] tmp = wyliczSrodekGrupy(c);
			c.theClosestList.clear();
			c.setOldX(c.getX());
			c.setOldY(c.getY());
			c.setNewX(tmp[0]);
			c.setNewY(tmp[1]);
			
		}
	}
	
	void minkowskiego() {
		double odleglosc;
		for (Point p : points) {
			odleglosc = 10000000;
			Centroid tmp = null;
			for (Centroid c : centroids) {
				double odl = Math.cbrt(modul(Math.pow(c.getX() - p.getX(), 3)) + modul(Math.pow(c.getY() - p.getY(), 3)));
				if (odl < odleglosc) {
					odleglosc = odl;
					tmp = c;
					
				}
			}
			p.setC(tmp.getColor());
			tmp.theClosestList.add(p);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wyliczanie srodka grupy
		for (Centroid c : centroids) {
			double [] tmp = wyliczSrodekGrupy(c);
			c.theClosestList.clear();
			c.setOldX(c.getX());
			c.setOldY(c.getY());
			c.setNewX(tmp[0]);
			c.setNewY(tmp[1]);
			
		}
	}

	double[] wyliczSrodekGrupy(Centroid c ) {
		double x=0, y=0,  liczba=0;
		for (Point p : c.theClosestList) {
			x+= p.getX();
			y+= p.getY();
			liczba++;
		}
		if(liczba ==0) liczba = 1;
		x= (int)x/liczba;
		y=(int)y/liczba;
		double[] result = {x,y};
		return result;
	}
	
	

	public double modul(double a){
		if(a<0) a=-a;
		return a;
	}

}

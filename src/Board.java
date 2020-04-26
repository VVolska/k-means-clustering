import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Board implements ActionListener {
	private static final Graphics Graphics = null;
	JLabel iloscCentroidowLabel = new JLabel("Ilość centroidów: ");
	JTextField iloscCentroidowField = new JTextField("20");
	JLabel iloscPunktowLabel = new JLabel("Ilość punktów: ");
	JTextField iloscPunktowField = new JTextField("1000");
	String[] odleglosci = { "Wybierz odleglosc", "Euklidesowa", "Miastowa", "Chebysheva" , "Minkowskiego"};
	JComboBox wybierzOdleglosc = new JComboBox(odleglosci);
	JButton startButton = new JButton("start");
	JButton wyczyscButton = new JButton("Wyczyść");
	JTextField przebiegPetliField = new JTextField("Przebieg Pętli");

	JPanel panel = new JPanel(new BorderLayout());
	JPanel panelUp = new JPanel();
	JPanel panelDown = new JPanel(new GridLayout(2, 1));
	JPanel panelDownUp = new JPanel(new GridLayout(1, 4));
	JPanel panelDownDown = new JPanel(new GridLayout(1, 4));

	Equation eq;
	int iloscCentroidow, iloscPunktow;
	String odleglosc;
	ArrayList<Point> points;
	ArrayList<Centroid> centroids;
	int width = 4, height = 4;

	public Board() {
		JFrame frame = new JFrame("Centroidy");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.add(panel);
		panel.add(panelUp);
		panel.add(panelDown, BorderLayout.SOUTH);
		panelDown.add(panelDownUp);
		panelDown.add(panelDownDown);

		panelDownUp.add(iloscCentroidowLabel);
		panelDownUp.add(iloscCentroidowField);
		panelDownUp.add(iloscPunktowLabel);
		panelDownUp.add(iloscPunktowField);
		panelDownDown.add(wybierzOdleglosc);
		panelDownDown.add(startButton);
		panelDownDown.add(wyczyscButton);
		panelDownDown.add(przebiegPetliField);

		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		panelUp.setBackground(Color.BLACK);

		wybierzOdleglosc.setSelectedIndex(0);
		wybierzOdleglosc.addActionListener(this);
		iloscCentroidowField.addActionListener(this);
		iloscPunktowField.addActionListener(this);
		startButton.addActionListener(this);
		wyczyscButton.addActionListener(this);

		actionPerformed(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			iloscCentroidow = Integer.parseInt(iloscCentroidowField.getText());
			iloscPunktow = Integer.parseInt(iloscPunktowField.getText());
			odleglosc = (String) wybierzOdleglosc.getSelectedItem();

			if (e.getSource().equals(startButton)) {
				// panelUp = new GraphPanel();
				startAction();
				if(wybierzOdleglosc.getSelectedItem().equals("Euklidesowa")){
					euklidesowa();
					
				}else if(wybierzOdleglosc.getSelectedItem().equals("Miastowa")){
					miastowa();
					
				}else if(wybierzOdleglosc.getSelectedItem().equals("Chebysheva")){
					chebysheva();
					
				}else if(wybierzOdleglosc.getSelectedItem().equals("Minkowskiego")){
					minkowskiego();
					
				}
			} else if (e.getSource() == wyczyscButton) {
				panelUp.repaint();
				przebiegPetliField.setText("Przebieg pętli");

			} else {

			}

		} catch (NumberFormatException n) {
			throw new IllegalArgumentException(n);
		} catch (NullPointerException p) {

		}

	}
	
	private void euklidesowa(){
		
		boolean loop = true;
		eq = new Equation(points, centroids);
		int numer = 0;
		while(loop){	
			numer ++;
			eq.euklidesowa();
			Graphics g = panelUp.getGraphics();
			for(Point p:eq.points){
				g.setColor(p.getColor());
				g.drawOval((int)p.getX() ,(int) p.getY() , width , height);
				g.fillOval((int)p.getX() , (int)p.getY() , width , height );
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Centroid c : eq.centroids){
				g.setColor(Color.BLACK);
				g.drawOval((int)c.getOldX() , (int)c.getOldY() , width , height  );
				g.fillOval((int)c.getOldX() ,(int) c.getOldY() , width , height );
				if(eq.modul(c.getOldX() - c.getX())==0 && eq.modul(c.getOldY() - c.getY())==0 ) loop =false;
					g.setColor(Color.RED);
					g.drawOval((int)c.getX() , (int)c.getY() , width , height );
					g.fillOval((int)c.getX() ,(int) c.getY() , width , height );
				
				
				
			}
			
			przebiegPetliField.setText("Ilość kroków: " + numer);
		}
	}
	
	private void miastowa(){
		boolean loop = true;
		eq = new Equation(points, centroids);
		int numer = 0;
		while(loop){	
			numer ++;
			eq.miastowa();
			Graphics g = panelUp.getGraphics();
			for(Point p:eq.points){
				g.setColor(p.getColor());
				g.drawOval((int)p.getX() ,(int) p.getY() , width , height);
				g.fillOval((int)p.getX() , (int)p.getY() , width , height );
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Centroid c : eq.centroids){
				g.setColor(Color.BLACK);
				g.drawOval((int)c.getOldX() , (int)c.getOldY() , width , height  );
				g.fillOval((int)c.getOldX() ,(int) c.getOldY() , width , height );
				g.setColor(Color.RED);
				g.drawOval((int)c.getX() , (int)c.getY() , width , height );
				g.fillOval((int)c.getX() ,(int) c.getY() , width , height );
				if(eq.modul(c.getOldX() - c.getX())==0 && eq.modul(c.getOldY() - c.getY())==0 ) loop =false;
			}
			
			przebiegPetliField.setText("Ilość kroków: " + numer);
		}
	}
	
	private void chebysheva(){
		boolean loop = true;
		eq = new Equation(points, centroids);
		int numer = 0;
		while(loop){	
			numer ++;
			eq.chebysheva();
			Graphics g = panelUp.getGraphics();
			for(Point p:eq.points){
				g.setColor(p.getColor());
				g.drawOval((int)p.getX() ,(int) p.getY() , width , height);
				g.fillOval((int)p.getX() , (int)p.getY() , width , height );
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Centroid c : eq.centroids){
				g.setColor(Color.BLACK);
				g.drawOval((int)c.getOldX() , (int)c.getOldY() , width , height  );
				g.fillOval((int)c.getOldX() ,(int) c.getOldY() , width , height );
				g.setColor(Color.RED);
				g.drawOval((int)c.getX() , (int)c.getY() , width , height );
				g.fillOval((int)c.getX() ,(int) c.getY() , width , height );
				if(eq.modul(c.getOldX() - c.getX())==0 && eq.modul(c.getOldY() - c.getY())==0 ) loop =false;
			}
			
			przebiegPetliField.setText("Ilość kroków: " + numer);
		}
	}
	
	private void minkowskiego(){
		boolean loop = true;
		eq = new Equation(points, centroids);
		int numer = 0;
		while(loop){	
			numer ++;
			eq.minkowskiego();
			Graphics g = panelUp.getGraphics();
			for(Point p:eq.points){
				g.setColor(p.getColor());
				g.drawOval((int)p.getX() ,(int) p.getY() , width , height);
				g.fillOval((int)p.getX() , (int)p.getY() , width , height );
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Centroid c : eq.centroids){
				g.setColor(Color.BLACK);
				g.drawOval((int)c.getOldX() , (int)c.getOldY() , width , height  );
				g.fillOval((int)c.getOldX() ,(int) c.getOldY() , width , height );
				g.setColor(Color.RED);
				g.drawOval((int)c.getX() , (int)c.getY() , width , height );
				g.fillOval((int)c.getX() ,(int) c.getY() , width , height );
				if(eq.modul(c.getOldX() - c.getX())==0 && eq.modul(c.getOldY() - c.getY())==0 ) loop =false;
			}
			
			przebiegPetliField.setText("Ilość kroków: " + numer);
		}
	}
	public void startAction() {
		rysujPunkty();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rysujCentroidy();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public int getPunkty() {
		return iloscPunktow;
	}

	public void rysujPunkty() {
		points = new ArrayList<>();
		Graphics g = panelUp.getGraphics();
		int punkty = getPunkty();
		g.setColor(Color.WHITE);
		for (int i = 0; i < punkty; i++) {
			int x = (int) (Math.random() * panelUp.getWidth());
			int y = (int) (Math.random() * panelUp.getHeight());
			points.add(new Point(x,y,Color.WHITE));
			g.drawOval(x, y, width, height);
			g.fillOval(x, y, width, height);
		}
	}
	
	public void rysujCentroidy(){
		centroids = new ArrayList<>();
		Graphics g = panelUp.getGraphics();
		int centroidy = iloscCentroidow;
		ArrayList<Color> kolory = new ArrayList<>();
		kolory.add(Color.GREEN);
		kolory.add(Color.BLUE);
		kolory.add(Color.magenta);
		kolory.add(Color.YELLOW);
		kolory.add(Color.PINK);
		kolory.add(Color.ORANGE);
		kolory.add(Color.DARK_GRAY);
		kolory.add(Color.cyan);
		kolory.add(Color.white);
		kolory.add(Color.GRAY);
		
		int index = 0;
		for (int i = 0; i < centroidy; i++) {
			g.setColor(kolory.get(index));
			int x = (int) (Math.random() * panelUp.getWidth());
			int y = (int) (Math.random() * panelUp.getHeight());
			centroids.add(new Centroid(x,y ,kolory.get(index)));
			index++;
			g.drawOval(x, y, width, height);
			g.fillOval(x, y, width, height);
			if(index > 9) index = 0;
		}

	}
	
	
}
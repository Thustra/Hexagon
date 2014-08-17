package ui;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.paint.Color;

import javax.swing.JPanel;

import world.Coordinate;
import world.HexMap;
import world.Hexagon;
import world.axialCoordinate;
import world.cubeCoordinate;
 
public class DrawMap extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7577109612093697450L;
	private HexMap worldmap;
	
	public DrawMap(HexMap world){
		this.setSize(700, 500);
		this.setWorldmap(world);
		this.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				
				highlightNeighbours(x, y);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void highlightNeighbours(int x, int y) {
		HexMap nb = worldmap.getHexagonByScreenCoordinates(x, y).getNeighbours();	
		Iterator it = nb.getHashOfHexes().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			worldmap.getByCoordinate((Coordinate) pairs.getKey()).setHexColor(Color.AQUAMARINE);
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
	  
		super.paintComponent(g);

		Hashtable hexesToDraw = worldmap.getHashOfHexes();
		int i = hexesToDraw.size();

		Iterator<Map.Entry<axialCoordinate, Hexagon>> it = hexesToDraw.entrySet().iterator();

		while (it.hasNext()){
			Map.Entry<axialCoordinate, Hexagon> entry = it.next();

			// Calculate the centerpoint of a hexagon
			double center_x =  (entry.getValue().getSize() * Math.sqrt(3) * (entry.getKey().getQ() + (double)entry.getKey().getR()/2));
			double center_y = entry.getValue().getSize() * 3/2 * (double)entry.getKey().getR();

			Polygon p = new Polygon();
			for (int j = 0; j < 6; j++)
				p.addPoint((int) (100+center_x + entry.getValue().getSize() * Math.cos(2 * Math.PI / 6 * (j + 0.5))),
						(int) (100+center_y + entry.getValue().getSize() * Math.sin(2 * Math.PI / 6 * (j + 0.5))));

			g.drawPolygon(p);
			g.setColor(entry.getValue().getHexColor());
			g.fillPolygon(p);
		}
	}

	public HexMap getWorldmap() {
		return worldmap;
	}

	private void setWorldmap(HexMap worldmap) {
		this.worldmap = worldmap;
	}

}
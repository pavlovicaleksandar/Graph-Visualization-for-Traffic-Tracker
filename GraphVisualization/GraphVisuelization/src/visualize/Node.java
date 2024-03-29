package visualize;

import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Node extends Circle  {
	
	private Map<String,Edge> edges;
	private String id;
	private int degree;
	private Color color;
	private double size;
	private Text label;
	
	public Node(String idd) { // constructor for node
		
		id=idd;
		size=10; // radius
		color=Color.AQUAMARINE;
		edges=new HashMap<String,Edge>(); // edges that this node has
		
		label=new Text(idd);
		label.setFill(Color.BLACK);
		label.xProperty().bind(this.centerXProperty().subtract(label.getBoundsInLocal().getWidth()/2));
		label.yProperty().bind(this.centerYProperty().add(label.getBoundsInLocal().getHeight()/16));
		label.setVisible(false);
		
		super.setRadius(size);                                 
		super.setFill(color);
		super.setCenterX(Visualization.startX+ Math.random()*(Visualization.endX-Visualization.startX)); // random generating X relative to size of pane
		super.setCenterY(50+Math.random()*(Visualization.endY-50)); // random generating Y relative to size of pane
		
	}
	
	public void addEdge(String id1,Edge e) throws ExistException {
		
		if(edges.get(id1)!=null) throw new ExistException("Edge "+ id+"-"+id1+" already exist");
		
		degree++;
		edges.put(id1, e);
		
	}
	
	public void removeEdge(String id1) throws NotExistException {
		
		if(edges.get(id1)==null) throw new NotExistException("Edge "+ id+"-"+id1+" doesnt exist");
		
		degree--;
		edges.remove(id1);
		
	}
	
	public String toString() {
		
		return id;
		
	}
	
	
	
	// GETTERS AND SETTERS
	public Map<String,Edge> getEdges(){
		
		return edges;
	}
	public String getIDDD() { // because there is getId already from super class
		
		return id;
		
	}	
	public int getDegree() {
	
		return degree;
		
	}
	public double getSize() {
		
		return size;
		
	}
	public void setDegree(int d) {
		
		degree=d;
		
	}
	public void setSize(double size) {
		
		this.size = size;
		super.setRadius(size);
		
	}
	public Color getColor() {
		
		return color;
		
	}
	public void setColor(Color color) {
		
		this.color = color;
		super.setFill(color);
		
	}
	public double getX() {
		
		return super.getCenterX();
		
	}
	public void setX(double x) {
		
		super.setCenterX(x);
		
		for(Edge e : edges.values()) { // updating all edges of this node
			e.update();
		}
		
	}
	public double getY() {
		
		return super.getCenterY();
		
	}
	public void setY(double y) {
		
		super.setCenterY(y);
		
		for(Edge e : edges.values()) { // updating positions all edges of node
			
			e.update();
		}
		
	}
	public Text getLabel() {
		
		return label;
		
	}
	public void setLabel(String lab) {
		
		label.setText(lab);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

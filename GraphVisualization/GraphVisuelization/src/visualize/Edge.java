package visualize;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Edge extends Line {
	
	private Node n1,n2;
	private Color color;
	private Text label;
	
	public Edge(Node n11,Node n22,String lab) {
		
		n1=n11; n2=n22;
		color=Color.BLACK;
		
		label=new Text();
		label.setText(lab);
		label.xProperty().bind(this.startXProperty().add(this.endXProperty().subtract(this.startXProperty()).divide(2)));
		label.yProperty().bind(this.startYProperty().add(this.endYProperty().subtract(this.startYProperty()).divide(2)));
		label.setVisible(false);
		
		super.setStroke(color);
		update();
		

	}
	
	public Edge(Node n11,Node n22) {
		
		n1=n11; n2=n22;
		color=Color.BLACK;
		
		super.setFill(color);
		update();
		
	}
	
	public void update() { // updating coordinates of Line
		
		super.setStartX(n1.getCenterX());
		super.setStartY(n1.getCenterY());
		super.setEndX(n2.getCenterX());
		super.setEndY(n2.getCenterY());
		
	}
	

	
	public String toString() {
		
		return n1.toString()+" "+n2.toString()+" "+label;
		
	}
	
	// GETTERS AND SETTERS
	public Node getN1() {
		
		return n1;
		
	}
	public void setN1(Node n1) {
		
		this.n1 = n1;
		
	}
	public Node getN2() {
		
		return n2;
		
	}
	public void setN2(Node n2) {
		
		this.n2 = n2;
		
	}
	public Text getLabel() {
		
		return label;
		
	}
	public void setLabel(String lab) {
		
		label.setText(lab);
		
	}
	public Color getColor() {
		
		return color;
		
	}
	public void setColor(Color color) {
		
		this.color = color;
		super.setStroke(color);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

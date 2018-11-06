package visualize;
import javafx.application.Platform;

public class ExpCont extends Thread {

	private boolean exp; // true-> expansion / false-> contraction
	private double scaleFactor=1.0; // set initial value
	private Graph graph;
	
	public void run() {
		
		try {
			
			while(true) {
				
				synchronized(this) {
					
				wait();
				
				}
				
				if(exp) {
					
					double x=graph.getGraphCenterX();
					double y=graph.getGraphCenterY();
					
					for(Node n : graph.getAllNodes().values()) {
						
						Platform.runLater(new Runnable() {
							
							public void run() {
								
								n.setX(n.getX()-((x-n.getX())*scaleFactor));
								n.setY(n.getY()-((y-n.getY())*scaleFactor));
								
							}
							
						});
					}
					
				}else {
					
					double x=graph.getGraphCenterX();
					double y=graph.getGraphCenterY();
					
					for(Node n : graph.getAllNodes().values()) {
						
						Platform.runLater(new Runnable() {
							
							public void run() {
								
								n.setX(n.getX()+((x-n.getX())*(scaleFactor-1)));
								n.setY(n.getY()+((y-n.getY())*(scaleFactor-1)));
								
							}
							
						});
					
						
					}
					
				}
				
			}
			
		}catch(Exception e) {
			
		}
	}

	public void setExp(boolean exp) {
		
		this.exp = exp;
		
	}

	public void setGraph(Graph graph) {
		
		this.graph = graph;
		
	}

	public void setScaleFactor(double scaleFactor) {
		
		this.scaleFactor = scaleFactor;
		
	}
	
	public synchronized void wakeup() {
		
		notify();
		
	}

}

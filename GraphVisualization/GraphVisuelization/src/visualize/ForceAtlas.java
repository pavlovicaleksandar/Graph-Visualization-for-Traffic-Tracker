package visualize;
import javafx.application.Platform;

public class ForceAtlas extends Thread {

	private Graph graph;
	private boolean work;
	
	
	public void run() {
		try {
			while (true) {
				
				synchronized (this) {
					
					while(!work) {
						
						wait();
						
					}
					
				}
				sleep(5);
				for (Node n : graph.getAllNodes().values()) {

					double nx = n.getX();
					double ny = n.getY();

					double d = 0.0001 + Math.sqrt(nx * nx + ny * ny);
					double gf = 0.0001 * 30 * d;

					
					Platform.runLater(new Runnable() {
						
						public void run() {
							
							n.setX(n.getX() - (gf * nx / d));
							n.setY(n.getY() - (gf * ny / d));
						}
						
					});
					
				}
				
				for(Node n1: graph.getAllNodes().values()) {
					
					for(Node n2 : graph.getAllNodes().values()) {
						
						if(n1!=n2) {
							
							double angle=Math.atan2(n2.getY()-n1.getY(), n2.getX()-n1.getX()); // angle in radians
							double f1=0.001*((n1.getDegree()+1)*(n2.getDegree()+1))/Math.sqrt(Math.pow((n1.getX()-n2.getX()),2)+Math.pow((n1.getY()-n2.getY()),2));
							double f2=0.001*Math.sqrt(Math.pow((n1.getX()-n2.getX()),2)+Math.pow((n1.getY()-n2.getY()),2));
							double f11=angle*(f2-f1)*(-0.00013567);
							double f22=angle*(f1-f2)*(-0.000153567);
							
							Platform.runLater(new Runnable() {
								
								public void run() {
								
									
									n1.setX((-1)*f11*n1.getX()+n1.getX());
									n1.setY(n1.getY()+(-1)*n1.getY()*f22);
									
									n2.setX((-1)*f22*n2.getX()+n2.getX());
									n2.setY(n2.getY()+n2.getY()*f11);
									
									
								}
							});
						}
					}
				}
			}
		
			
		} catch (InterruptedException e) {

		}
		
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}
	
	public synchronized void wakeup() {
		
		work=true;
		notify();
		
	}
	
}

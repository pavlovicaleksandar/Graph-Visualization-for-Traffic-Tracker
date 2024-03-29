package undoredo;

import visualize.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import javafx.scene.paint.Color;

public class NodeReadd extends AbstractUndoableEdit {

	private Graph g;
	private String id;
	private int degree;
	private Color color;
	private double size;
	private String label;
	private double x,y;

	public NodeReadd(Graph gg, String idd, String lab, double siz, Color col, int dgr,double xx,double yy) {

		id = idd; label = lab; size = siz; color = col; degree = dgr; g = gg; x=xx; y=yy;

	}

	@Override
	public void redo() throws CannotRedoException {
		
		try {
			
			g.addNode(id);
			Node n = g.getNode(id);
			n.setDegree(degree);
			n.setLabel(label);
			n.setColor(color);
			n.setSize(size);
			n.setX(x);
			n.setY(y);
			
		} catch (ExistException e) {

		}

	}

	@Override
	public void undo() throws CannotUndoException {

		try {

			g.removeNode(id);

		} catch (NotExistException e) {

		}
	

	}

}

package undoredo;

import java.util.HashMap;
import java.util.HashSet;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import visualize.Graph;
import visualize.Node;
import javafx.scene.paint.Color;


public class NodeRecolor extends AbstractUndoableEdit {

	private Color newColor;
	private HashSet<Node> nodes;
	private HashMap<Node,Color> oldColor;
	
	public NodeRecolor(HashMap<Node,Color> oldC,Color newC,HashSet<Node> n) {
		
		oldColor=oldC; newColor=newC; nodes=n;
		
	}
	
	@Override
	public void redo() throws CannotRedoException {
		
		for(Node n : nodes) { // iterate throught nodes and set new size to them
			
			n.setColor(newColor);
		
		}
		
		
	}
	
	@Override
	public void undo() throws CannotUndoException { // iterate throught nodes and set old size to them
		
		for(Node n : nodes) {
			
			n.setColor(oldColor.get(n));
		
		}
		
	}
	
	
}

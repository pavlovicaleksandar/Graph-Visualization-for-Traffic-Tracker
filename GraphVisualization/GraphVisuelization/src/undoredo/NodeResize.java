package undoredo;
import visualize.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class NodeResize extends AbstractUndoableEdit {

	private double newSize;
	private HashSet<Node> nodes;
	private HashMap<Node,Double> oldSize;
	
	public NodeResize(HashMap<Node,Double> oldS,double newS,HashSet<Node> n) {
		
		oldSize=oldS; newSize=newS; nodes=n;
		
	}
	
	@Override
	public void redo() throws CannotRedoException {
		
		for(Node n : nodes) { // iterate throught nodes and set new size to them
			
			n.setSize(newSize);
		
		}
		
		
	}
	
	@Override
	public void undo() throws CannotUndoException { // iterate throught nodes and set old size to them
		
		for(Node n : nodes) {
			
			n.setSize(oldSize.get(n));
		
		}
		
	}
	
	
	
}

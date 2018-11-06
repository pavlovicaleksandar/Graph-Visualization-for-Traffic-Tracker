package formats;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.util.regex.*;

import javafx.scene.paint.Color;
import visualize.Edge;
import visualize.ExistException;
import visualize.Graph;
import visualize.Node;

public class TXT extends Format {

	@Override
	public Graph load(Graph g, File f) {

		File file = f; // new File(fname);
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String line;

			while ((line = buff.readLine()) != null) {

				String[] words = line.split(" ");

				for(String s : words) {
				//	System.out.println(s);
				}
				if (words[0].equals("node")) { // to be sure that this line is for node

					String id = words[1];
					double x = Double.parseDouble(words[2]);
					double y = Double.parseDouble(words[3]);
					int degree = Integer.parseInt(words[4]);
					double size = Double.parseDouble(words[5]);
					if(words[6].equals("null"))
						words[6]="";
					String label = words[6];
					double red = Double.parseDouble(words[7]);
					double green = Double.parseDouble(words[8]);
					double blue = Double.parseDouble(words[9]);

					try {

						g.addNode(id);
						Node n=g.getNode(id);
						n.setX(x);
						n.setY(y);
						n.setSize(size);
						n.setDegree(degree);
						n.setLabel(label);
						n.setColor(Color.color(red, green, blue));
						
					} catch (ExistException e) {

					}

					// System.out.println(words.length+" "+words[0]);

				}else {
					
					String id1=words[1];
					String id2=words[2];
					if(words[3].equals("null"))
						words[3]="";
					String lab=words[3];
					double red = Double.parseDouble(words[4]);
					double green = Double.parseDouble(words[5]);
					double blue = Double.parseDouble(words[6]);
					
					try {
						
						g.addEdge(id1, id2, lab);
						g.getEdge(id1, id2).setColor(Color.color(red, green, blue));
						
					}catch(ExistException e) {
						
					}
					
				}
				
			}
			
			buff.close();
			
		} catch (IOException e) {

		}
		
		return g;
		
	}

	public void save(Graph g, File file) {
		
		try {
			File fout = file;
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			for(Node n: g.getAllNodes().values()) {
				
				String lab;
				
				if(n.getLabel().getText().equals("")) {
					
					lab="null"; // if it doesnt have label
					
				}else {
					lab=n.getLabel().getText();
				}
				
				bw.write("node "+n.getIDDD()+" "+n.getX()+" "+n.getY()+" "+n.getDegree()+" "+n.getSize()+" "+lab+" "+n.getColor().getRed()+" "+n.getColor().getGreen()+" "+n.getColor().getBlue());
				bw.newLine();
			}
			
			
			for(Edge e: g.getAllEdges().values()) {
				
				String lab=e.getLabel().getText();
				
				if(lab.equals("")) {
					
					lab="null"; // if it doesnt have label
					
				}
				
				
				bw.write("edge "+e.getN1().getIDDD()+" "+e.getN2().getIDDD()+" "+lab+" "+e.getColor().getRed()+" "+e.getColor().getGreen()+" "+e.getColor().getBlue());
				bw.newLine();
				
			}
			
			bw.close();
		}catch(Exception e) {
		}
		
		
	}
	
	static public void main(String[] args) {

	}

}

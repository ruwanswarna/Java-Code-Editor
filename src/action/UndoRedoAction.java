package action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class UndoRedoAction {
	
	//private static LinkedList<UndoRedo> changes =new LinkedList<>(); 
	//private static Stack<String> Undo = new Stack<>();
	//private static Stack<String> Redo = new Stack<>();
	
	public static UR_Element sendQuery(String project,String _package,String fileName, String query ,String text, int start, int end) {
		
		LinkedList<UndoRedo> changes = UndoRedo.getChanges();
		Iterator<UndoRedo> itr=changes.iterator();  
		while(itr.hasNext()){  
		   
			UndoRedo undoredoObj = itr.next();
		    String curr_project = undoredoObj.getProject();
		    String curr_package = undoredoObj.getPackage();
		    String curr_fileName = undoredoObj.getFileName();
		    if(curr_project.equals(project) && curr_package.equals(_package) && curr_fileName.equals(fileName)) {
		    	
		    	Stack<UR_Element> Undo =UndoRedo.getUndo();
		    	Stack<UR_Element> Redo =UndoRedo.getRedo();
		    	if(query=="UNDO") {
		    		 return UndoRedo.UNDO(Undo,Redo);
				}
				else if(query=="REDO") {
					 return UndoRedo.REDO(Undo,Redo);
				}
				else if(query=="WRITE") {
					UndoRedo.WRITE(Undo, text,start, end);
					
				}
				else if(query=="DELETE") {
					UndoRedo.DELETE(Redo, text, start, end);
					
				}
		    	
		    }
		}
		return null;
		
	}
}

package action;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

public class UndoRedo {
	
	private String project;
	private String _package;
	private String fileName;
	
	private static Stack<UR_Element> Undo = new Stack<>();
	private static Stack<UR_Element> Redo = new Stack<>();
	private static LinkedList<UndoRedo> changes = new LinkedList<>();
	
	public UndoRedo(String project, String _package, String fileName) {
		
		this.project = project;
		this._package = _package;
		this.fileName = fileName;
		changes.add(this);
	}
	
	
	public static Stack<UR_Element> getUndo() {
		return Undo;
	}


	public void setUndo(Stack<UR_Element> undo) {
		Undo = undo;
	}


	public static Stack<UR_Element> getRedo() {
		return Redo;
	}


	public void setRedo(Stack<UR_Element> redo) {
		Redo = redo;
	}


	public static LinkedList<UndoRedo> getChanges() {
		return changes;
	}

	public static void setChanges(LinkedList<UndoRedo> changes) {
		UndoRedo.changes = changes;
	}


	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getPackage() {
		return _package;
	}
	public void setPackage(String _package) {
		this._package = _package;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	static void WRITE(Stack<UR_Element> Undo, String text, int start, int end){

		Undo.push(new UR_Element(text, start, end));
	}
	static void DELETE(Stack<UR_Element> Redo, String text, int start, int end){
		
		Redo.push(new UR_Element(text, start, end));
	}

	static UR_Element UNDO(Stack<UR_Element> Undo, Stack<UR_Element> Redo){
		
		
		try {
			UR_Element obj = Undo.peek();
			Undo.pop();
			Redo.push(obj);
			return obj;
			
		} catch (EmptyStackException e) {
			
		}
		return null;
	}

	static UR_Element REDO(Stack<UR_Element> Undo, Stack<UR_Element> Redo){
		
		try {
			UR_Element obj = Redo.peek();
			Redo.pop();
			Undo.push(obj);
			return obj;
		} catch (EmptyStackException e) {	
			
		}
		return null;
	}
	
	static void READ(Stack<String> Undo){
		
		Stack<String> revOrder = new Stack<String>();

		while (Undo.size() > 0){
				
			revOrder.push(Undo.peek());
			Undo.pop();
		}

		while (revOrder.size() > 0){
			
			System.out.print(revOrder.peek());
			Undo.push(revOrder.peek());

			revOrder.pop();
		}

		System.out.print(" ");
	}
	
}

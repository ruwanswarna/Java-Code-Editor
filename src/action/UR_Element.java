package action;

public class UR_Element {
	private String text;
	private int start;
	private int end;
	
	public UR_Element(String text, int start, int end) {
		this.text = text;
		this.start = start;
		this.end = end;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}

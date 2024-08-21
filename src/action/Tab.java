package action;

public class Tab {
	private String source;
	private String _package;
	private String title;
	private String content;
	
	private boolean isSaved;
	private boolean isMainClass = false;
	
	public void createUndoRedoObject() {
		
		UndoRedo object = new UndoRedo(source,_package,title);
	}
	
	@Override
	public String toString() {
		return "Tab [source=" + source + ", _package=" + _package + ", title=" + title + ", content=" + content
				+ ", isSaved=" + isSaved + "]";
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String get_package() {
		return _package;
	}
	public void set_package(String _package) {
		this._package = _package;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isSaved() {
		return isSaved;
	}
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	public boolean isMainClass() {
		return isMainClass;
	}
	public void setMainClass(boolean isMainClass) {
		this.isMainClass = isMainClass;
	}
	
}

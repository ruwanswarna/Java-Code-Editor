package action;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class DeletedTextFilter extends DocumentFilter {
	@Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        // Override remove method to track deleted text
        Document doc = fb.getDocument();
        String deletedText = doc.getText(offset, length);

        // Attach the deleted text as a property to the document
        doc.putProperty(Document.TitleProperty, deletedText);

        // Perform the actual removal
        super.remove(fb, offset, length);
	}
}

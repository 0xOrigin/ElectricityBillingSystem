package Views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author xorigin
 */
public class FixedSizeDocument extends PlainDocument {
    
    private final int max;

    public FixedSizeDocument(int max) {
        
        super();
        this.max = max;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        
        if(str == null) 
            return;

        if((getLength() + str.length()) <= max){
            
            super.insertString(offset, str, attr);
        }
    }       
    
}

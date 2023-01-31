package gui.level;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * only allows numbers and emptiness.
 */
public class NumberListener extends DocumentFilter {
  /**
   * accepted characters.
   */
  private final String acceptedCharacters = "0123456789";

  /**
   *  inserts String.
   *
   * @param fb filterbypass
   * @param offset offset
   * @param string the string
   * @param attr the attributes
   * @throws BadLocationException exception
   */
  @Override
  public void insertString(final DocumentFilter.FilterBypass fb,
                           final int offset, final String string,
                           final AttributeSet attr) throws
      BadLocationException {
    for (int i = 0; i < string.length(); i++) {
      if (!acceptedCharacters.contains(String.valueOf(string.charAt(i)))) {
        return;
      }
    }

    fb.insertString(offset, string, attr);

  }


  /**
   *  replaces String.
   *
   * @param fb filterbypass
   * @param length the length
   * @param offset offset
   * @param text the string
   * @param attrs the attributes
   * @throws BadLocationException exception
   */
  @Override
  public void replace(final DocumentFilter.FilterBypass fb, final int offset,
                      final int length, final String text,
                      final AttributeSet attrs) throws BadLocationException {


    for (int i = 0; i < text.length(); i++) {
      if (!acceptedCharacters.contains(String.valueOf(text.charAt(i)))) {
        return;
      }
    }

    fb.insertString(offset, text, attrs);
  }
}


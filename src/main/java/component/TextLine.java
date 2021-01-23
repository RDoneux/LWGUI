package component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.regex.Pattern;

public class TextLine extends Component {

    private Color background;
    private Color border;

    private boolean focused;

    private StringBuilder textBuilder;

    private String punctuation = "?!,.'-;()";

    public TextLine() {

        textBuilder = new StringBuilder();

        background = Color.LIGHT_GRAY;
        border = Color.BLACK;
        foreground = Color.BLACK;
        sizeEditable = true;
        edge = 5;

    }

    public Font scaleFont(String text, Rectangle rect, Graphics g) {
        float fontSize = 20.0f;

        font = g.getFont().deriveFont(fontSize);
        int width = g.getFontMetrics(font).stringWidth(text);
        fontSize = (rect.width / width) * fontSize;
        return g.getFont().deriveFont(fontSize);
    }

    @Override
    public void revise() {

    }

    @Override
    public void paint(Graphics g) {
        Rectangle clipBounds = g.getClipBounds();
        g.setClip(parent.getBounds());

        if (show) {
            font = new Font("Lucida Console", Font.PLAIN, height - 2);

            g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
            g.fillRoundRect(x, y, width, height, edge, edge);

            g.setFont(font);
            g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));

            int xOffset = 0; // if the text is larger than the display area, move it left by the excess amount
            if (g.getFontMetrics(font).stringWidth(textBuilder.toString()) > width) {
                xOffset = g.getFontMetrics(font).stringWidth(textBuilder.toString()) - width;
            }
            g.setClip(this.getBounds());
            g.drawString(textBuilder.toString(), ((x + 5) - xOffset), (y + g.getFontMetrics(font).getAscent()) + 2);
            g.setClip(parent.getBounds());

            g.setColor(new Color(border.getRed(), border.getGreen(), border.getBlue(), transparency));
            g.drawRoundRect(x, y, width, height, edge, edge);

            if (focused && transparency > 0) {
                g.setColor(new Color(50, 150, 200));
                g.drawRoundRect(x - 1, y - 1, width + 2, height + 2, edge, edge);
            }

        }

        g.setClip(clipBounds);

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (show && focused) {
            if (Character.isLetterOrDigit(e.getKeyCode()) || Character.isSpaceChar(e.getKeyCode())
                    || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", String.valueOf(e.getKeyChar()))) {
                textBuilder.append(e.getKeyChar());
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && textBuilder.length() > 0) {
                textBuilder.replace(textBuilder.length() - 1, textBuilder.length(), "");
            }
            setText(textBuilder.toString());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (getBounds().contains(e.getPoint())) {
            focused = true;
        } else {
            focused = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void resetText() {
        setText("");
        textBuilder = new StringBuilder();
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}

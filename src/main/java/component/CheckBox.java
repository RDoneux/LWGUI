package component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CheckBox extends Component {

    private boolean checked;

    private Color background;
    private Color border;

    private String text;

    private Dimension checkBoxSize;

    public CheckBox() {
        checked = false;

        background = Color.DARK_GRAY;
        foreground = Color.WHITE;
        border = Color.BLACK;

        sizeEditable = false;
        checkBoxSize = new Dimension(20, 20);

    }

    public CheckBox(String text) {

        this.text = text;

        checked = false;

        background = Color.DARK_GRAY;
        foreground = Color.WHITE;
        border = Color.BLACK;

        sizeEditable = false;
        checkBoxSize = new Dimension(20, 20);

    }

    @Override
    public void revise() {
        // EMPTY
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));
        g.setFont(font);
        g.drawString(text, x, y + g.getFontMetrics(font).getAscent());

        int xOffset = g.getFontMetrics(font).stringWidth(text) + 5;

        g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
        g.fillRect(x + xOffset, y, checkBoxSize.width, checkBoxSize.height); // draw background
        if (checked) {
            g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));
            g.fillRect(x + xOffset, y, checkBoxSize.width, checkBoxSize.height);
        }
        g.setColor(new Color(border.getRed(), border.getGreen(), border.getBlue(), transparency));
        g.drawRect(x + xOffset, y, checkBoxSize.width, checkBoxSize.height);

        width = xOffset + checkBoxSize.width;
        height = checkBoxSize.height;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //EMPTY
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //EMPTY
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //EMPTY
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (getBounds().contains(e.getPoint())) {
            checked = !checked;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //EMPTY
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //EMPTY
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }
}

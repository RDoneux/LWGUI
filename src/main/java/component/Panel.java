package component;

import tools.Maths;
import tools.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

/**
 * Basic GUIContainer used to position {@link Component}s within a frame.
 * Compatible with a {@link Layout} manager and {@link Animation}
 *
 * @author Robert Doneux
 * @version 0.1
 */

public class Panel extends Container {

    private Color background;
    private Image image;
    private BufferedImage masterImage;
    private AlphaComposite comp;
    private int contrast; // add some contrast to a image
    private boolean scale;

    public Panel() {
        setName("Panel");
        background = Color.LIGHT_GRAY;
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        sizeEditable = true;
        contrast = 0;
    }

    @Override
    public void revise() {

        if (image != null && width > 0 && height > 0 && scale) {
            if (image.getWidth(null) != width || image.getHeight(null) != height) {
                if (masterImage.getWidth() < width || masterImage.getHeight() < height) {
                    image = masterImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                } else {
                    image = masterImage.getSubimage(0, 0, width, height);
                }
                image = Utils.makeRoundedCorner(image, edge);
            }
        }

        for (GUIComponent child : children) {
            child.revise();
        }

        if (layout != null) {
            layout.updateLayout();
        }

    }

    @Override
    public void paint(Graphics g) {

        Rectangle previousClip = g.getClipBounds();

        // clip the paint call to the size of the parent container
        if (topLevelParent == null) {
            g.setClip(parent.getAnimationBounds());
        } else {
            g.setClip(topLevelParent.getBounds());
        }

        if (show) {
            if (image == null) {
                g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
                g.fillRoundRect(x, y, width, height, edge, edge);
            } else {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), contrast));
                AlphaComposite pre = (AlphaComposite) g2d.getComposite();
                if (contrast > 0) {
                    g.fillRoundRect(x, y, width, height, edge, edge);
                }
                g2d.setComposite(comp);
                g2d.drawImage(image, x, y, width, height, null);
                g2d.setComposite(pre);
            }

            for (GUIComponent child : children) {
                child.drawEffect(g);
                child.paint(g);
            }

        }
        g.setClip(previousClip);

        if (layout != null) {
            if (layout.isDebugging()) {
                layout.debug(g);
            }
        }

    }

    public void setBackground(Color colour) {
        this.background = colour;
    }

    @Override
    public void setTransparency(int transparency) {
        super.setTransparency(transparency);
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) Maths.map(transparency, 0, 255, 0, 1));
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseDragged(arg0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseMoved(arg0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseClicked(arg0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        for (GUIComponent child : children) {
            if (child.getBounds().contains(arg0.getPoint())) {
                child.mouseEntered(arg0);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseExited(arg0);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mousePressed(arg0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseReleased(arg0);
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        for (GUIComponent child : children) {
            child.keyPressed(arg0);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        for (GUIComponent child : children) {
            child.keyReleased(arg0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        for (GUIComponent child : children) {
            child.keyTyped(arg0);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0) {
        for (GUIComponent child : children) {
            child.mouseWheelMoved(arg0);
        }
    }

    public Image getImage() {
        return image;
    }

    /**
     * identify the image that should be drawn as a background. The contrast
     * identifies if an image of the colour of the background should be drawn to
     * provide some contrast. This works particularly well when black text is being
     * rendered onto a dark image.
     *
     * @param image    - the image to render
     * @param contrast - the transparency of the background contrast. 0 = no
     *                 contrast
     */
    public void setImage(BufferedImage image, int contrast, boolean scale) {
        this.scale = scale;
        this.contrast = contrast;
        this.masterImage = image;
        this.image = image;
    }

    /**
     * identify the image that should be drawn as a background. The contrast
     * identifies if an image of the colour of the background should be drawn to
     * provide some contrast. This works particularly well when black text is being
     * rendered onto a dark image. This method automatically sets the scale value to
     * false
     *
     * @param image    - the image to render
     * @param contrast - the transparency of the background contrast. 0 = no
     *                 contrast
     */
    public void setImage(BufferedImage image, int contrast) {
        this.contrast = contrast;
        this.masterImage = image;
        this.image = image;
    }

}

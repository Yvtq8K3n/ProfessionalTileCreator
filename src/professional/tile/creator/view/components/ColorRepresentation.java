package professional.tile.creator.view.components;

import javax.swing.*;
import java.awt.*;

public class ColorRepresentation extends AbstractButton {

    public enum State{
        ACTIVE,
        INACTIVE
    }

    public final static int DIMENSION = 25;
    private State state;

    public ColorRepresentation() {
        super();
        this.state = State.INACTIVE;
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        switch (state) {
            case ACTIVE:
                this.state = State.ACTIVE;
                this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                break;
            case INACTIVE:
                this.state = State.INACTIVE;
                this.setBorder(null);
                break;
            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
        System.out.println("changed");
    }
}

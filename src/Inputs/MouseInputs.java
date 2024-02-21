package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    public boolean isDragging = false;
    public int mouseXPlacer = 0, mouseYPlacer = 0;
    public int mouseXUI = 0, mouseYUI = 0;
    public int mouseConstantX = 0, mouseConstantY = 0;
    public boolean buttonPressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonPressed = true;
        mouseXUI = e.getX();
        mouseYUI = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttonPressed = false;
        isDragging = false;
        mouseXPlacer = 0;
        mouseYPlacer = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseXPlacer = e.getX();
        mouseYPlacer = e.getY();
        mouseConstantX = e.getX();
        mouseConstantY = e.getY();
        isDragging = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseConstantX = e.getX();
        mouseConstantY = e.getY();
    }

    public int getMouseXPlacer() {
        return mouseXPlacer;
    }

    public int getMouseYPlacer() {
        return mouseYPlacer;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public int getMouseConstantX() {
        return mouseConstantX;
    }

    public int getMouseConstantY() {
        return mouseConstantY;
    }
}

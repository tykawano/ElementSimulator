package Inputs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    public boolean isDragging = false;
    public int mouseX = 0, mouseY = 0;
    public int mouseXUI = 0, mouseYUI = 0;
    public boolean buttonPressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!buttonPressed){
            buttonPressed = true;
        }
        else {
            buttonPressed = false;
        }
        mouseXUI = e.getX();
        mouseYUI = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
        mouseX = 0;
        mouseY = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        isDragging = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

}

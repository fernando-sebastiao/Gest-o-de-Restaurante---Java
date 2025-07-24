package Calendario;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

public class JSpinnerAno extends JSpinner 
{
  public JSpinnerAno(int anoSeleccionado)
  {
    Integer value = new Integer(anoSeleccionado); 
    Integer min = new Integer(0);
    //Integer max = new Integer(100); 
    Integer step = new Integer(1); 
    SpinnerNumberModel model = new SpinnerNumberModel(value,  null, null, step); 
    this.setModel(model);

    javax.swing.JSpinner.NumberEditor editor = (javax.swing.JSpinner.NumberEditor)getEditor();
    DecimalFormat dcm = editor.getFormat();
    dcm.applyLocalizedPattern("####");

    touch();
  }

  public void touch()
  {
    Object next = getNextValue();
    setValue(next);
    
    Object prev = getPreviousValue();
    setValue(prev);
  }
}
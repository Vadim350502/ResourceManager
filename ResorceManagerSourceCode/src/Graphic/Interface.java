package Graphic;

import Function.ListElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by Dmitriy on 14.11.2015.
 */
public class Interface {
    Display display;
    Shell shell;
    ListElement LE [];
    Composite State, ListLEContent;
    ScrolledComposite ListLE;
    Label StateL [] = new Label[3];


    public Interface()
    {Display display = new Display();
     shell = new Shell(display, SWT.MENU|SWT.BORDER);
     shell.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
     shell.setSize(500, 600);
     Rectangle bds = display.getBounds();
     Point p = shell.getSize();
     int nLeft = (bds.width - p.x) / 2;
     int nTop = (bds.height - p.y) / 2;
     shell.setBounds(nLeft, nTop, p.x, p.y);

     Composite Menu = new Composite(shell,SWT.NONE);
     Menu.setBounds(5,10,485,50);

     State = new Composite(shell,SWT.NONE);
     State.setBounds(5,80,485,100);

     ListLE = new ScrolledComposite(shell,SWT.BORDER|SWT.V_SCROLL);
     ListLE.setBounds(5, 200, 485, 350);
     ListLE.setMinHeight(350);
     ListLEContent = new Composite(ListLE,SWT.NONE);
     ListLEContent.setSize(485,345);
     ListLE.setContent(ListLEContent);

     for(int i=0;i<3;i++)
     StateL[i] = new Label(State,SWT.NONE);


     shell.open();

     while(!shell.isDisposed())
     {if (!display.readAndDispatch())
         display.sleep();
     }
    }

    public void NewScale()
    {

    }

    public void SaveScale()
    {

    }

    public void LoadScale()
    {

    }

    public Composite GetStateArea()
    {

    }

    public Composite GetListArea()
    {

    }

    public static void main(String[] args)
    {Interface graph = new Interface();
    }
}
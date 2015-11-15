package Graphic;

import Function.ListElement;
import Function.ProjectImage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import java.awt.event.FocusListener;

/**
 * Created by Dmitriy on 14.11.2015.
 */
public class Interface {
    Display display;
    Shell shell;

    Composite State, ListLEContent;
    ScrolledComposite ListLE;
    Label StateL [] = new Label[3];
    ProjectImage PI;

    public Interface()
    {PI = new ProjectImage();

     Display display = new Display();
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
     ListLEContent.setSize(485, 345);
     ListLE.setContent(ListLEContent);


     Text StateT1 = new Text(State,SWT.BORDER|SWT.CENTER);
     Text StateT2 = new Text(State,SWT.BORDER);
     for(int i=0;i<2;i++)
     {StateL[i] = new Label(State, SWT.NONE);
      StateL[i].setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     }
     StateT1.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     StateT2.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     StateL[2] = new Label(State, SWT.BORDER);
     StateL[2].setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     StateL[0].setBounds(5, 50, 80, 35);
     StateL[1].setBounds(245, 50, 80, 35);
     StateL[2].setBounds(335, 45, 145, 35);
     StateT1.setBounds(5, 5, 475, 35);
     StateT2.setBounds(85, 45, 145, 35);
     StateL[0].setText("Overall:");
     StateL[1].setText("Balance:");
     StateL[2].setText("0");
     StateT1.setText("<Resource name>");
     StateT1.setTextLimit(30);
     StateT2.setText("<Number>");
     StateT2.setTextLimit(9);
     StateT1.setBackground(State.getBackground());
     StateT2.setBackground(State.getBackground());

     Listener LSfocin = new Listener() {
         @Override
         public void handleEvent(Event e)
         { if (e.type == SWT.FOCUSED)
              {if(StateT2.isFocusControl())StateT2.selectAll();
               if(StateT1.isFocusControl())StateT1.selectAll();
              }
         }
        };

     Listener LStypeChar1 = new Listener() {
        @Override
        public void handleEvent(Event e)
        {PI.ResourceName=StateT1.getText();
            System.out.println(PI.ResourceName);
        }
    };
     Listener LSfocout = new Listener() {
        @Override
        public void handleEvent(Event e)
        {if(StateT2.getCharCount()==0)StateT2.setText(Integer.toString(PI.Overall));
        }
    };


     Listener LStypeChar2 = new Listener() {
        @Override
        public void handleEvent(Event e)
        {char[] veryfy;
          int flg=0;
          int i=0;
          String actual = "";

          if (((e.character >'9')||(e.character<'0'))&&(e.keyCode!=16777220)&&(e.keyCode!=16777219))
          {flg=StateT2.getCharCount();
           veryfy = StateT2.getTextChars();
           for(i=0;i<flg;i++)
           if((veryfy[i]<='9')&&(veryfy[i]>='0'))
           actual+=Character.toString(veryfy[i]);
           StateT2.setText(actual);
          }

          actual="";
        if(StateT2.getCharCount()>0)
            {int zeroflg;
             veryfy = StateT2.getTextChars();
             flg=StateT2.getCharCount();
             for(i=0, zeroflg=0;(i<flg)&&(veryfy[i]<='9')&&(veryfy[i]>='0');i++)
                {if(zeroflg==0 && veryfy[i]!='0') zeroflg=1;
                 if(zeroflg==1) actual+=Character.toString(veryfy[i]);
                }
             if(i==flg && zeroflg==1){System.out.println(actual);PI.Overall=Integer.decode(actual);}
             else PI.Overall=0;
            }
        }
    };



     StateT1.addListener(SWT.FOCUSED, LSfocin);
     StateT1.addListener(SWT.KeyUp, LStypeChar1);
     StateT2.addListener(SWT.KeyUp,LStypeChar2);
     StateT2.addListener(SWT.FOCUSED, LSfocin);
     StateT2.addListener(SWT.FOCUSED, LSfocout);




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


    public void GetListArea()
    {

    }

    public static void main(String[] args)
    {Interface graph = new Interface();
    }
}
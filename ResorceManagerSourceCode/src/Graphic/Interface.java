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
    {
     Label MenuL [] = new Label [10];

     Display display = new Display();
     shell = new Shell(display, SWT.MENU|SWT.BORDER);
     shell.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
     shell.setSize(500, 600);
     shell.setText("Resource Manager");
     shell.setImage(new Image(display, "../Resource Manager/Images/Box_1.png"));
     Rectangle bds = display.getBounds();
     Point p = shell.getSize();
     int nLeft = (bds.width - p.x) / 2;
     int nTop = (bds.height - p.y) / 2;
     shell.setBounds(nLeft, nTop, p.x, p.y);

     Composite Menu = new Composite(shell,SWT.NONE);
     Menu.setBounds(5, 10, 485, 90);

     for(int i=0;i<5;i++)
     {MenuL[i] = new Label(Menu, SWT.ON_TOP);
     }

     MenuL[0].setBounds(25, 5, 50, 50);
     MenuL[1].setBounds(120, 5, 50, 50);
     MenuL[2].setBounds(215, 5, 50, 50);
     MenuL[3].setBounds(310, 5, 50, 50);
     MenuL[4].setBounds(405, 5, 50, 50);

     MenuL[0].setImage(new Image(display, "../Resource Manager/Images/New_1.png"));
     MenuL[1].setImage(new Image(display, "../Resource Manager/Images/Add_1.png"));
     MenuL[2].setImage(new Image(display, "../Resource Manager/Images/Save_1.png"));
     MenuL[3].setImage(new Image(display, "../Resource Manager/Images/Open_1.png"));
     MenuL[4].setImage(new Image(display, "../Resource Manager/Images/Exit_1.png"));

     for(int i=5;i<10;i++)
     {MenuL[i] = new Label(Menu, SWT.CENTER);
      MenuL[i].setFont(new Font(display, "CALIBRI", 12, SWT.BOLD));
     }
     MenuL[5].setBounds(10, 60, 80, 25);
     MenuL[6].setBounds(105, 60, 80, 25);
     MenuL[7].setBounds(200, 60, 80, 25);
     MenuL[8].setBounds(295, 60, 80, 25);
     MenuL[9].setBounds(390, 60, 80, 25);
     MenuL[5].setText("New scale");
     MenuL[6].setText("Add form");
     MenuL[7].setText("Save scale");
     MenuL[8].setText("Load scale");
     MenuL[9].setText("Exit");

     State = new Composite(shell,SWT.NONE);
     State.setBounds(5, 115, 485, 90);

     ListLE = new ScrolledComposite(shell,SWT.BORDER|SWT.V_SCROLL);
     ListLE.setBounds(5, 220, 485, 340);
     ListLE.setMinHeight(340);
     ListLEContent = new Composite(ListLE,SWT.NONE);
     ListLEContent.setSize(485, 335);
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
     StateT2.setText("0");
     StateT2.setTextLimit(9);
     StateT1.setBackground(State.getBackground());
     StateT2.setBackground(State.getBackground());

     PI = new ProjectImage(display,ListLEContent,StateL[2]);

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
        }
    };

     Listener LSfocout = new Listener() {
            @Override
            public void handleEvent(Event e)
            {if(StateT2.getCharCount()==0)StateT2.setText(Integer.toString(PI.Overall));
             PI.UpdateData(false, 0);
            }
        };


         Listener LStypeChar2 = new Listener() {
             @Override
             public void handleEvent(Event e)
             {char[] veryfy;
                 int flg=0;
                 int i=0;
                 String actual = "";

          if (((e.character >'9')||(e.character<'0'))&&(e.keyCode!=16777220)&&(e.keyCode!=16777219)&&(e.keyCode!=8)&&(e.keyCode!=127))
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
             if(i==flg && zeroflg==1)PI.Overall=Integer.decode(actual);
             else PI.Overall=0;
            }
        PI.UpdateData(false,0);
        }
    };

    Listener NewListener = new Listener() {
        @Override
        public void handleEvent(Event e)
        {PI.freeRes();
         PI = new ProjectImage(display,ListLEContent,StateL[2]);
         StateT2.setText("0");
         StateL[2].setText("0");
         ListLEContent.setSize(485, 335);
         StateT1.setText("<Resource name>");
        }
    };

    Listener AddListener = new Listener() {
        @Override
        public void handleEvent(Event e)
        {PI.AddElement(PI);
        }
    };

    Listener ExitListener = new Listener() {
        @Override
        public void handleEvent(Event e)
        {Menu.dispose();
         for(int i=0;i<10;i++)
         MenuL[i].dispose();
         State.dispose();
         ListLE.dispose();
         ListLEContent.dispose();
         StateT1.dispose();
         StateT2.dispose();
         for(int i=0;i<3;i++)
         StateL[i].dispose();

         shell.close();
        }
    };


     MenuL[0].addListener(SWT.MouseDown,NewListener);
     MenuL[1].addListener(SWT.MouseDown,AddListener);
     MenuL[4].addListener(SWT.MouseDown,ExitListener);

     StateT1.addListener(SWT.FOCUSED, LSfocin);
     StateT1.addListener(SWT.KeyUp, LStypeChar1);
     StateT2.addListener(SWT.KeyUp,LStypeChar2);
     StateT2.addListener(SWT.FOCUSED, LSfocin);
     StateT2.addListener(SWT.FocusOut, LSfocout);




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



    public static void main(String[] args)
    {Interface graph = new Interface();
    }
}
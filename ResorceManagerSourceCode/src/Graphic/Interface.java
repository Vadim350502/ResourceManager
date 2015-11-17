package Graphic;

import Function.ProjectImage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.io.IOException;

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
    Text StateT1;
    Text StateT2;

    public Interface()
    {
     Label MenuL [] = new Label [10];

     display = new Display();
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
     ListLEContent.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
     ListLE.setContent(ListLEContent);


     StateT1 = new Text(State,SWT.BORDER|SWT.CENTER);
     StateT2 = new Text(State,SWT.BORDER);
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
              {if(StateT2.isFocusControl()) StateT2.selectAll();
               if(StateT1.isFocusControl()) StateT1.selectAll();
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

    Listener SaveListener = new Listener() {
        @Override
        public void handleEvent(Event e)
        {
            try {
                SaveScale();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    };

    Listener LoadListener = new Listener() {
        @Override
        public void handleEvent(Event e)
        {
            try {
                LoadScale();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
     MenuL[2].addListener(SWT.MouseDown,SaveListener);
     MenuL[3].addListener(SWT.MouseDown,LoadListener);
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
    public void MessageWindow(String message,Shell Disable)
    {Shell messagew = new Shell(display, SWT.MENU|SWT.BORDER);
     messagew.setSize(300, 150);
     Rectangle bds = display.getBounds();
     Point p = messagew.getSize();
     int nLeft = (bds.width - p.x) / 2;
     int nTop = (bds.height - p.y) / 2;
     messagew.setBounds(nLeft, nTop, p.x, p.y);
     messagew.setText("Error");
     messagew.setImage(new Image(display, "../Resource Manager/Images/Box_1.png"));


     Label lbmsg = new Label(messagew,SWT.CENTER);
     lbmsg.setText(message);
     lbmsg.setBounds(15, 20, 260, 30);
     lbmsg.setFont(new Font(display, "TIMES NEW ROMAN", 14, SWT.BOLD));

     Button btnok = new Button(messagew,SWT.PUSH);
     btnok.setText("OK");
     btnok.setBounds(70, 70, 150, 40);

     Listener ListenerOk = new Listener()
        {
            public void handleEvent(Event e){
                if (e.type == SWT.MouseDown)
                {messagew.dispose();
                }
            }};

     btnok.addListener(SWT.MouseDown, ListenerOk);

     messagew.open();
     Disable.setEnabled(false);
     while(!messagew.isDisposed())
        {if (!display.readAndDispatch())
            display.sleep();
        }

     Disable.setEnabled(true);
    }

    public boolean QuestionWindow(String question,Shell Disable)
    {Shell QW = new Shell(display, SWT.MENU|SWT.BORDER);
        QW.setSize(300, 150);
        Rectangle bds = display.getBounds();
        Point p = QW.getSize();
        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;
        QW.setBounds(nLeft, nTop, p.x, p.y);
        QW.setText("Error");
        QW.setImage(new Image(display, "../Resource Manager/Images/Box_1.png"));

        boolean state[]= new boolean[1];

        Label lbmsg = new Label(QW,SWT.CENTER);
        lbmsg.setText(question);
        lbmsg.setBounds(15, 20, 260, 30);
        lbmsg.setFont(new Font(display, "TIMES NEW ROMAN", 14, SWT.BOLD));

        Button BtnNo = new Button(QW,SWT.PUSH);
        BtnNo.setText("No");
        BtnNo.setBounds(165, 70, 120, 40);

        Button BtnYes = new Button(QW,SWT.PUSH);
        BtnYes.setText("Yes");
        BtnYes.setBounds(10, 70, 120, 40);

        Listener ListenerYes = new Listener()
        {
            public void handleEvent(Event e){
                if (e.type == SWT.MouseDown)
                {state[0]=true;
                 QW.close();
                }
            }};

        Listener ListenerNo = new Listener()
        {
            public void handleEvent(Event e){
                if (e.type == SWT.MouseDown)
                {state[0]=false;
                 QW.close();
                }
            }};

        BtnYes.addListener(SWT.MouseDown, ListenerYes);
        BtnNo.addListener(SWT.MouseDown, ListenerNo);

        QW.open();

        Disable.setEnabled(false);

        while(!QW.isDisposed())
        {if (!display.readAndDispatch())
            display.sleep();
        }

        Disable.setEnabled(true);
        return(state[0]);
    }


    public void SaveScale() throws IOException {
     Shell Savewindow = new Shell(display, SWT.MENU|SWT.BORDER);
     Savewindow.setSize(500, 600);
     Savewindow.setLocation(shell.getLocation());
     Savewindow.setText("Save file");
     Savewindow.setImage(new Image(display, "../Resource Manager/Images/Box_1.png"));

     String[]files;
     int j,val[];
     boolean state []= new boolean[3];

     val=new int[1];
     state[2]=false;

     List FileList = new List(Savewindow,SWT.BORDER|SWT.MULTI|SWT.V_SCROLL);
     FileList.setBounds(10, 10, 475, 440);
     FileList.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));

     File DataFolder = new File("../Resource Manager/RMCache/");
     files=DataFolder.list();
     for(j=0;j<files.length;j++)
     FileList.add(files[j].substring(0,files[j].length()-4));
     val[0]=files.length;


     Text Namefield = new Text(Savewindow,SWT.BORDER);
     Namefield.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     Namefield.setBounds(10, 465, 475, 35);
     Namefield.setText("NewFile");
     Namefield.setTextLimit(50);

     Button BtnSave = new Button(Savewindow,SWT.PUSH);
     BtnSave.setText("Save");
     BtnSave.setBounds(175, 515, 145, 40);

     Button BtnCancel = new Button(Savewindow,SWT.PUSH);
     BtnCancel.setText("Cancel");
     BtnCancel.setBounds(340, 515, 145, 40);

     Button BtnDelete = new Button(Savewindow,SWT.PUSH);
     BtnDelete.setText("Delete");
     BtnDelete.setBounds(10, 515, 145, 40);

     if(FileList.getItemCount() != 0) FileList.select(0);

     Listener ListSelect = new Listener() {
         @Override
         public void handleEvent(Event event) {
             if(FileList.getItemCount() != 0)
                 Namefield.setText(FileList.getItem(FileList.getSelectionIndex()));
         }
     };

     Listener FieldFocus = new Listener() {
            @Override
            public void handleEvent(Event event) {
             Namefield.selectAll();
            }
     };


     Listener Cancel = new Listener() {
            @Override
            public void handleEvent(Event event) {
             Savewindow.close();
            }
     };

     Listener Save = new Listener() {
            @Override
            public void handleEvent(Event event) {
            state[1]=false;
            state[0]=false;
            if(Namefield.getText().length()<1) MessageWindow("Enter file name!!!",Savewindow);
            else{
            for(int i=0;i<val[0]&&!state[1];i++)
                if(FileList.getItem(i).compareTo(Namefield.getText())==0) state[1]=true;
                if(state[1])
                   if(QuestionWindow("Overwrite this file?", Savewindow)){
                       try {
                           state[0]=PI.SaveData(Namefield.getText(), true);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                   else state[0]=true;
                if(!state[1]) {
                       try {
                           state[0]=PI.SaveData(Namefield.getText(), false);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
            if(!state[0])MessageWindow("Failed to save!",Savewindow);
            state[2]=true;
            }
            }
     };

     Listener Delete = new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (FileList.getItemCount() != 0) {
                    state[0] = QuestionWindow("Delete this file?", Savewindow);
                    if (state[0]) {
                        File del = new File("../Resource Manager/RMCache/" + FileList.getItem(FileList.getSelectionIndex()) + ".rmc");
                        del.delete();
                        state[2] = true;
                    }
                }
            }
     };


     FileList.addListener(SWT.MouseDown,ListSelect);
     Namefield.addListener(SWT.MouseDown,FieldFocus);
     BtnCancel.addListener(SWT.MouseDown,Cancel);
     BtnSave.addListener(SWT.MouseDown,Save);
     BtnDelete.addListener(SWT.MouseDown, Delete);


     shell.setEnabled(false);
     Savewindow.open();

     while(!Savewindow.isDisposed())
        {if (!display.readAndDispatch())
            display.sleep();
         if(state[2])
          {files=DataFolder.list();
           FileList.removeAll();
           for(j=0;j<files.length;j++)
           FileList.add(files[j].substring(0,files[j].length()-4));
           val[0]=files.length;
           state[2]=false;
           if(FileList.getItemCount()==0) Namefield.setText("New File");
           if(FileList.getItemCount() != 0) FileList.select(0);
          }
        }
     shell.setEnabled(true);

    }

    public void LoadScale() throws IOException {
        Shell Loadwindow = new Shell(display, SWT.MENU|SWT.BORDER);
        Loadwindow.setSize(500, 600);
        Loadwindow.setLocation(shell.getLocation());
        Loadwindow.setText("Save file");
        Loadwindow.setImage(new Image(display, "../Resource Manager/Images/Box_1.png"));


        String[]files;
        int j,val[];
        boolean state []= new boolean[3];

        val=new int[1];
        state[2]=false;

        List FileList = new List(Loadwindow,SWT.BORDER|SWT.MULTI|SWT.V_SCROLL);
        FileList.setBounds(10, 10, 475, 440);
        FileList.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));

        File DataFolder = new File("../Resource Manager/RMCache/");
        files=DataFolder.list();
        for(j=0;j<files.length;j++)
        FileList.add(files[j].substring(0,files[j].length()-4));
        val[0]=files.length;



        Label Namefield = new Label(Loadwindow, SWT.BORDER);
        Namefield.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
        Namefield.setBounds(10, 465, 475, 35);
        if(FileList.getItemCount() != 0)
           {FileList.select(0);
            Namefield.setText(FileList.getItem(0));
           }
        else Namefield.setText("No saved files");

        Button BtnLoad = new Button(Loadwindow,SWT.PUSH);
        BtnLoad.setText("Load");
        BtnLoad.setBounds(175, 515, 145, 40);

        Button BtnCancel = new Button(Loadwindow,SWT.PUSH);
        BtnCancel.setText("Cancel");
        BtnCancel.setBounds(340, 515, 145, 40);

        Button BtnDelete = new Button(Loadwindow,SWT.PUSH);
        BtnDelete.setText("Delete");
        BtnDelete.setBounds(10, 515, 145, 40);


        Listener ListSelect = new Listener() {
            @Override
            public void handleEvent(Event event) {
                if(FileList.getItemCount() != 0)
                Namefield.setText(FileList.getItem(FileList.getSelectionIndex()));
                else Namefield.setText("No saved files");
            }
        };


        Listener Cancel = new Listener() {
            @Override
            public void handleEvent(Event event) {
                Loadwindow.close();
            }
        };

        Listener Delete = new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (FileList.getItemCount() != 0) {
                    state[0] = QuestionWindow("Delete this file?", Loadwindow);
                    if (state[0]) {
                        File del = new File("../Resource Manager/RMCache/" + FileList.getItem(FileList.getSelectionIndex()) + ".rmc");
                        del.delete();
                        state[2] = true;
                    }
                }
            }
        };

        Listener Load = new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (FileList.getItemCount() != 0)
                {state[0]=QuestionWindow("Close current project?",Loadwindow);
                 if(state[0])
                     try {
                         if(PI.LoadData(FileList.getItem(FileList.getSelectionIndex()),PI))
                          {StateT2.setText(Integer.toString(PI.Overall));
                           StateT1.setText(PI.ResourceName);
                           Loadwindow.close();
                           }
                         else MessageWindow("File have damage!",Loadwindow);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                }
            }
        };


//
        FileList.addListener(SWT.MouseDown,ListSelect);
        BtnCancel.addListener(SWT.MouseDown,Cancel);
        BtnLoad.addListener(SWT.MouseDown,Load);
        BtnDelete.addListener(SWT.MouseDown, Delete);


        shell.setEnabled(false);
        Loadwindow.open();

        while(!Loadwindow.isDisposed())
        {if (!display.readAndDispatch())
            display.sleep();
            if(state[2])
            {files=DataFolder.list();
                FileList.removeAll();
                for(j=0;j<files.length;j++)
                    FileList.add(files[j].substring(0,files[j].length()-4));
                val[0]=files.length;
                state[2]=false;
                if(FileList.getItemCount()==0) Namefield.setText("New File");
                if(FileList.getItemCount() != 0) FileList.select(0);
            }
        }
        shell.setEnabled(true);

    }

    public static void main(String[] args)
    {Interface graph = new Interface();
    }
}
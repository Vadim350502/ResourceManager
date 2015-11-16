package Function;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
/**
 * Created by Dmitriy on 14.11.2015.
 */
public class ListElement {
    public  boolean isExist;
    public  boolean isVisual;
    String description;
    int index;
    int value;

    Composite Block;
    Text Desc;
    Text InpVal;
    Label Info [];

    public ListElement(int pval,int pnum, String pdes)
    {
     index = pnum;
     value=pval;
     description=pdes;
     isExist = true;
    }

    public int getvalue()
    {return(value);
    }

    public void setvalue(int pval)
    {value=pval;
     if(isVisual)InpVal.setText(Integer.toString(value));
    }

    public int getindex()
    {return(index);
    }
    public void setindex(int pnum)
    {index =pnum;
     if(isVisual)InpVal.setText(Integer.toString(index));
    }

    public String getdescription()
    {return(description);
    }

    public void setdescription(String pdes)
    {description=pdes;
     if(isVisual)Desc.setText(description);
    }

    public void visualize(Composite base,int line,ProjectImage obj,Display display)
    {Block = new Composite(base, SWT.BORDER);
     Block.setBounds(5, line, 470, 105);

     Desc = new Text(Block,SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
     InpVal = new Text(Block,SWT.BORDER);
     Desc.setFont(new Font(display, "TIMES NEW ROMAN", 14, SWT.NORMAL));
     Desc.setBounds(65, 5, 205, 90);
     InpVal.setBounds(280, 55, 115, 40);
     InpVal.setFont(new Font(display, "TIMES NEW ROMAN", 16, SWT.BOLD));
     Desc.setTextLimit(100);
     InpVal.setTextLimit(9);
     InpVal.setText("0");

     Info = new Label [3];
     for(int z=0;z<3;z+=1)
        Info[z]= new Label(Block,SWT.NONE);
     Info[0].setBounds(10, 30, 50, 50);
     Info[0].setFont(new Font(display, "TIMES NEW ROMAN", 20, SWT.BOLD));
     Info[0].setText(Integer.toString(index));
     Info[1].setBounds(280, 5, 115, 40);
     Info[1].setFont(new Font(display, "TIMES NEW ROMAN", 20, SWT.BOLD));
     Info[1].setText("Using:");
     Info[2].setBounds(405, 25, 50, 50);
     Info[2].setImage(new Image(display, "../Resource Manager/Images/Delete_3.png"));

     Listener DeleteL = new Listener()
     {
         @Override
         public void handleEvent(Event event) {
             if(event.type==SWT.MouseDown)
             {freeres();
              obj.UpdateData();
             }
         }
     };

    Info[2].addListener(SWT.MouseDown, new Listener() {
        @Override
        public void handleEvent(Event event) {
            if (event.type == SWT.MouseDown) {
                freeres();
                obj.UpdateData();
            }
        }
    });


    InpVal.addListener(SWT.KeyUp, new Listener() {
        @Override
        public void handleEvent(Event e) {
            char[] veryfy;
            int flg = 0;
            int i = 0;
            String actual = "";

            if (((e.character > '9') || (e.character < '0')) && (e.keyCode != 16777220) && (e.keyCode != 16777219)) {
                flg = InpVal.getCharCount();
                veryfy = InpVal.getTextChars();
                for (i = 0; i < flg; i++)
                    if ((veryfy[i] <= '9') && (veryfy[i] >= '0'))
                        actual += Character.toString(veryfy[i]);
                InpVal.setText(actual);
            }

            actual = "";
            if (InpVal.getCharCount() > 0) {
                int zeroflg;
                veryfy = InpVal.getTextChars();
                flg = InpVal.getCharCount();
                for (i = 0, zeroflg = 0; (i < flg) && (veryfy[i] <= '9') && (veryfy[i] >= '0'); i++) {
                    if (zeroflg == 0 && veryfy[i] != '0') zeroflg = 1;
                    if (zeroflg == 1) actual += Character.toString(veryfy[i]);
                }
                if (i == flg && zeroflg == 1) value = Integer.decode(actual);
                else value = 0;
            }
        }
    });

    InpVal.addListener(SWT.FocusOut, new Listener() {
        @Override
        public void handleEvent(Event e) {
            if (InpVal.getCharCount() == 0) InpVal.setText(Integer.toString(value));
        }
    });

    Desc.addListener(SWT.FocusOut, new Listener() {
        @Override
        public void handleEvent(Event e) {
            if (Desc.getCharCount() != 0) description=Desc.getText();
        }
    });
        

    isVisual=true;
    }
    public void freeres()
    {Block.dispose();
     Desc.dispose();
     InpVal.dispose();
     for(int z=0;z<3;z+=1)
        Info[z].dispose();
    }
}

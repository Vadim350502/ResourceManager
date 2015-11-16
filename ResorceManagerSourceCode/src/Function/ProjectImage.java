package Function;

import org.eclipse.swt.widgets.*;

/**
 * Created by Dmitriy on 15.11.2015.
 */
public class ProjectImage {
    ListElement List[];
    boolean elementState[] = new boolean [500];
    public String ResourceName;
    public int Overall;
    public int Balance;
    public int IndexOfList;
    public Display display;
    public Composite base;
    Label BalOut;

    public ProjectImage(Display d, Composite c, Label BalancePrint) {
    ResourceName="<Resource name>";
    Overall=0;
    List = new ListElement[500];
    Balance=0;
    IndexOfList=0;
    display=d;
    base=c;
    BalOut=BalancePrint;
    for(int i=0;i<500;i++)
      {List[i]=null;
       elementState[i]=false;
      }
    }

    public void SaveData(String name){

    }

    public void LoadData(String name){

    }

    public boolean AddElement(ProjectImage PIobj){
    if(IndexOfList<500)
        {int i;
         for(i=0;(i<500)&&(elementState[i]);i++);
         List[i]= new ListElement(0,i,"<Insert description here>");
         elementState[i]=true;
         List[i].obj=PIobj;
         IndexOfList++;
         if(20+IndexOfList*110 >335)base.setSize(485, 20+IndexOfList*110);
         for(i=IndexOfList-1;(i<500)&&(elementState[i]);i++)List[i].visualize(base, 5 + i * 110, display);
         return(true);
        }
     else return(false);
    }


    public void DeleteElement(int index){
    elementState[index]=false;
    List[index]=null;
    int i,k,s,flg=0;
    for(i=0;i<500;i++)
    {if(!elementState[i])
        {flg=i;
         for(k=i,s=1;(k+s)<500;k++)
           {while(((k+s)<500)&&(!elementState[k+s]))s++;
            if((k+s)==500) i=500;
            else
            {List[k]=List[k+s];
             List[k+s]=null;
             List[k].setindex(k);
             elementState[k]=true;
             elementState[k+s]=false;
            }
           }
        }

    }
    IndexOfList--;
    if(flg!=499)
    UpdateData(true,flg);
    UpdateData(false,0);
    }


    public void UpdateData(boolean full,int begin){
    Balance=0;
    int i;
    if(full)
       {for(i=begin;(i<500)&&(elementState[i]);i++)
           {if(List[i].isVisual)List[i].freeres();
            Balance+=List[i].getvalue();
            if(20+IndexOfList*110 >335)base.setSize(485, 20+IndexOfList*110);
            else base.setSize(485, 335);
            List[i].visualize(base, 5 + i * 110, display);
           }
       }
    else  for(i=0;(i<500)&&(elementState[i]);i++) Balance+=List[i].getvalue();
    BalOut.setText(Integer.toString(Overall-Balance));
    }
    public void freeRes()
    {for(int i=0;(i<500)&&(elementState[i]);i++)
         if(List[i].isVisual)List[i].freeres();

    }

}

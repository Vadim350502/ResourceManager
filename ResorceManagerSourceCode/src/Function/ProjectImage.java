package Function;

import org.eclipse.swt.widgets.*;

import java.io.*;

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

    public boolean SaveData(String name,boolean owerwrite) throws IOException {
        File saving = new File("../Resource Manager/RMCache/" + name + ".rmc");
        if (saving.exists())
            {if (!owerwrite) return (false);
             if (owerwrite) {
                 saving.delete();
                 try {
                 saving.createNewFile();
                 } catch (IOException e) {
                   e.printStackTrace();
                 }
             }
            }
        else {
            try {
                saving.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile writable;
        writable = new RandomAccessFile(saving,"rw");
        writable.writeBytes("<#ResName>\n");
        writable.writeBytes(ResourceName + '\n');
        writable.writeBytes("<#Param>\n");
        writable.writeBytes(Integer.toString(Overall)+'\n');
        writable.writeBytes("<#ElementsNum>\n");
        writable.writeBytes(Integer.toString(IndexOfList)+'\n');
        if(IndexOfList>0)
          {writable.writeBytes("<#ListContent>\n");
           writable.writeBytes("///////////////////////////////////////////////////////////\n\n");
           for(int i=0;(i<500)&&elementState[i];i++)
             {writable.writeBytes("<#Element>\n");
              writable.writeBytes("<#Index>\n");
              writable.writeBytes(Integer.toString(List[i].getindex())+'\n');
              writable.writeBytes("<#Value>\n");
              writable.writeBytes(Integer.toString(List[i].getvalue())+'\n');
              writable.writeBytes("<#Description>\n");
              writable.writeBytes(List[i].description+'\n');
              writable.writeBytes("<#End>\n");
              writable.writeBytes("///////////////////////////////////////////////////////////\n\n");
             }
          }
        writable.writeBytes("<#END NOTATION>\n");
        writable.writeBytes("<#FILE END>");
        writable.close();
        return(true);
    }

    public boolean LoadData(String name,ProjectImage object) throws IOException {
     File loading = new File("../Resource Manager/RMCache/" + name +".rmc");
     boolean flg=true;
     int cs=0,ec=0,tval[],tind=0,tparam=0,telem=0;
     String tstr[],tstr2="RES";

     tstr=new String[500];
     tval=new int [500];
     for(cs=0;cs<500;cs++)
     tval[cs]=0;
     cs=0;
     RandomAccessFile readable;
     readable = new RandomAccessFile(loading,"rw");
     readable.seek(0);
     name=readable.readLine();
     while((readable.getFilePointer()<readable.length())&&(name.compareTo("<#END NOTATION>")!=0)&&flg)
     { if(name.compareTo("<#ResName>")==0)tstr2=readable.readLine();
       if(name.compareTo("<#Param>")==0)tparam= Integer.decode(readable.readLine());
       if(name.compareTo("<#ElementsNum>")==0)
            {telem=Integer.decode(readable.readLine());
             if(telem==0) flg =false;
            }
       if(name.compareTo("<#Element>")==0)
          {ec++;
           cs=0;
           if(readable.readLine().compareTo("<#Index>") == 0)
              {cs++;
               tind=Integer.decode(readable.readLine());
              }
           else{readable.close(); return(false);}
           if(readable.readLine().compareTo("<#Value>") == 0)
              {cs++;
               tval[tind]=Integer.decode(readable.readLine());
              }
           else {readable.close(); return(false);}
           if(readable.readLine().compareTo("<#Description>") == 0)
              {cs++;
               tstr[tind]=readable.readLine();
              }
           else {readable.close(); return(false);}
           if(readable.readLine().compareTo("<#End>") == 0)
    {cs++;
        if(cs!=4){readable.close(); return(false);}
    }
    else{readable.close(); return(false);}
}
name=readable.readLine();
        }

        if((readable.getFilePointer()>=readable.length())||(ec!=telem))  {readable.close(); return(false);}
        else
        {freeRes();
         ResourceName=tstr2;
         Overall=tparam;
         IndexOfList=telem;
         for(int i=0;i<500;i++)
            {List[i]=null;
             elementState[i]=false;
             if(i<IndexOfList)
             {List[i]= new ListElement(tval[i],i,tstr[i]);
              List[i].obj=object;
              elementState[i]=true;
             }
            }
         UpdateData(true, 0);
         readable.close();
         return(true);
        }
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
            List[i].visualize(base, 5 + i * 110, display);
           }
        if(20+IndexOfList*110 >335)base.setSize(485, 20+IndexOfList*110);
        else base.setSize(485, 335);
       }
    else  for(i=0;(i<500)&&(elementState[i]);i++) Balance+=List[i].getvalue();
    BalOut.setText(Integer.toString(Overall-Balance));
    }
    public void freeRes()
    {for(int i=0;(i<500)&&(elementState[i]);i++)
         if(List[i].isVisual)List[i].freeres();

    }

}

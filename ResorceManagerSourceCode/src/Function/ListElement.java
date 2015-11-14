package Function;

/**
 * Created by Dmitriy on 14.11.2015.
 */
public class ListElement {
    String description;
    int number;
    int value;

    public ListElement(int pval,int pnum, String pdes)
    {number= pnum;
     value=pval;
     description=pdes;
    }

    public int getvalue()
    {return(value);
    }

    public void setvalue(int pval)
    {value=pval;
    }

    public int getnumber()
    {return(number);
    }
    public void setnumber(int pnum)
    {number=pnum;
    }

    public String getdescription()
    {return(description);
    }

    public void setdescription(String pdes)
    {description=pdes;
    }
}

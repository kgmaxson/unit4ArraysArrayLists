public class ArrayOps2
{
    public int[] values;
    public ArrayOps2(int[] initialValues)
    { 
        values = initialValues;
    }
    public String toString()
    {
        String str = "[";
        
        for (int i=0; i<values.length; i++)
        {
            if (i>0)
            {
                str += ", ";
            }
            str += values[i];
        }
        
        str += " ]";
        
        return str;
    }
    public void swapFirstAndLast()
    {
        int temp = values[0];
        values[0]=values[values.length-1];
        values[values.length-1]=temp;
    }
    public void shiftRight()
    {
        int newFirst = values[values.length-1];
        for (int i=values.length-1;i>0;i--)
        {
                values[i]=values[i-1];
            
        }
        values[0]=newFirst;
    }
    public void evenMakeZero()
    {
        for (int i=0; i<values.length;i+=2)
        {
            values[i]=0;
        }
    }
    public void makeLargerNeighbors()
    {
        int[] valuesCopy = new int[values.length];
        for (int i=0;i<values.length;i++)
        {
            valuesCopy[i]=values[i];
        }
        for (int i=1;i<values.length-1;i++)
        {
            if (valuesCopy[i-1]<valuesCopy[1+1])
            {
                values[i]=valuesCopy[i+1];
            }
            else
            {
                values[i]=valuesCopy[i-1];
            }
        }
    }
    public void removeMiddle()
    {
        int[] valuesCopyOdd = new int[values.length-1];
        int[] valuesCopyEven = new int[values.length-2];
        if (values.length%2==0)
        {
            for (int i=0;i<values.length;i++)
        {
            if (i<values.length/2-1){
                valuesCopyEven[i]=values[i];
            }
            else if (i==values.length/2||i==values.length/2+1)
            {
                
            }
            else
            {
                valuesCopyEven[i-2]=values[i];
            }
        }
        values = valuesCopyOdd;
        }
        else if (values.length%2==1)
        {
            for (int i=0;i<values.length;i++)
        {
            if (i<values.length/2){
                valuesCopyEven[i]=values[i];
            }
            else if (i==values.length/2)
            {
                
            }
            else
            {
                valuesCopyEven[i-1]=values[i];
            }
        }
        values = valuesCopyEven;
        }
    }
    public void evenToFront()
    {
        values = values;
    }
    public int secondLargest()
    {
        int largest = -99999999;
        int largest2 = -99999998;
        for (int i = 0; i<values.length;i++)
        {
            if (values[i]>largest)
            {
                largest2 = largest;
                largest = values[i];
            }
            else if (values[i]>largest2)
            {
                largest2=values[i];
            }
        }
        return largest2;
    }
    public String increasingOrder()
    {
        String increasing = "False";
        int previous = values[0];
        for (int i = 1; i<values.length;i++)
        {
            if (values[i]>previous)
            {
                previous = values[i];
                increasing = "True";
            }
            else
            {
                increasing = "False";
                break;
            }
        }
        return increasing;
    }
    public String adjascentDuplicates()
    {
        String duplicate = "False";
        int previous = values[0];
        for (int i = 1; i<values.length;i++)
        {
            if (values[i]!=previous)
            {
                duplicate = "False";
                previous = values[i];
            }
            else
            {
                duplicate = "True";
                break;
            }
        }
        return duplicate;
    }
}
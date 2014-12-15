import java.util.ArrayList;

public class ArrayListRunner
{
  public ArrayListRunner(ArrayList<String> list)
  {
      
  }
    
  public static void main(String[] args)
  {
       ArrayList<String> names = new ArrayList<String>(); 
       System.out.println(names);
       
       names.add("Alice");
       names.add("Bob");
       names.add("Connie");
       names.add("David");
       names.add("Edward");
       names.add("Fran");
       names.add("Gomez");
       names.add("Harry");
       
       System.out.println(names);
              
       int length = names.size();
       String getFirst = names.get(0);       
       String getLast = names.get(length-1);
       System.out.println("first name in the list: "+getFirst);
       System.out.println("last name in the list: "+getLast);
       
       System.out.println("size of the array: "+length);
       
       names.set(0, "Alice B. Tokas");
       
       System.out.println("change first name" + names);
       
       names.add(4, "Doug");
       
       System.out.println("add name"+ names);
       
       for(String elements: names )
       {
        System.out.println("Names: " + elements);
        }
        
       ArrayList<String> names2 = new ArrayList<String>(names);
       
       System.out.println("names2: "+names2);
       
       String removeFirstnames = names.remove(0);
       
       System.out.println("names: "+names);
  }
}












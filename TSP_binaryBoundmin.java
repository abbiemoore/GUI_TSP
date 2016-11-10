import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JFileChooser;

public class TSP_binaryBoundmin
{
int[][] costMatrix;
int min=100000;
int[] bestPath;
int count;
   public TSP_binaryBoundmin()
   {
      try
      {
         JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file to look in
         int returnVal = myChooser.showOpenDialog(null); 
         File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
         Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file         
         int n=in.nextInt(); //create a while loop so that scanner reads entire txt file
         int count=0;
         bestPath = new int[n-1];
         costMatrix = new int[n][n];
         for(int row=0; row<n;row++)
         {
            for(int col=0; col<n;col++)
            {
               costMatrix[row][col]=in.nextInt();
               //System.out.println(Integer.toString(costMatrix[row][col]));
            }
         }
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File Not Found");
        
      }
   }
   
   public static int[] getArray()
   {
      try
      {
   
         JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file to look in
         int returnVal = myChooser.showOpenDialog(null); 
         File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
         Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file
         int n=in.nextInt(); //create a while loop so that scanner reads entire txt file
         int[] inputArray = new int[n-1];
         int j=1;
         for (int i=0; i<inputArray.length; i++)
         {
            inputArray[i]=j;
            j++;
         }
         return inputArray;
         
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File Not Found");
         return null;
      }
   }
   public void perm(int [] input, int p)
   {
      
      if (p==input.length-1)//if p is at the end of the word
      {
         count++;
         System.out.print(Integer.toString(count));
         if (count==1)
         {
            int cost = costEvaluate(input);
            if(cost<min)
            {
               min=cost;
               for (int i =0; i<input.length; i++)
               {
                  bestPath[i]=input[i];
               }
            }
         }
         else
         {
            int cost = upToDateSum(input);
            if(cost<min)
            {
               min=cost;
               for (int i =0; i<input.length; i++)
               {
                  bestPath[i]=input[i];
               }
            }

         }   
      }
      else 
      {
         for(int i = p; i<input.length; i++) 
         {
         
            swap(input, i, p);
            perm(input, p+1); //call the perm method again and raise p increase indicies 
            swap(input, i, p);
         }
      }
   }
   public int[] getPath()
   {
      System.out.println("BEST " + Arrays.toString(bestPath));
      return bestPath;
   }
   
   public int getMin()
   {
      return min;
   }
   
   public int costEvaluate(int[] cities)
   {
   int sum=0;
   int cost1 = costMatrix[0][cities[0]];
   sum=sum+cost1;
   int cost2 = costMatrix[cities[cities.length-1]][0];
   sum=sum+cost2;
   
      for(int i=0; i<cities.length-1; i++)
      { 
         int cost = costMatrix[cities[i]][cities[i+1]];
         sum= sum+cost;
      }         
      
      return sum;
         
   }
   
   public int upToDateSum(int[] cities)
   {
   int upToDateSum=0;
   int cost1 = costMatrix[0][cities[0]];
   upToDateSum=upToDateSum+cost1;
   if(upToDateSum>min)
   {
      return;
   }
   int cost2 = costMatrix[cities[cities.length-1]][0];
   upToDateSum=upToDateSum+cost2;
   if(upToDateSum>min)
   {
      return;
   }
   
   for(int i=0; i<cities.length-1; i++)
   { 
      int cost = costMatrix[cities[i]][cities[i+1]];
      upToDateSum= upToDateSum+cost;
      if (sum>min)
      {
         return;
      }
   }
   return min;         

   }
   
   
      
   public void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i]=a[j];
      a[j]=temp;
   }
 
}

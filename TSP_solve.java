import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JFileChooser;

public class TSP_solve
{
int[][] costMatrix;
double min = Double.POSITIVE_INFINITY;
int[] bestPath;
int[] newArray;
int count;


   public TSP_solve()
   {
      try
      {
         JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file to look in
         int returnVal = myChooser.showOpenDialog(null); 
         File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
         Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file         
         int n=in.nextInt(); //scans the first number in the file which is the size of the matrix
         bestPath = new int[n-1]; //initializes the bestPath array
         costMatrix = new int[n][n]; //creates a cost matrix size n (makes it generic enough so the size changes dependant on the file choosen)
         int count=0; //instatiate counter
         for(int row=0; row<n;row++) //double for loop adds the data from the file and stores it in the costMatrix
         {
            for(int col=0; col<n;col++)
            {
               costMatrix[row][col]=in.nextInt();
            }
         }
         newArray= new int[n-1];
         int j=1;
         for (int i=0; i<newArray.length; i++)
         {
            newArray[i]=j;
            j++;
         }

         
      }
      catch(FileNotFoundException e) //catches if the file isn't found
      {
         System.out.println("File Not Found");
        
      }
   } //end of constructor
   
   

      /*try
      {
   
         JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file with the matrix
         int returnVal = myChooser.showOpenDialog(null); 
         File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
         Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file
         int n=in.nextInt(); //scans the first number in the file which is the size of the matrix
         int[] inputArray = new int[n-1]; //creates a new array that is the size of the matrix -1
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
      }*/
   

   
   public void perm(int [] input, int p)
   {
   
      
      if (p==input.length-1)//if p is at the end of the word
      {
         count++;
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
         for(int i = p; i<input.length; i++) 
         {
         
            swap(input, i, p);
            perm(input, p+1); //call the perm method again and raise p increase indicies 
            swap(input, i, p);
         }
      }
      
   }
   
   public int[] getPath() //return the best path in array form minus the 0 (start and end)
   {
      return bestPath;
   }
   
   public double getMin() //returns the min cost of the best path
   {
      return min;
   }
   
   public int costEvaluate(int[] cities) //evaluates the cost of a path around the cities using the matrix
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
      
   public void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i]=a[j];
      a[j]=temp;
   }
   
   public int[] getMyArray() //this method generates an array with the numbers from 1 to n-1
   {
      return newArray;
   }

   
}


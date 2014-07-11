/*
    Right answers for jobs.txt: 69119377652 and 67311454237
                  for test.txt: 145924 and 138232

*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GreedySchedule {
    
    private static class Task {
        public int w;
        public int l;
        
        public Task(int we, int le) {
            w = we;
            l = le;
        }
    }

    private static class TasksDiffComparator implements Comparator<Task>
    {
        public int compare(Task x, Task y)
        {
            if ( (x.w - x.l) < (y.w - y.l) ) {
                return 1;
            }
            else if ( (x.w - x.l) > (y.w - y.l) ) {
                return -1;
            }
            else { // (x.w - x.l) == (y.w - y.l)
                if (x.w <= y.w) {
                    return 1;
                }
                else 
                    return -1;
            }
        }   
    }
   
    private static class TasksDivideComparator implements Comparator<Task>
    {
        public int compare(Task x, Task y)
        {
            if ( ((double)x.w /(double) x.l) < ((double)y.w / (double)y.l) ) {
                return 1;
            }
            else if ( (double)(x.w / (double)x.l) > ((double)y.w / (double)y.l) ) {
                return -1;
            }
            else { // (x.w / x.l) == (y.w / y.l)
                return 1;
            }
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {   
        Comparator<Task> diffComparator = new TasksDiffComparator();
        Comparator<Task> divideComparator = new TasksDivideComparator();
        PriorityQueue<Task> queueDif = new PriorityQueue<>(10000, diffComparator);
        PriorityQueue<Task> queueDiv = new PriorityQueue<>(10000, divideComparator);
        Scanner in = new Scanner(new File("jobs.txt")); //System.in "test.txt"
        int N = in.nextInt();
        int inW;
        int inL;
        Task t;
        for (int i = 0; i < N; i++) {
            inW = in.nextInt();
            inL = in.nextInt();
            t = new Task (inW, inL);
            queueDif.add(t);
            queueDiv.add(t);
        }
        
        long SumDif = 0;
        long CurrentC = 0;
        long WeightedC = 0;
        Task currTask;
        
        while (queueDif.size() != 0) {
            currTask = queueDif.remove();
            CurrentC += currTask.l;
            WeightedC = currTask.w * CurrentC;
            SumDif += WeightedC;
        }

        long SumDiv = 0;
        CurrentC = 0;

        while (queueDiv.size() != 0) {
            currTask = queueDiv.remove();
            CurrentC += currTask.l;
            WeightedC = currTask.w * CurrentC;
            SumDiv += WeightedC;
        }        
        
        System.out.println("Weighted summ according to differences equals " + SumDif);
        System.out.println("Weighted summ according to division equals " + SumDiv);
        
    }
}
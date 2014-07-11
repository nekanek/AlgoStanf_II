import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
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
    
    
    public static void main(String[] args) throws FileNotFoundException {   // unit testing
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
        
        
        
        
        
        
        
        
//        RandomizedQueue<Integer> intsDeque = new RandomizedQueue<>();
//        int TESTS_NUMBER = 5;
//        String result = "";
//        
//        for (int i = 0; i < TESTS_NUMBER; i++) {
//            System.out.println("..enqueing " + i);
//            intsDeque.enqueue(i);
//            //System.out.println("..pushing " + PUSHED_VALUE);
//            //intsDeque.addFirst(PUSHED_VALUE);
//            result = result + " " + i;
//        }
//        int M = intsDeque.N + 1;
//        System.out.println("Resulting deque: " + result);
//        System.out.println("Its size: " + M);
//        System.out.println("Resulting deque: ");
//        for (Integer i : intsDeque) {
//            System.out.println("In first iterator: ");
//            System.out.println(i + " ");
//            System.out.println("Starting another iterator: ");
//            for (Integer j : intsDeque) {
//                System.out.print(j + " ");
//            }
//        }
//        System.out.println();
//        System.out.println("..dequeing:");
//        while (!intsDeque.isEmpty()) {
//            System.out.println(intsDeque.dequeue());
//        }
//   
    }
}
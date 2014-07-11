import java.util.Comparator;



    public abstract class TasksDiffComparator implements Comparator<Task>
    {
        public int compare(Task x, Task y)
        {
            if ( (x.w - x.l) < (y.w - y.l) ) {
                return -1;
            }
            else if ( (x.w - x.l) > (y.w - y.l) ) {
                return 1;
            }
            else if ((x.w - x.l) == (y.w - y.l)) {
                if (x.w <= y.w) {
                    return -1;
                }
                else 
                    return 1;
            }
            else {
                return 0;
    
           }
        }

    
      
        private class Task {
            public int w;
            public int l;
        
            public Task(int we, int le) {
                w = we;
                l = le;
            }
        }
    }
   


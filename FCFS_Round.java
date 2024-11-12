
import java.util.Scanner;
public class FCFS 
{
    static class Process 
    {
        int id = 0;
        int arrivalTime = 0;
        int burstTime = 0;
        int completionTime = 0;
        int turnaroundTime = 0;
        int waitingTime = 0;
        int priority = 0;

        Process(int id, int arrivalTime, int burstTime,int priority) 
        {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
        }

        Process(int id,int burstTime,int priority) 
        {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
        }
    }

    private static void calculateFCFS(Process[] processes) 
    {
        int currentTime = 0;

        for (Process p : processes) 
        {
            // Ensure the current time is at least the arrival time of the process
            if (currentTime < p.arrivalTime) 
                currentTime = p.arrivalTime;
            

            p.completionTime = currentTime + p.burstTime;

            p.turnaroundTime = p.completionTime - p.arrivalTime;

            p.waitingTime = p.turnaroundTime - p.burstTime;

            currentTime = p.completionTime;
        }
    }   
    private static void calculateRR(Process[] processes) 
    {
        int n = processes.length;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Time Quantum: ");
        int tq = sc.nextInt();
        
        int[] rbt = new int[n];
        for (int i = 0; i < n; i++) 
        {
            rbt[i] = processes[i].burstTime;  // Copy burst time
        }
        
        int currentTime = 0;
        int completed = 0; 
        boolean[] comp = new boolean[n];

        while (completed < n) 
        {
            boolean didProcessRun = false; 

            for (int i = 0; i < n; i++) 
            {
                if (!comp[i]) 
                {
                    didProcessRun = true;                    
                    if (rbt[i] > tq) 
                    {
                        currentTime += tq;
                        rbt[i] -= tq; 
                    }
                    else 
                    {
                        currentTime += rbt[i];
                        rbt[i] = 0;  

                        comp[i] = true;
                        completed++;
                        
                        processes[i].completionTime = currentTime;
                        processes[i].turnaroundTime = currentTime - processes[i].arrivalTime;
                        processes[i].waitingTime = processes[i].turnaroundTime - processes[i].burstTime;
                    }
                }
            }
            
            if (!didProcessRun) 
            {
                currentTime++;
            }
        }
    }



    private static void display(Process[] processes)
    {
        System.out.println("\nProcess ID | Arrival Time | Priority | Burst Time | Completion Time | Turnaround Time | Waiting Time");        
        for (Process p : processes) 
            System.out.printf("%9d | %12d | %9d | %9d | %15d | %14d | %11d\n",
                p.id, p.arrivalTime,p.priority, p.burstTime, p.completionTime, p.turnaroundTime, p.waitingTime);
        
    }
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];
        int ch = 0; // for priority scheduling
        
        do
        {
            System.out.println("\n-------MENU------");
            System.out.println("1.FCFS");
            System.out.println("2.RR");
            System.out.println("3.Exit");
            System.out.print("Enter your choice : ");
            ch = scanner.nextInt();
            switch(ch)
            {
                case 1:
                    for(int i = 0; i < n; i++) 
                    {
                        System.out.print("Enter arrival time for process " + (i + 1) + ": ");
                        int arrivalTime = scanner.nextInt();
                        System.out.print("Enter burst time for process " + (i + 1) + ": ");
                        int burstTime = scanner.nextInt();
                        processes[i] = new Process(i + 1, arrivalTime, burstTime,0);
                    }
                    for(int i = 0; i < n - 1; i++)
                    {
                        for(int j = i + 1; j < n; j++)
                        {
                            if(processes[i].arrivalTime > processes[j].arrivalTime)
                            {
                                Process temp = processes[i];
                                processes[i] = processes[j];
                                processes[j] = temp;
                            }
                        }

                    }
                    calculateFCFS(processes);
                    display(processes);
                    break;

               
                case 2:
                    for(int i = 0; i < n; i++) 
                    {
                        System.out.print("Enter burst time for process " + (i + 1) + ": ");
                        int burstTime = scanner.nextInt();
                        processes[i] = new Process(i + 1, burstTime,0);
                    }
                    for(int i = 0; i < n - 1; i++)
                    {
                        for(int j = i + 1; j < n; j++)
                        {
                            if(processes[i].arrivalTime > processes[j].arrivalTime)
                            {
                                Process temp = processes[i];
                                processes[i] = processes[j];
                                processes[j] = temp;
                            }
                        }

                    }
                    calculateRR(processes);
                    display(processes);
                    break;
            }

        }while(ch != 3);

        scanner.close();
    }
}

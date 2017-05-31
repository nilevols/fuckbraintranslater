package a;

import java.util.Scanner;

public class A
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Process p = new Process(scan.nextLine());
        p.go();
        scan.close();
    }
}

class Memory
{
    private int[] mem;
    
    private int currPoint = 0;
    
    public Memory()
    {
        mem = new int[32];
    }
    
    public void backward()
    {
        currPoint--;
    }
    
    public void forward()
    {
        currPoint++;
        if(currPoint > mem.length - 1)
        {
            int[] newMem = new int[mem.length / 2 + mem.length];
            System.arraycopy(mem, 0, newMem, 0, mem.length);
            mem = newMem;
            newMem = null;
        }
    }
    
    public void increase()
    {
        mem[currPoint]++;
    }
    
    public void decrease()
    {
        mem[currPoint]--;
    }
    
    public int current()
    {
        return mem[currPoint];
    }
}

class Process
{
    private final char[] code;
    
    private final Memory mem = new Memory();
    
    private int currStep = 0;
    
    public Process(String code)
    {
        this.code = code.toCharArray();
    }
    
    private void doCurr()
    {
        switch(code[currStep])
        {
        case '>':
            mem.forward();
        break;
        case '<':
            mem.backward();
            break;
        case '+':
            mem.increase();
            break;
        case '-':
            mem.decrease();
            break;
        case '.':
            System.out.print((char)mem.current());
            break;
        case '[':
            if(mem.current() == 0)
                jumpForward();
            break;
        case ']':
            if(mem.current() != 0)
                jumpBackward();
            break;
        }
        currStep++;
    }
    
    private void jumpForward()
    {
        int tempCurrStep = currStep + 1;
        int count = 0;
        while(count >= 0)
        {
            if(code[tempCurrStep] == '[')
                count++;
            else if(code[tempCurrStep] == ']')
                count--;
            tempCurrStep++;
        }
        currStep = tempCurrStep;
    }
    
    private void jumpBackward()
    {
        int tempCurrStep = currStep - 1;
        int count = 0;
        while(count >= 0)
        {
            if(code[tempCurrStep] == '[')
                count--;
            else if(code[tempCurrStep] == ']')
                count++;
            tempCurrStep--;
        }
        currStep = tempCurrStep;
    }
    
    public void go()
    {
        while(currStep < code.length)
        {
            doCurr();
        }
    }
}

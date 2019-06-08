package Algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GreedyAlgorithm {
    static int MAX = 1000*5000;

    public static void main(String[] args) throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader input = new BufferedReader(new FileReader(filePath+"/src/Algorithm/input/input_greedy.txt"));
        String str = input.readLine().trim();
        int count = Integer.parseInt(str);
        int osSize, memoryTotal, appCount, runningTime, memorySize;
        String[] temp;
        int useableMem;
        int[] memArray;

        while(count > 0){
            str = input.readLine().trim();

            temp = str.split(" ");
            osSize = Integer.parseInt(temp[0]);
            memoryTotal = Integer.parseInt(temp[1]);

            if(osSize > memoryTotal)
                return;
            if(1 > osSize && osSize > 10000)
                return;
            if(1 > memoryTotal && memoryTotal > 10000)
                return;

            useableMem = (memoryTotal - osSize) + 1;

            memArray = new int[useableMem];

            InitArray(memArray);

            str = input.readLine().trim();
            appCount = Integer.parseInt(str);

            while(appCount > 0){
                str = input.readLine().trim();
                temp = str.split(" ");
                runningTime = Integer.parseInt(temp[0]);
                memorySize = Integer.parseInt(temp[1]);
                memArray[0] = 0;
                int mem = 0;

                if(1 > runningTime && runningTime > 50000)
                    return;
                if(1 > memorySize && memorySize > 10000)
                    return;

                while(mem < (useableMem - 1)){
                    if(mem + memorySize >= useableMem){
                        break;
                    }

                    if(memArray[mem] + runningTime < memArray[mem + memorySize]){
                        memArray[mem + memorySize] = memArray[mem] + runningTime;
                    }

                    mem++;
                }
                appCount--;
            }

            if(memArray[useableMem-1] == MAX){
                System.out.println("result : -1");
            }else{
                System.out.println("result : "+memArray[useableMem-1]);
            }

            count--;
        }

        input.close();
    }

    static void InitArray(int[] memArray){
        for(int i = 0; i < memArray.length; i++){
            memArray[i] = MAX;
        }
    }
}

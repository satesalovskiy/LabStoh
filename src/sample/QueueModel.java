package sample;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class QueueModel {
    public static final int NUMBER_OF_ITERATIONS = 5;
    private LinkedList<Call> queue;
    private int customerIntensity;
    private int serverSpeed;
    private double sumOfTimeCallInSystem = 0;

    private double sumOfAllTime = 0;
    private double tCurrent, tQueue;
    private double avCreateTime, avHandleTime;
    private int serverIsBusy=0;
    private int alltime =0;

    private HashMap<Integer, Integer> ergod;


    public QueueModel(int customerIntensity, int serverSpeed) {
        this.customerIntensity = customerIntensity;
        this.serverSpeed = serverSpeed;
        queue = new LinkedList<>();
        avCreateTime = (double) 1 / (double)customerIntensity;
        avHandleTime = (double) 1 / (double)serverSpeed;
        ergod = new HashMap<>();
    }

    public void work() {
        Random random = new Random();
        int steps;
        int ccount = 0;
        int scount = 0;
        Call a = new Call(0, 0, 0);

        if (serverSpeed > customerIntensity) {
            steps = serverSpeed;
        } else {
            steps = customerIntensity;
        }

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {

            if(!ergod.containsKey(queue.size())){
                ergod.put(queue.size(), 1);
            } else {
                ergod.put(queue.size(), ergod.get(queue.size())+1);
            }

           // System.out.println(queue.size());


            for (int m = 0; m < steps; m++) {

                alltime++;

                if (ccount < customerIntensity) {
                    ccount++;

                    double tRand = 0.01 + (avCreateTime - 0.01) * random.nextDouble(); // Время на создание
                    double oRand = 0.01 + (avHandleTime - 0.01) * random.nextDouble(); //Время на обработку

                   // sumOfTimeCallInSystem +=tRand;

                    sumOfAllTime += tRand + oRand;

                    if (a.oRand > tRand) {
                        tQueue += a.tRand;
                        serverIsBusy++;

                        sumOfTimeCallInSystem += a.oRand;
                    }

                    queue.addLast(new Call(i, tRand, oRand));
                }

                if (!queue.isEmpty() && scount < serverSpeed) {
                    scount++;

                    a = queue.pollFirst();
                }

            }
            ccount = 0;
            scount = 0;
        }

        // System.out.println(tQueue);
        //  System.out.println(sumOfAllTime);
        System.out.println("Среднее время пребывания требования в очереди: " + sumOfTimeCallInSystem / sumOfAllTime);
//        System.out.println(sumOfTimeCallInSystem);
//        System.out.println(sumOfAllTime);
//
////        System.out.println(alltime);
////        System.out.println(serverIsBusy);
//        System.out.println(serverIsBusy);
//        System.out.println(alltime);
        System.out.println("Вероятность, что сервер занят: " + ((double)serverIsBusy/(double)alltime));

        double averageNumberOfCallInAQueue;

        System.out.println("Среднее количество заявок в очереди: "  );
        System.out.println("Размер очереди: " + ergod);
    }
}

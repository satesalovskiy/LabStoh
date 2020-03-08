package sample;

import java.util.LinkedList;
import java.util.Random;

public class QueueModel {
    public static final int NUMBER_OF_ITERATIONS = 1000;
    private LinkedList<Call> queue;
    private int customerIntensity;
    private int serverSpeed;
    private double sumOfTimeCallInSystem = 0;

    private double sumOfAllTime = 0;
    private double tCurrent, tQueue;
    private double avCreateTime, avHandleTime;


    public QueueModel(int customerIntensity, int serverSpeed) {
        this.customerIntensity = customerIntensity;
        this.serverSpeed = serverSpeed;
        queue = new LinkedList<Call>();
        avCreateTime = (double)1/customerIntensity;
        avHandleTime = (double)1/serverSpeed;
    }

    public void work() {
        Random random = new Random();
        int steps;

        //Основной цикл. NUMBER_OF_ITERATIONS - количество единиц времени всего в программе
        for(int i =0; i<NUMBER_OF_ITERATIONS; i++) {

            //В этом цикле создаются и добавляются заявки в очередь, в количестве лямбда
            for(int m=0; m < customerIntensity; m++){

                //tRand, oRand - это случайные значения в интервале (0, 1/лямбда]-(avCreationTime) или (0, 1/мю] - (avHandleTime)
                //
                double tRand = 0.01 + (avCreateTime - 0.01) * random.nextDouble();
                double oRand = 0.01 + (avHandleTime - 0.01) * random.nextDouble();

                //Добавление заявки в очередь. На всякий случай, сохраняю текущий момент времени и tRand, oRand
                queue.addLast(new Call(i, tRand, oRand));

                //Сумма всего времени в системе
                sumOfAllTime += tRand + oRand;

                //Непонятное мне tCurrent
                tCurrent += tRand;
            }

            //Здесь просто определяется то, сколько заявок обработает наш сервер.
            //customerIntensity - лямбда
            //serverSpeed - мю
            if(serverSpeed > customerIntensity){
                steps = customerIntensity;
            } else {
                steps = serverSpeed;
            }

            //Проходимся по тому количеству заявок сколько можем обработать
            for (int m=0; m < steps; m++){
                if(!queue.isEmpty()){


                    //Условие, про которое ты писала
                    if(tCurrent<sumOfAllTime){

                        //Достаем заявку
                        queue.pollFirst();

                        //Суммируем количество времени в очереди
                        tQueue += sumOfAllTime - tCurrent;
                    }

                }
            }
        }
        System.out.println(tQueue);
    }
}

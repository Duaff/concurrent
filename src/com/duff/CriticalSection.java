package com.duff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by dafu on 2017/1/2.
 */
public class CriticalSection {
    static void testApproaches(PairManager pm1, PairManager pm2) {
        ExecutorService es = Executors.newCachedThreadPool();
        PairManipulator pmp1 = new PairManipulator(pm1);
        PairManipulator pmp2 = new PairManipulator(pm2);
        PairChecker pcheck1 = new PairChecker(pm1);
        PairChecker pcheck2 = new PairChecker(pm2);
        es.execute(pcheck1);
        es.execute(pcheck2);
        es.execute(pmp1);
        es.execute(pmp2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("pmp1:" + pmp1 + "-pmp2:" + pmp2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pm1 = new PairManage1();
        PairManager pm2 = new PairManage2();
        testApproaches(pm1,pm2);
    }
}

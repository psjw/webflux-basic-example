package psjw.webflux.basic.ch03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CModel {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
      log.info("Start main");
      var count = 1;
        Future<Integer> result = getResult();
        while(!result.isDone()){
            log.info("Waiting for result, count: {}", count++);
            Thread.sleep(100);
        }

        var nextValue = result.get() + 1;
        assert nextValue == 1;
        log.info("Finish main");
    }

    private static Future<Integer> getResult() {
        //1개의 쓰레드를 생성하고 끝냄
        var executor = Executors.newSingleThreadExecutor();
        try{
            return executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    log.info("Start getResult");
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    var result = 0;
                    try {
                        return result;
                    }finally {
                        log.info("Finish getResult");
                    }
                }
            });
        }finally {
            executor.shutdown();
        }
    }


}

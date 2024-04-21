package psjw.webflux.basic.ch04;

import java.util.concurrent.*;

public class FutureHelper {
    public static Future<Integer> getFuture(){
        var executor = Executors.newSingleThreadExecutor();
        try {
            return executor.submit(() -> {
                return 1;
            });
        } finally {
            executor.shutdown();
        }
    }

    public static Future<Integer> getFutureCompleteAfter1s(){
        var executor = Executors.newSingleThreadExecutor();
        try{
            return executor.submit(() -> {
                Thread.sleep(1000);
                return 1;
            });
        }finally {
            executor.shutdown();
        }
    }

    public static Future<?> getFutureWithException() {
        var executor = Executors.newSingleThreadExecutor();
        try{
            return executor.submit(() -> {
                new ExecutionException(new Exception("Error"));
            });
        }finally {
            executor.shutdown();
        }
    }
}

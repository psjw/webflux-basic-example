package psjw.webflux.basic.ch04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Ch04_Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Future<Integer> future = FutureHelper.getFuture();
        assert !future.isDone();
        assert !future.isCancelled();

        var result = future.get();
        assert result.equals(1);
        assert future.isDone();
        assert !future.isCancelled();

        Future<Integer> future1 = FutureHelper.getFutureCompleteAfter1s();
        var result1 = future1.get(1500, TimeUnit.MILLISECONDS);
        assert result1.equals(1);

        //Timeout
        Future<Integer> futureToTimeout = FutureHelper.getFutureCompleteAfter1s();
        Exception exception = null;
        try{
            futureToTimeout.get(500, TimeUnit.MILLISECONDS);
        }catch (TimeoutException e){
            exception = e;
        }
        assert exception != null;

        //cancel
        Future<Integer> future2 = FutureHelper.getFuture();
        var successToCancel = future2.cancel(true);
        assert future2.isCancelled();
        assert future2.isDone();
        assert successToCancel;

        successToCancel = future2.cancel(true);
        assert future2.isCancelled();
        assert future2.isDone();
        assert !successToCancel;

        //futrue 인터페이스 한계
        Future futureToCancel = FutureHelper.getFuture();
        futureToCancel.cancel(true);

        Future<?> futureWithException = FutureHelper.getFutureWithException();
        Exception exception1 = null;
        try{
            futureWithException.get();
        }catch (ExecutionException e){
            exception1 = e;
        }
        assert futureWithException.isDone();
        assert exception1 != null;
    }
}

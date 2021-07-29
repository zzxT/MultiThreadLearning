package semaphore.semaphore_1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreApi {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);

        semaphore.acquire(2);
//        semaphore.acquire();
        semaphore.release(3);

        System.out.println(semaphore.availablePermits()); // 3

//        semaphore.tryAcquire()

        semaphore.drainPermits();

        System.out.println(semaphore.availablePermits()); // 0


        boolean acquire = semaphore.tryAcquire();
        System.out.println(acquire); // false

        boolean acquire1 = semaphore.tryAcquire(1, TimeUnit.SECONDS);
        System.out.println(acquire1); // false




    }
}

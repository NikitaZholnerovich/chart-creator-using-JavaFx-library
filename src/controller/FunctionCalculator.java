package controller;

import model.Point;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FunctionCalculator {
    private ExecutorService executor
            = Executors.newFixedThreadPool(2);

    public Future<Double> CalculateYForFunction1(Double x) {
        return executor.submit(() -> {
            return 3 * x - 4.0;
        });
    }

    public Future<Double> CalculateYForFunction2(Double x) {
        return executor.submit(() -> {
            double sum = 0.0;
            for (int k = 0; k < 10; k += 1) {
                sum += (Math.pow(-1, k) * Math.pow(x, 2 * k)) / (factorial(2 * k));
            }

            return sum;
        });

    }

    private long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}


package spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Pi {
    private static final int NUM_SAMPLES = 100;
    
    public static void main(String[] args) {
        SparkConf sparkConfig = 
                 new SparkConf().setAppName("Pi")
                                .set("spark.default.parallelism", "2")
                                .set("spark.executor.memory", "512m")
                                .set("spark.executor.cores", "1");
        
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConfig);

        List<Integer> l = new ArrayList<>(NUM_SAMPLES);
        for (int i = 0; i < NUM_SAMPLES; i++) {
          l.add(i);
        }

        long count = sparkContext.parallelize(l).filter(i -> {
          double x = Math.random();
          double y = Math.random();
          return x*x + y*y < 1;
        }).count();
        
        System.out.println("Pi is roughly " + 4.0 * count / NUM_SAMPLES);
    }
}


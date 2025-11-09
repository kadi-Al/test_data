xml
<dependencies>
    <dependency>
        <groupId>org.apache.spark</groupId>
        <artifactId>spark-core_2.12</artifactId>
        <version>3.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-aws</artifactId>
        <version>3.3.1</version>
    </dependency>
</dependencies>
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;
import java.util.List;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import org.apache.spark.storage.StorageLevel;

public class SparkS3Example {
    public static void main(String[] args) {
        // Initialize Spark Context
        JavaSparkContext sc = new JavaSparkContext("local", "Spark S3 Example", System.getenv("SPARK_HOME"));

        // Set AWS credentials and region
        DefaultAWSCredentialsProviderChain awsCredentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
        sc.hadoopConfiguration().set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem");
        sc.hadoopConfiguration().set("fs.s3a.access.key", awsCredentialsProvider.getCredentials().getAWSAccessKeyId());
        sc.hadoopConfiguration().set("fs.s3a.secret.key", awsCredentialsProvider.getCredentials().getAWSSecretKey());
        sc.hadoopConfiguration().set("fs.s3a.endpoint", "s3.amazonaws.com");
        sc.hadoopConfiguration().set("fs.s3a.region", Regions.US_EAST_1.getName());

        // Example data - in a real application, you would read from multiple S3 buckets here
        List<Tuple2<String, Integer>> data = Arrays.asList(new Tuple2<>("foo", 1), new Tuple2<>("bar", 2), new Tuple2<>("foo", 3));
        JavaPairRDD<String, Integer> rdd = sc.parallelizePairs(data);

        // Perform reduceByKey operation with a custom lambda function
        JavaPairRDD<String, Integer> result = rdd.reduceByKey((a, b) -> a + b);

        // Save the result back to S3 (or any Hadoop-compatible storage)
        result.saveAsTextFile("s3a://your-bucket/output");

        sc.stop();
    }
}
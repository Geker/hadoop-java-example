package com.umermansoor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * The main application class.
 *
 * @author Umer Mansoor
 */
public class App
{
    /**
     * Application entry point.
     * @param args
     * @throws Exception - Bad idea but produces less cluttered code.
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: hadoopex <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf=new Configuration();
		// Create the job specification object
        Job job = Job.getInstance(conf);
        job.setJarByClass(App.class);
        job.setJobName("Earthquake Measurment");

        // Setup input and output paths
        int len=args.length;
        FileInputFormat.addInputPath(job, new Path(args[len-2]));
        FileOutputFormat.setOutputPath(job, new Path(args[len-1]));

        // Set the Mapper and Reducer classes
        job.setMapperClass(EarthquakeMapper.class);
        job.setReducerClass(EarthquakeReducer.class);

        // Specify the type of output keys and values
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // Wait for the job to finish before terminating
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

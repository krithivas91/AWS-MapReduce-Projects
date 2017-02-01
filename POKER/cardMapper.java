package poker;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class cardMapper extends Mapper<LongWritable,Text,LongWritable,Text>
	{		
		public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException
		{
			String line = value.toString();
			context.write(new LongWritable(Long.parseLong(line.split(" ")[0])),new Text(line.split(" ")[1]));	
		}
	
	public static void main(String[] args) throws Exception  
	{
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf,"Job");
	    job.setJarByClass(cardMapper.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    job.setMapperClass(cardMapper.class);
	    job.setReducerClass(cardReducer.class);
	    job.setMapOutputKeyClass(LongWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(LongWritable.class);
	    job.setOutputValueClass(Text.class);
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}


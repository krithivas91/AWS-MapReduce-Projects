package poker;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class cardReducer extends Reducer<LongWritable,Text,LongWritable,Text>
{
	public void reduce(LongWritable key,Iterable<Text>values, Context context) throws IOException,InterruptedException
	{	
		String result = "";
		String Stringofall = "";
		String[] pack = {"heart","diamond","spade","club"};
		boolean b;
		for(Text value : values)
		{
			
			result += value + " ";
		}
		for (int i=0;i<pack.length;i++)
		{
			b = result.contains(pack[i]);
			if(!b)
			{
				Stringofall += pack[i] + " ";
				
			}
		}
		context.write(key,new Text(Stringofall));	
	}
}

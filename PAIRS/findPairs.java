package findPairs;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;




public class findPairs {
	
	public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
    	Job job = Job.getInstance(conf,"First Job");
        
    	job.setJarByClass(findPairs.class);

        job.setNumReduceTasks(1);

        job.setOutputKeyClass(WordPair.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(PairLocationMapper.class);
 
        job.setReducerClass(WordPairReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        
        
        
        job.setMapOutputKeyClass(WordPair.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }

    public static class PairLocationMapper extends Mapper<LongWritable, Text, WordPair, IntWritable> {
        WordPair wordPair = new WordPair();
        IntWritable first = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            int nextWord = context.getConfiguration().getInt("neighbors", 1);    
            String[] word = value.toString().split("\\s+");
            if (word.length > 1) {
              for (int counter = 0; counter < word.length; counter++) {
                  wordPair.setAlpha(word[counter]);

                 int begin = (counter - nextWord < 0) ? 0 : counter - nextWord;
                 int end = (counter + nextWord >= word.length) ? word.length - 1 : counter + nextWord;
                  for (int k = begin; k <= end; k++) {
                      if (k == counter) continue;
                       wordPair.setNextWord(word[k]);
                      
                       context.write(wordPair, first);
                  }
              }
          }
      }
    }


    public static class WordPairReducer extends Reducer<WordPair,IntWritable,Text,Text> {
        private IntWritable allCount = new IntWritable();
        
        Map<WordPair,IntWritable> wordMap = new HashMap<WordPair, IntWritable>();
        
        @Override
        protected void reduce(WordPair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
           
        	int counter = 0;
            for (IntWritable value : values) {
                 counter += value.get();
            }
            allCount.set(counter);
            
            System.out.println(key.toString() + allCount);
            wordMap.put(key,allCount);
            
            
            }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
        	
        	List numberList = new ArrayList(wordMap.entrySet());
        	Collections.sort(numberList,new Comparator()
        	{
        		public int compare(Object o1,Object o2)
        		{
        			return ((Comparable)((Map.Entry)(o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue());
        		}
        	});
        			
        	List finalList = numberList.subList(numberList.size() -100, numberList.size());
            for (int i=0;i<finalList.size();i++)
            {
            	context.write(new Text(finalList.get(i).toString().split("=")[0]),new Text(finalList.get(i).toString().split("=")[1]));
            }
     
      }
        
    }
    public static class WordPair implements Writable,WritableComparable<WordPair> {

        private Text word;
        private Text neighbor;

        public WordPair(Text word, Text neighbor) {
            this.word = word;
            this.neighbor = neighbor;
        }

        public WordPair(String word, String neighbor) {
            this(new Text(word),new Text(neighbor));
        }

        public WordPair() {
            this.word = new Text();
            this.neighbor = new Text();
        }

        @Override
        public int compareTo(WordPair other) {
            int returnVal = this.word.compareTo(other.getAlpha());
            if(returnVal != 0){
                return returnVal;
            }
            if(this.neighbor.toString().equals("*")){
                return -1;
            }else if(other.getNextWord().toString().equals("*")){
                return 1;
            }
            return this.neighbor.compareTo(other.getNextWord());
        }

        public WordPair read(DataInput in) throws IOException {
            WordPair wordPair = new WordPair();
            wordPair.readFields(in);
            return wordPair;
        }

        @Override
        public void write(DataOutput out) throws IOException {
            word.write(out);
            neighbor.write(out);
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            word.readFields(in);
            neighbor.readFields(in);
        }

        @Override
        public String toString() {
            return "{word=["+word+"]"+
                   " neighbor=["+neighbor+"]}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WordPair wordPair = (WordPair) o;

            if (neighbor != null ? !neighbor.equals(wordPair.neighbor) : wordPair.neighbor != null) return false;
            if (word != null ? !word.equals(wordPair.word) : wordPair.word != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int value = word != null ? word.hashCode() : 0;
            value = 163 * value + (neighbor != null ? neighbor.hashCode() : 0);
            return value;
        }

        public void setAlpha(String word){
            this.word.set(word);
        }
        public void setNextWord(String neighbor){
            this.neighbor.set(neighbor);
        }

        public Text getAlpha() {
            return word;
        }

        public Text getNextWord() {
            return neighbor;
        }


    }

}
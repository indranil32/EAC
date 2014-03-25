import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.ProgramDriver;

public class MemoryUtilization {

	public static class CPUTokenizerMapper extends
			Mapper<Object, Text, Text, Text> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private Text outputText= new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			org.apache.hadoop.mapreduce.lib.input.FileSplit fileSplit = ( org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit();
			FileSystem fs = FileSystem.get(new Configuration());
			String filename = fileSplit.getPath().toString();
			String date = filename.split("_")[1];
			String output=  "";
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				String tokenValue = itr.nextToken().toString();
				if(tokenValue.equals("MemTotal:") 
						|| tokenValue.equals("MemFree:")
						|| tokenValue.equals("Cached:")
						|| tokenValue.equals("SwapCached:")){
					output = output.equals("")?tokenValue+itr.nextToken():output+"|"+tokenValue+itr.nextToken();
				}
			}
			word.set(date);
			outputText.set(output);
			context.write(word, outputText);
		}
	}

	public static class CPUIntSumReducer extends
			Reducer<Text, Text, Text, Text> {
		private Text result = new Text();

		public void reduce(Text key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {
				String appendString = "";
				for (Text val : values) {
					appendString = appendString.equals("")?val.toString():appendString+"|"+val.toString();
				}
				result.set(appendString);
				context.write(key, result);
		}
	}

	public static void main(String[] argv) throws Exception {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, argv)
				.getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: errorcount <in> <out>");
			System.exit(2);
		}
		@SuppressWarnings("deprecation")
		Job job = new Job(conf, "memory utilization");
		job.setJarByClass(MemoryUtilization.class);
		job.setMapperClass(CPUTokenizerMapper.class);
		job.setCombinerClass(CPUIntSumReducer.class);
		job.setReducerClass(CPUIntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

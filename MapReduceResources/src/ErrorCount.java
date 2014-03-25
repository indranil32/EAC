import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
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

public class ErrorCount {

	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				String token=itr.nextToken();
				if (null!=token && ""!=token && token.equalsIgnoreCase("ERROR")) {
					word.set("ERROR");
					context.write(word, one);
				} else if (null!=token && ""!=token && token.equalsIgnoreCase("WARNING")) {
					word.set("WARNING");
					context.write(word, one);
				} else if (null!=token && ""!=token && token.equalsIgnoreCase("EXCEPTION")) {
					word.set("EXCEPTION");
					context.write(word, one);
				}
			}
		}
	}

	public static class IntSumReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {

			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	@SuppressWarnings("deprecation")
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
		Job job = new Job(conf, "error count");
		job.setJarByClass(ErrorCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

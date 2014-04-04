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


public class RebootCount {
	
    private static final String REASON_IDENTIFIER_TOKEN = "Reason:";
    private static final String VALUE_SPLITTER = ":=:";
    
    public static class RebootCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    	private Text reason = new Text();
    	private final static IntWritable one = new IntWritable(1);

		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		    if (!value.toString().contains(REASON_IDENTIFIER_TOKEN)) {
			return;
		    }
		    StringTokenizer itr = new StringTokenizer(value.toString());
		    StringBuffer rowdata = new StringBuffer();   
	    	while (itr.hasMoreTokens()) {
	    		String token = itr.nextToken().trim();
				if(token.equals(REASON_IDENTIFIER_TOKEN)){
					while (itr.hasMoreTokens()){
						if(rowdata.length()!=0)
							rowdata.append(" ");
						else
							rowdata.append("Reason"+VALUE_SPLITTER);
						rowdata.append(itr.nextToken().trim());
					}
				}
		    }
	    	rowdata.append(VALUE_SPLITTER+"Count"+VALUE_SPLITTER);
	    	reason.set(rowdata.toString());
		    context.write(reason, one);
		}
    }

    public static class RebootCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

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

    /**
     * @throws Exception
     * @param args
     * @param
     * @return void
     */
    public static void main(String[] args) throws Exception {
	Configuration conf = new Configuration();
	@SuppressWarnings("deprecation")
	Job job = new Job(conf, "rebootcount");
	job.setJarByClass(RebootCount.class);
	job.setMapperClass(RebootCountMapper.class);
	job.setCombinerClass(RebootCountReducer.class);
	job.setReducerClass(RebootCountReducer.class);	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

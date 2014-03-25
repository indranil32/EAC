import java.io.IOException;
import java.util.StringTokenizer;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.mortbay.util.ajax.JSON;


public class IVSPAssetMR {

	/**
	 * @param args
	 */
	public static class IVSPMapper extends
	Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		
		private static final IVSPBean PARSER = new IVSPBean() ;

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			 if(value.equals(null) || value.getLength() == 0)
	               return;
			 System.out.println("Value"+value.toString());
			 String textValue = "";
			 StringTokenizer itr = new StringTokenizer(value.toString());
				while (itr.hasMoreTokens()) {
					textValue=textValue+itr.nextToken().toString();
				}
				//java.util.HashMap<String,Object> json1 = (java.util.HashMap<String,Object>) JSON.parse(textValue);
			    /*JSONObject content = null;
				try {
					content = json.getJSONObject(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				/*for(Iterator json1.get){
					
				}
				Object session = (Object) json1.get("sessionInfos");*/
				
			    //ObjectMapper mapper = new ObjectMapper();
			    //IVSPBean data= mapper.readValue(json1.toString(), IVSPBean.class);
			    IVSPBean bean = IVSPBean.fromJsonDoc(textValue.toString());
			 //System.out.println("IVSPBEAN"+ data.toString());
			 //JSONObject jo = null;
				//java.util.HashMap<String,Object> json2 = (java.util.HashMap<String,Object>) JSON.parse(session.to);
			 word.set(bean.getOperatingParameters().getVod_assetTitle());//data.getSessionInfos().getOperatingParameters().getVod_assetTitle());
			 context.write(word, one);
		}
	}

	public static class IVSPReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			//if (key.toString().equalsIgnoreCase("ERROR")) {
				int sum = 0;
				for (IntWritable val : values) {
					sum += val.get();
				}
				result.set(sum);
				context.write(key, result);
			//}
		}
	}

	public static void main(String[] argv) throws Exception {
		int exitCode = -1;
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, argv)
				.getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: errorcount <in> <out>");
			System.exit(2);
		}
		@SuppressWarnings("deprecation")
		Job job = new Job(conf, "IVSP count");
		job.setJarByClass(IVSPAssetMR.class);
		job.setMapperClass(IVSPMapper.class);
		job.setCombinerClass(IVSPReducer.class);
		job.setReducerClass(IVSPReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

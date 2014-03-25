
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class IVSPAssetMRNew {
	/**
	 * @param args
	 */
	public static class IVSPMapperNew extends
	Mapper<Object, Text, Text, Text> {

			private Text Asset = new Text();
			private Text Session = new Text();
		
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			 org.apache.hadoop.mapreduce.lib.input.FileSplit fileSplit = ( org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit();
			FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(fileSplit.getPath())));
            String line;
            String wholeText = "";
            line=br.readLine();
            while (line != null){
            	wholeText+=line;
                line=br.readLine();
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
			    IVSPBean bean = IVSPBean.fromJsonDoc(wholeText.toString());
			 //System.out.println("IVSPBEAN"+ data.toString());
			 //JSONObject jo = null;
				//java.util.HashMap<String,Object> json2 = (java.util.HashMap<String,Object>) JSON.parse(session.to);
			 Asset.set(bean.getOperatingParameters().getVod_assetTitle());//data.getSessionInfos().getOperatingParameters().getVod_assetTitle());
			 Session.set(bean.getServerSessionId().toString());
			 context.write(Asset, Session);
		}
	}

	public static class IVSPReducerNew extends
			Reducer<Text, Text, Text, Text> {
		private IntWritable result = new IntWritable();
		private Text textOutput = new Text();  
		public void reduce(Text key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {
				int sum = 0;
				List<String> sessionList = new ArrayList<String>();
				for (Text val : values) {
					if(!sessionList.contains(val.toString())){
						sessionList.add(val.toString());
						sum += 1;
					}
				}
				result.set(sum);
				textOutput.set(result.toString());
				context.write(key, textOutput);
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
		Job job = new Job(conf, "IVSP count New");
		job.setJarByClass(IVSPAssetMRNew.class);
		job.setMapperClass(IVSPMapperNew.class);
		job.setCombinerClass(IVSPReducerNew.class);
		job.setReducerClass(IVSPReducerNew.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		NonSplittableTextFile.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		NonSplittableTextFile.setMaxInputSplitSize(job, 10000000L);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

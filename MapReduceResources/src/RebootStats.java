import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class RebootStats {
	private static final String PID_IDENTIFIER_TOKEN    = "Failing PID";
    private static final String REASON_IDENTIFIER_TOKEN = "Reason:";
    private static final String VALUE_SPLITTER = ":=:";
    private static final String MAC_IDENTIFIER_TOKEN = "MAC:";

    public static class RebootRowMapper extends Mapper<Object, Text, Text, Text> {

	private Text   mapperkey = new Text();
	private Text   output    = new Text();

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    if (!value.toString().contains(PID_IDENTIFIER_TOKEN) && !value.toString().contains(REASON_IDENTIFIER_TOKEN) && !value.toString().contains(MAC_IDENTIFIER_TOKEN)) {
		return;
	    }
	    StringTokenizer itr = new StringTokenizer(value.toString());
	    StringBuffer rowdata = new StringBuffer();
	    StringBuffer keydata = new StringBuffer();
	    int count = 0;
	    String failTime = "";
	    if(value.toString().contains(PID_IDENTIFIER_TOKEN)){
			while (itr.hasMoreTokens()) {
				String token = itr.nextToken().trim();
				if(keydata.length()==0){
					failTime = token;
					keydata.append(token.substring(0, 10));
				}else if(count==2){
					keydata.append(VALUE_SPLITTER);
					keydata.append(token);
				}else if(token.equals("PID")){
					if(itr.hasMoreTokens()){
						token = itr.nextToken();
						rowdata.append(VALUE_SPLITTER+"Timestamp"+VALUE_SPLITTER+failTime+VALUE_SPLITTER+"PID"+VALUE_SPLITTER);
						rowdata.append(token);
					}
				}
				count++;
		    }
	    }else if(value.toString().contains(REASON_IDENTIFIER_TOKEN)){
	    	while (itr.hasMoreTokens()) {
	    		String token = itr.nextToken().trim();
				if(keydata.length()==0){
					keydata.append(token.substring(0, 10));
				}else if(count==2){
					keydata.append(VALUE_SPLITTER);
					keydata.append(token);
				}else if(token.equals(REASON_IDENTIFIER_TOKEN)){
					while (itr.hasMoreTokens()){
						if(rowdata.length()!=0)
							rowdata.append(" ");
						else
							rowdata.append("Reason"+VALUE_SPLITTER);
						rowdata.append(itr.nextToken().trim());
					}
				}
				count++;
		    }
	    }else if(value.toString().contains(MAC_IDENTIFIER_TOKEN)){
	    	while (itr.hasMoreTokens()) {
	    		String token = itr.nextToken().trim();
				if(keydata.length()==0){
					keydata.append(token.substring(0, 10));
				}else if(count==2){
					keydata.append(VALUE_SPLITTER);
					keydata.append(token);
				}else if(token.equals(MAC_IDENTIFIER_TOKEN)){
					while (itr.hasMoreTokens()){
						if(rowdata.length()!=0)
							rowdata.append(" ");
						else
							rowdata.append("MAC"+VALUE_SPLITTER);
						rowdata.append(itr.nextToken().trim());
					}
				}
				count++;
		    }
	    }
	    mapperkey.set(keydata.toString().trim());
	    output.set(rowdata.toString());
	    context.write(mapperkey, output);
	}
    }

    public static class RebootRowReducer extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	    StringBuffer appendString = new StringBuffer();
	    String message = "";
	    String mac="";
	    for (Text val : values) {
	    	if(val.toString().contains("PID"+VALUE_SPLITTER)){
	    		appendString.append(val);
	    	}else if(val.toString().contains("Reason"+VALUE_SPLITTER)){
	    		message = VALUE_SPLITTER+val.toString();
	    	}else if(val.toString().contains("MAC"+VALUE_SPLITTER)){
	    		mac = VALUE_SPLITTER+val.toString();
	    	}
	    }
	    appendString.append(message);
	    appendString.append(mac);
	    result.set(appendString.toString());
	    if(appendString.toString().contains("PID"+VALUE_SPLITTER)){
	    	context.write(key, result);
	    }
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
	Job job = new Job(conf, "rebootstats");
	job.setJarByClass(RebootStats.class);
	job.setMapperClass(RebootRowMapper.class);
	job.setCombinerClass(RebootRowReducer.class);
	job.setReducerClass(RebootRowReducer.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

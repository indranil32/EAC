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


public class CopyRebootTry {
	private static final String PID_IDENTIFIER_TOKEN    = "Failing PID";
    private static final String REASON_IDENTIFIER_TOKEN = "Reason:";
    private static final String VALUE_SPLITTER = ":=:";

    public static class RebootRowMapper extends Mapper<Object, Text, Text, Text> {

	private Text   mapperkey = new Text();
	private Text   output    = new Text();

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    if (!value.toString().contains(PID_IDENTIFIER_TOKEN) && !value.toString().contains(REASON_IDENTIFIER_TOKEN)) {
		return;
	    }
	    System.out.println("reboottry :: map : " + value.toString());
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
						rowdata.append(failTime+VALUE_SPLITTER+"PID"+VALUE_SPLITTER);
						rowdata.append(token);
					}
				}
				count++;
		    }
	    }else{
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
						rowdata.append(itr.nextToken().trim());
					}
				}
				count++;
		    }
	    }
	    mapperkey.set(keydata.toString().trim());
	    output.set(rowdata.toString());
	    System.out.println("Key     "+mapperkey.toString());
	    System.out.println("Value     "+output.toString());
	    context.write(mapperkey, output);
	}
    }

    public static class RebootRowReducer extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	    System.out.println("rebootstats :: reduce Key: " + key.toString());
	    StringBuffer appendString = new StringBuffer();
	    String PIDValue="";
	    String message = "";
	    for (Text val : values) {
	    	if(val.toString().contains("PID"+VALUE_SPLITTER)){
	    		//PIDValue=val.toString().split(VALUE_SPLITTER)[0];
	    		//appendString.append(PIDValue+VALUE_SPLITTER);
	    		appendString.append(val);
	    	}else{
	    		message = val.toString();
	    	}
	    }
	    appendString.append(VALUE_SPLITTER);
	    appendString.append(message);
	    /*for (Text value : values) {
	    	if(!(value.toString().contains("PID"+VALUE_SPLITTER))){
	    		//PIDValue=val.toString().split(VALUE_SPLITTER)[0];
	    		//appendString.append(PIDValue+VALUE_SPLITTER);
	    		appendString.append(value.toString());
	    	}
	    }*/
	    /*for (Text val : values) {
	    	if(val.toString().contains("PID"+VALUE_SPLITTER)){
	    		continue;
	    	}else{
	    		if(appendString.length()!=0)
	    			appendString.append(" ");
	    		appendString.append(val.toString());
	    	}
	    }*/
	    //key.set(PIDValue);	   
	    result.set(appendString.toString()); 
	    System.out.println("rebootstats :: Key for OP: " + key.toString());
    	System.out.println("rebootstats :: reduce  OP: " + appendString);
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
	System.out.println("reboottry :: STARTS");
	Configuration conf = new Configuration();
	@SuppressWarnings("deprecation")
	Job job = new Job(conf, "reboottry");
	job.setJarByClass(RebootStats.class);
	job.setMapperClass(RebootRowMapper.class);
	job.setCombinerClass(RebootRowReducer.class);
	job.setReducerClass(RebootRowReducer.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	System.out.println("reboottry :: INPROGRESS");
	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

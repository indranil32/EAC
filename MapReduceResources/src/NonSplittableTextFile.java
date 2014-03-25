import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;


public abstract class NonSplittableTextFile extends FileInputFormat  {

	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		    return false;
	}
}

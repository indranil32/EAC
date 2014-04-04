/**
 * 
 */
import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;
/**
 * @author imajumde
 *
 */
public class ReadOutput {
	
	public static void main (String[] args) {
		read(args[0]);
	}
	
	public static void read(String path) {
        	try{
        		Path pt=new Path(path);
        		FileSystem fs = FileSystem.get(new Configuration());
	        	BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	        	String line = br.readLine();
	        	StringBuffer content = new StringBuffer();
        		while (line != null){
        			content.append(line);
		                content.append("\n");
				System.out.println(line);
                		line=br.readLine();
	        	}
        		write(content.toString(), "../hadoop-2.2.0/last/analysis.out");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void write (String content, String path) {
		try {
			File file = new File(path);
			// if file exists, archive it and create new
			if (file.exists()) {
				System.out.println("analysis out exists from previous job output");
				if(file.renameTo(new File("../hadoop-2.2.0/last/archive/"+file.getName()+"_"+Calendar.getInstance().getTimeInMillis()))) 
					System.out.println("Archived file : " + file.getName());
				else
					System.out.println("Archiving failed");
				// new analysis.out
				file =  new File(path);
				file.createNewFile();
			} else {
				 System.out.println("New file");
				file.createNewFile();
			}
			System.out.println("File path : " +file.getAbsoluteFile());
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			//fw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


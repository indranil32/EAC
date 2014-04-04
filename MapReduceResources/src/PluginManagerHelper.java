import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PluginManagerHelper {
	public PluginManagerHelper(){
		System.out.println("Setting env!!");
	}

	public static void main(String args[]) throws Exception{
		PluginManagerHelper helper = new PluginManagerHelper();
		helper.run("../hadoop-2.2.0/./run.sh","/usr/local/bin/bash hadoop jar MR.jar RebootStats","/logs/C2K/","/out102");
	}

	public FileInputStream run(String env, String exec, String input, String output) throws Exception {
		System.out.println("Running... env = " +env);
		Process p = Runtime.getRuntime().exec(env);
		try {
            		Scanner error = new Scanner(p.getErrorStream());
		        if (error.hasNext()) System.out.println("ERROR : " + error.nextLine());
            			error.close();
		} catch (Exception ex) {
        	    ex.printStackTrace();
		}
		p.waitFor();
		System.out.println("JAVA_HOME - " + System.getenv("JAVA_HOME"));
		System.out.println("HADOOP_COMMON_HOME - " +System.getenv("HADOOP_COMMON_HOME"));
		p = Runtime.getRuntime().exec(exec + " " + input + " " + output);
		error e = new error(p);
             	Thread e_t = new Thread(e);
             	e_t.start();
             	info i = new info(p);
             	Thread i_t = new Thread(i);
             	i_t.start();
		int exit = p.waitFor();
		System.out.println("Exit : "+ exit);
		//if (exit != 0) throw new Exception("Analysis could not be completed!!");
		System.out.println("Running... Done!!");
		return null;//new FileInputStream(LAST_JOB_OUTPUT);
		//	return new FileInputStream(new File("C:\\Users\\imajumde\\git\\EAC\\PluginManager\\src\\main\\resources\\analysis.out"));
	}
}

class error implements Runnable {
	Process  p = null;
	public error(Process  p) {
		this.p = p;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
            Scanner error = new Scanner(p.getErrorStream());
            if (error.hasNext()) System.out.println("ERROR : " + error.nextLine());
            error.close();
		} catch (Exception ex) {
            ex.printStackTrace();
		}
	}
	
}


class info implements Runnable {
	Process  p = null;
	public info(Process  p) {
		this.p = p;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
            Scanner info = new Scanner(p.getInputStream());
            if (info.hasNext()) System.out.println("ERROR : " + info.nextLine());
            info.close();
            //OutputStream stdout = p.getOutputStream();
		} catch (Exception ex) {
            ex.printStackTrace();
		}
	}
}

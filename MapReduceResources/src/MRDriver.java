import org.apache.hadoop.util.ProgramDriver;


public class MRDriver {

	public static void main(String argv[]){
	    int exitCode = -1;
	    ProgramDriver pgd = new ProgramDriver();
	    try {
	      pgd.addClass("errorcount", ErrorCount.class, 
	              "A map/reduce program that counts the word ERROR in the input files.");
	      pgd.addClass("ivspasset", IVSPAssetMR.class, 
	              "A map/reduce program that manipulates the IVSP assets data.");
	      pgd.addClass("ivspassetNew", IVSPAssetMRNew.class, 
	              "A map/reduce program that manipulates the IVSP assets data New.");
	      pgd.addClass("cpuUtilization", MemoryUtilization.class, 
	              "A map/reduce program that manipulates the CPU Utilization of a machine.");
	      exitCode = pgd.run(argv);
	    }
	    catch(Throwable e){
	      e.printStackTrace();
	    }	    
	    System.exit(exitCode);
	  }

}

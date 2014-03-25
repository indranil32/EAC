package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BigDataBackEnd {

	private String RESOURCE = "/root/MapReduceResource/jar/./run.sh";
	private String IVSP_COMMAND = " ivspasset /logs/IVSP/ ";
	private String MEM_COMMAND = " cpuUtilization /logs/CPUUsage/ ";
	private String LOG_COMMAND = " errorcount /logs/ConductorLogs/ ";
	private String OUTPUT_FILE = "/part-r-00000";
	private String HDFS_OUTPUT = "/output/";
	private String LOCAL_OUTPUT = "/root/output/";
	
	
	
	/**
	 * 			bigDataBO.getPieYTooltips()) + "]");
				bigDataBO.getPieTitles()) + "]" );
				bigDataBO.getPieData() + "" );
				bigDataBO.getLineTitles()) + "]");
				bigDataBO.getLineData() +"" );
				bigDataBO.getLineXMajorTick() +"" );			
				bigDataBO.getLineXMinorTick() +"" );
				bigDataBO.getLineYMajorTick() +"" );
				bigDataBO.getLineYMinorTick() +"");
				bigDataBO.getLineYMax() +"" );
				bigDataBO.getLineXYAxis()) + "]}");
	 * @return
	 * @throws Exception 
	 */
	
	
	public BigDataBO getIVSPData() throws Exception {
		IVSP_COMMAND = IVSP_COMMAND +  HDFS_OUTPUT+"IVSP"+Math.random();
		BigDataBO bo = new BigDataBO();
		System.out.println("getIVSPData " + IVSP_COMMAND);
		try {
			int exit = Runtime.getRuntime().exec(RESOURCE + " "+ IVSP_COMMAND).waitFor();
			if (exit != 0) throw new Exception("Exit status is not 0");
			Scanner scan = new Scanner(new File(LOCAL_OUTPUT+OUTPUT_FILE));
			while(scan.hasNextLine()) {
				String oneLine = scan.nextLine();
				System.out.println(oneLine);
				StringTokenizer tokens = new StringTokenizer(oneLine);
				while (tokens.hasMoreTokens()) {
					String title = tokens.nextToken();
					System.out.println("title: " + title);
					bo.getPieTitles().add(title);
					bo.getLineTitles().add(title);
					String data = tokens.nextToken();
					System.out.println("data :" +data);
					bo.getPieYTooltips().add(data);
					bo.getPieData().add(Double.parseDouble(data));
					List<Integer> lineData= new ArrayList<Integer>();
					lineData.add(Integer.parseInt(data));
					lineData.add(Integer.parseInt(data)+10);
					lineData.add(Integer.parseInt(data)+27);
					lineData.add(Integer.parseInt(data)+3);
					bo.getLineData().add(lineData);
				}
			}
			bo.getLineXYAxis().add("Month");
			bo.getLineXYAxis().add("No of views");
			bo.setLineXMinorTick(0.1);
			bo.setLineXMajorTick(1);
			bo.setLineYMajorTick(10);
			bo.setLineYMinorTick(1);
			bo.setLineYMax(100);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bo;
	}

	public BigDataBO getLOGData() throws Exception {
		LOG_COMMAND = LOG_COMMAND + HDFS_OUTPUT+"LOG"+Math.random();
		BigDataBO bo = new BigDataBO();
		System.out.println("getLOGData "  + " " + LOG_COMMAND);
		try {
			int exit = Runtime.getRuntime().exec(RESOURCE + " "+ LOG_COMMAND ).waitFor();
			if (exit != 0) throw new Exception("Exit status is not 0");
			Scanner scan = new Scanner(new File(LOCAL_OUTPUT+OUTPUT_FILE));
			while(scan.hasNextLine()) {
				String oneLine = scan.nextLine();
				StringTokenizer tokens = new StringTokenizer(oneLine);
				while (tokens.hasMoreTokens()) {
					String title = tokens.nextToken();
					bo.getPieTitles().add(title);
					bo.getLineTitles().add(title);
					String data = tokens.nextToken();
					bo.getPieYTooltips().add(data);
					bo.getPieData().add(Double.parseDouble(data));
					List<Integer> lineData= new ArrayList<Integer>();
					lineData.add(Integer.parseInt(data));
					lineData.add(Integer.parseInt(data)+100);
					lineData.add(Integer.parseInt(data)+477);
					lineData.add(Integer.parseInt(data)+613);
					bo.getLineData().add(lineData);
				}
			}
			bo.getLineXYAxis().add("Count");
			bo.getLineXYAxis().add("SYSTEM DIAGNOSTICS");
			bo.setLineXMinorTick(0.1);
			bo.setLineXMajorTick(1);
			bo.setLineYMajorTick(20000);
			bo.setLineYMinorTick(2000);
			bo.setLineYMax(300000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}

	public BigDataBO getMEMData() throws Exception {
		MEM_COMMAND = MEM_COMMAND + HDFS_OUTPUT+"MEM"+Math.random();
		BigDataBO bo = new BigDataBO();
		System.out.println("getMEMData " + MEM_COMMAND);
		List<Integer> lineDataswap = new ArrayList<Integer>();
		List<Integer> lineDatacach = new ArrayList<Integer>();
		List<Integer> lineDatafree = new ArrayList<Integer>();
		int count = 0;
		try {
			int exit = Runtime.getRuntime().exec(RESOURCE + " "+ MEM_COMMAND).waitFor();
			if (exit != 0) throw new Exception("Exit status is not 0");
			System.out.println("titles: SwapCached, Cached, MemFree");
			bo.getPieTitles().add("SwapCached");
			bo.getPieTitles().add("Cached");
			bo.getPieTitles().add("MemFree");
			bo.getLineTitles().add("SwapCached");
			bo.getLineTitles().add("Cached");
			bo.getLineTitles().add("MemFree");
			Scanner scan = new Scanner(new File(LOCAL_OUTPUT+OUTPUT_FILE));
			while(scan.hasNextLine()) {
				String oneLine = scan.nextLine();
				System.out.println(oneLine);
				StringTokenizer tokens = new StringTokenizer(oneLine);
				while (tokens.hasMoreTokens()) {
					tokens.nextToken(); // ignoring the 1st one
					String[] title = tokens.nextToken().split(":");
					Integer swap = Integer.parseInt(title[1]);
					Integer cach = Integer.parseInt(title[3]);
					Integer free = Integer.parseInt(title[5]);
					lineDataswap.add(swap);
					lineDatacach.add(cach);
					lineDatafree.add(free);
					System.out.println("data :" +swap + " " + cach + " " + free);
					if (count == 0) {
						bo.getPieYTooltips().add(title[1]);
						bo.getPieYTooltips().add(title[2]);
						bo.getPieYTooltips().add(title[5]);
						bo.getPieData().add(Double.parseDouble((title[1])));
						bo.getPieData().add(Double.parseDouble((title[3])));
						bo.getPieData().add(Double.parseDouble((title[5])));
						count ++;
					}
				}
			}
			bo.getLineData().add(lineDataswap);
			bo.getLineData().add(lineDatacach);
			bo.getLineData().add(lineDatafree);
			bo.getLineXYAxis().add("Month");
			bo.getLineXYAxis().add("Memory Used");
			bo.setLineXMinorTick(0.1);
			bo.setLineXMajorTick(1);
			bo.setLineYMajorTick(10);
			bo.setLineYMinorTick(1);
			bo.setLineYMax(100);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	
	

}

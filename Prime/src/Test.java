import com.cisco.analytics.eac.EACRestService;

public class Test {

	public static void main(String[] args) {
		EACRestService eac = new EACRestService();
		String json = (String) eac.run("CMD2K_REBOOTS", null).getEntity();
		System.out.println(json);
	}
}

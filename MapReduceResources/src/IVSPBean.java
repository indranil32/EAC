import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 */

/**
 * @author saashokk
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IVSPBean {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String toString() {
		return "IVSPBean [serverSessionId=" + serverSessionId
				+ ", operatingParameters=" + operatingParameters.toString() + "]";
	}

	private String serverSessionId;

	public String getServerSessionId() {
		return serverSessionId;
	}

	public void setServerSessionId(String serverSessionId) {
		this.serverSessionId = serverSessionId;
	}

	public OperatingParameters getOperatingParameters() {
		return operatingParameters;
	}

	public void setOperatingParameters(OperatingParameters operatingParameters) {
		this.operatingParameters = operatingParameters;
	}

	private OperatingParameters operatingParameters;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class OperatingParameters {

		@Override
		public String toString() {
			return "OperatingParameters [vod_assetTitle=" + vod_assetTitle
					+ "]";
		}

		private String vod_assetTitle;

		public String getVod_assetTitle() {
			return vod_assetTitle;
		}

		public void setVod_assetTitle(String vod_assetTitle) {
			this.vod_assetTitle = vod_assetTitle;
		}

	}

	public static final IVSPBean fromJsonDoc(String jsonDoc) throws IOException {
		JsonNode rootNode = mapper.readTree(jsonDoc);
		return mapper.treeToValue(rootNode, IVSPBean.class);
	}

}

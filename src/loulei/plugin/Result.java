package loulei.plugin;

import java.util.List;

public class Result {

	private String from;
	private String to;
	private List<Trans_result> trans_result;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Trans_result> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(List<Trans_result> trans_result) {
		this.trans_result = trans_result;
	}

	public static class Trans_result{
		private String src;
		private String dst;
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getDst() {
			return dst;
		}
		public void setDst(String dst) {
			this.dst = dst;
		}
		
		
	}
}

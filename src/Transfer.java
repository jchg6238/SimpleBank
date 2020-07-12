import java.util.Date;

public class Transfer {
	
	private String xname;
	private String yname;
	private int transmon;
	private Date date;

	Transfer(String xname, String yname, int transmon) {
		this.xname = xname;
		this.yname = yname;
		this.transmon = transmon;
		date = new Date();
	}

	public String getXname() {
		return xname;
	}

	public String getYname() {
		return yname;
	}

	public int getTransmon() {
		return transmon;
	}

	public Date getDate() {
		return date;
	}

	public void printTran() {
		System.out.println(xname + " 轉帳 " + transmon + "元" + " 給  " + yname + " 在  " + date);
	}
}

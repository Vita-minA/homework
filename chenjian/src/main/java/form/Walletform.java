package form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
*@author 作者：陈健
*@version 创建时间：2017年8月7日 下午7:30:28
*说明：
*/
public class Walletform {
	@NotNull
	@Size(min=2,max=8)
	private String username;
	@NotNull
	@Size(min=5,max=11)
	private String itcode;
	@NotNull
	@Min(100)
	@Max(100000)
	private int amount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getItcode() {
		return itcode;
	}
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}

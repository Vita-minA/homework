package form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
*@author ���ߣ��½�
*@version ����ʱ�䣺2017��8��7�� ����7:30:28
*˵����
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

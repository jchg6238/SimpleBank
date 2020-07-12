import java.io.*;

public class Account {
	
	protected String name;
	protected int accmoney;
	private int money;
	private int count = 0;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Transfer tran[] = new Transfer[100];

	public Account(String name, int accmoney) {
		this.name = name;
		this.accmoney = accmoney;
	}

	public Account() {
		
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public int getAccMoney() {
		return accmoney;
	}

	public int getCount() {
		return count;
	}

	public void save() {
		System.out.print("輸入存款金額 ");
		try {
			money = Integer.parseInt(br.readLine());
			System.out.println("存款金額為 " + money);
			accmoney += money;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void take() {
		boolean bool = true;
		while (bool) {
			System.out.print("輸入提款金額 ");
			try {
				money = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (money < accmoney) {
				accmoney -= money;
				System.out.println("提款金額為 " + money);
				bool = false;
			} else
				System.out.println("! 餘額不足 請重新輸入");
		}
	}

	public String toString() {
		return name + "帳戶餘額為 " + accmoney;
	}

	public void tran(Account transacc) {

		System.out.println("\n轉入帳戶為 " + transacc.getName());
		boolean bool = true;
		while (bool) {
			System.out.print("\n輸入轉帳金額 ");
			try {
				money = Integer.parseInt(br.readLine());

				if (money < accmoney) {
					System.out.println("******************\n轉入帳戶為 " + transacc.getName() + " \n金額為 " + money + " 元"
							+ "\n\n選擇 1確認 2取消轉帳\n******************");
					int sel;
					try {
						sel = Integer.parseInt(br.readLine());

						if (sel == 2)
							return;
						accmoney -= money;
						transacc.accmoney += money;
						tran[count] = new Transfer("我 ", transacc.getName(), money);
						count++;
						transacc.tran[transacc.count] = new Transfer(name, "我", money);
						transacc.count++;
						System.out.println("! 轉帳成功");
						bool = false;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else
					System.out.println("\n! 餘額不足 請重新輸入");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void printMon() {
		for (int i = 0; i < count; i++) {
			tran[i].printTran();
		}
	}
}

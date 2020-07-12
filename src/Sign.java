import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sign {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Transfer tran[] = new Transfer[100];

	public Sign() {

		Database connection = new Database();
		// 建立清單
		connection.create();

		System.out.print("\n請輸入欲設定幾組帳號資料 ");
		//設定預設資料
		int num;
		boolean bool = true;

		try {
			num = Integer.parseInt(br.readLine());
			Account[] acc = new Account[num];

			for (int i = 0; i < num; i++) {
				acc[i] = new Account();
				System.out.print("-\n請輸入第 " + (i + 1) + "組帳戶名稱 ");
				acc[i].name = br.readLine();
				System.out.print("請輸入第 " + (i + 1) + "組預設金額 ");
				acc[i].accmoney = Integer.parseInt(br.readLine());
				System.out.println("-");
			}

			for (int i = 0; i < acc.length; i++) {
				if (i == 0)
					System.out.println("\n資料儲存...");
				//插入資料
				connection.insertTable(acc[i].getName(), Integer.toString(acc[i].getAccMoney()));
				if (i == acc.length - 1)
					System.out.println("資料儲存完畢!");
			}
			//讀取資料
			connection.getData();

			while (bool) {
				System.out.println("==========================\n" + "\t歡迎使用ATM\n" + "===========================");
				for (int i = 0; i < acc.length; i++)
					System.out.println("(" + (i + 1) + ")" + acc[i].name);
				do {
					System.out.println("\n* 選擇使用者帳戶  輸入0結束(資料儲存) *");
					num = Integer.parseInt(br.readLine()) - 1;

					if (num == -1)
						bool = !bool;
					else if (num >= acc.length)
						System.out.println("! 輸入錯誤 請重新輸入");
				} while (num >= acc.length);

				while (num != -1) {
					System.out.println("\n目前用戶為 " + acc[num].getName());
					System.out.println("==================================================\n"
							+ "*選擇功能項目 1存款 2提款 3查詢餘額 4轉帳 5轉帳紀錄 0離開*\n"
							+ "==================================================");
					int sel = Integer.parseInt(br.readLine());

					switch (sel) {
					case 1:
						acc[num].save();//存款
						break;
					case 2:
						acc[num].take();//提款
						break;
					case 3:
						System.out.println(acc[num]);
						break;
					case 4:
						while (bool) {
							System.out.println("選擇轉入帳戶 ");
							for (int i = 0; i < acc.length; i++) {
								if (acc[i].equals(acc[num]))
									continue;
								System.out.println((i + 1) + "," + acc[i].name);
							}
							int transacc = Integer.parseInt(br.readLine()) - 1;

							if (transacc >= acc.length) {
								System.out.println("! 輸入錯誤 請重新輸入");
								continue;
							} else if (acc[num].getName().equals(acc[transacc].getName()))
								System.out.println("! 不能轉帳給自己,請重新選擇轉入帳戶\n");

							else {
								acc[num].tran(acc[transacc]);
								bool = false;
							}
						}
						break;
					case 5:
						acc[num].printMon();
						break;
					case 0:
						num = -1;
						break;
					default:
						System.out.println("! 輸入錯誤 請重新輸入");
					}
				}
			}
			System.out.println("謝謝光臨 !");

			connection.delete();
			
			for (int i = 0; i < acc.length; i++) {
				if (i == 0)
					System.out.println("\n資料儲存...");
				connection.insertTable(acc[i].getName(), Integer.toString(acc[i].getAccMoney()));
				if (i == acc.length - 1)
					System.out.println("資料儲存完畢!");
			}
			connection.getData();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package documentmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserUtility{
	public static final byte SIGNUP = 1;
	public static final byte LOGIN = 2;
	FileWriter csvFileWrite;
	FileReader csvFileRead;
	BufferedReader readCSV;
	private String UserName, EmailID, Password;
	static String mail = "";

	UserUtility() {
		try {
			csvFileWrite = new FileWriter("D://Detail.csv", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void createNewUser() {
		boolean flag=true;
		do {
			System.out.print("Enter the User Name :");
			UserName = Utility.getInstance().readStringInput();
			boolean isname = Utility.getInstance().nameValidate(UserName);
			if (isname)
				flag=false;
			else
				System.out.println("\u001B[31mEnter Valid UserName \nOnly allowed Alphabets and Space\u001B[0m");
		} while (flag);
		flag=true;
		do {
			System.out.print("Enter Email-ID :");
			EmailID = Utility.getInstance().readStringInput();
			boolean isMail = Utility.getInstance().mailValidate(EmailID);
			if (isMail) {
				try {
					csvFileRead = new FileReader("D://Detail.csv");
					readCSV = new BufferedReader(csvFileRead);
					try {
						String read = readCSV.readLine();
//						System.out.println(read);
						while (read != null) {
							String[] mails=read.split(",");
							if (mails[0].contains(EmailID) && mails[0].equalsIgnoreCase(EmailID)) {
								flag = false;
								break;
							}
							read = readCSV.readLine();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (flag)
					flag=false;
				else {
					System.out.println("This Mail ID already exists...");
					flag=true;
				}
			} else {
				System.out.println("Enter the Valid MailId ");
			}

		} while (flag);
		flag=true;
		do {
			System.out.print("Enter PassWord :");
			Password = Utility.getInstance().readStringInput();
			boolean ispassword = Utility.getInstance().passwordValidate(Password);
			if (ispassword)
				flag=false;
			else
				System.out.println(
						"\u001B[31mEnter valid PassWord...\nMinimum 8 Characters...\nOne UpperCase\nSpecial Characters...\u001B[0m");
		} while (flag);
		try {
			// csvFileWrite.append("Email").append(",").append("PassWord");
			// csvFileWrite.append("\n");
			// csvFileWrite.append(ADMIN_MAIL).append(",").append(ADMIN_PASSWORD);
			// csvFileWrite.append("\n");
			csvFileWrite.append(EmailID).append(",").append(Password);
			csvFileWrite.append("\n");
			csvFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String directory = "C:\\Users\\USER\\Documents\\jesi\\" + EmailID;
		File path = new File(directory);
		if (!(path.exists())) {
			path.mkdirs();
		}
		directory = "C:\\Users\\USER\\Documents\\jesi\\" + EmailID + "\\My Documents";
		path = new File(directory);
		if (!(path.exists())) {
			path.mkdirs();
		}
		directory = "C:\\Users\\USER\\Documents\\jesi\\" + EmailID + "\\Received From Others";
		path = new File(directory);
		if (!(path.exists())) {
			path.mkdirs();
		}
		System.out.println("\u001B[32mSuccesFully Created...\u001B[0m \nPlease Login ");
		login();
	}

	protected void login() {
		boolean flag = false;
		System.out.print("Enter Email-Id :");
		mail = Utility.getInstance().readStringInput();
		System.out.print("PassWord :");
		String pass = Utility.getInstance().readStringInput();
		try {
			csvFileRead = new FileReader("D://Detail.csv");
			readCSV = new BufferedReader(csvFileRead);
			try {
				String read = readCSV.readLine();
				while (read != null) {
					if (read.equalsIgnoreCase(mail + "," + pass)) {
						System.out.println("\u001B[32mLogin SuccessFully...\u001B[0m");
						flag = true;
					}
					read = readCSV.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (flag) {
			DocManagement document=new DocManagement();
			document.homePage();
		} else {
			boolean loop=true;
			do {
				System.out.println("\u001B[31mInvalid Email OR Password\u001B[0m \n1.SignUp \n2.Login Again");
				byte n = Utility.getInstance().readByteInput();
				if (n == SIGNUP) {
					createNewUser();
					loop=false;
				} else if (n == LOGIN) {
					login();
					loop=false;
				} else 
					System.out.println("\u001B[31mEnter Valid Input..\u001B[0m");
			} while (loop);
		}
	}
}
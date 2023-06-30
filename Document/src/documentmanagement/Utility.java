package documentmanagement;

import java.util.Scanner;

public class Utility {
	private static Utility util = null;
	Scanner scanner = new Scanner(System.in);
	
	public static Utility getInstance() {
		if (util == null)
			util = new Utility();
		return util;
	}

	public byte readByteInput() {
		byte input = 0;
		boolean flag=true;
		do {
			if (scanner.hasNextByte()) {
				input = scanner.nextByte();
				scanner.nextLine();
				flag=false;
			} else {
				System.out.println("\u001B[31mEnter Valid Input\u001B[0m");
				scanner.nextLine();
			}
		} while (flag);

		return input;
	}

	public String readStringInput() {
		String input="";
		boolean flag=true;
		do {
			if (scanner.hasNextLine()) {
				input = scanner.nextLine();
				flag=false;
			} else {
				System.out.println("\u001B[31mEnter Valid Input\u001B[0m");
			}
		} while (flag);

		return input;
	}

	protected boolean nameValidate(String UserName) {
		String nameregex = "[A-Za-z\s]{1,}";
		if (UserName.matches(nameregex))
			return true;
		else
			return false;
	}

	protected boolean mailValidate(String EmailID) {
		String emailregex = "^[A-Za-z0-9_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
		if (EmailID.matches(emailregex))
			return true;
		else
			return false;
	}

	protected boolean passwordValidate(String Password) {
		String passregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#%^&*.,?]).{8,16}$";
		if (Password.matches(passregex))
			return true;
		else
			return false;
	}
	protected boolean fileNameValidate(String FileName) {
		String fileregex="[-_.!@#$%^& A-Za-z0-9]+";
		if(FileName.matches(fileregex))
			return true;
		else
			return false;
	}
}
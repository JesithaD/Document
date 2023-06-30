package documentmanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Folder {
	public final static byte YES = 1;
	public final static byte NO = 2;
	public final static byte WRITE = 1;
	public final static byte READ = 2;
	public final static byte EXIT = 3;
	public final static byte TEXT = 1;
	public final static byte EXCEL = 2;
	public final static byte WORD = 3;
	String fileName, extension, Users, filePath;
	File file;

	public void createFolder(String mail) {
		Users = mail;
		// System.out.println("User "+Users);
		System.out.println("Enter the Folder Name:");
		String fold = Utility.getInstance().readStringInput();
		File file = new File("C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + fold);
		if (file.exists()) {
			System.out.println("This Folder is Already Exists...");
			createFolder(Users);
		} else
			file.mkdirs();
		boolean flag=true;
		do {
			System.out.println(
					"\u001B[32mFolder Created...\u001B[0m\nYou want Create New File In This Folder? \n1.Yes \n2.No");
			byte option = Utility.getInstance().readByteInput();
			if (option == 1) {
				createFile(Users, fold);
				flag=false;
			} else if (option == 2) {
				System.out.println("\u001B[34mOkay...\u001B[0m");
				DocManagement document=new DocManagement();
				document.homePage();
				flag=false;
			} else {
				System.out.println("\u001B[31mEnter the Correct Option\u001B[0m");
			}
		} while (flag);
	}

	public void createFile(String mail, String fold) {
		Users = mail;
		filePath = "C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\";
		System.out.println("Which type of File You Want...? \n1.Text File \n2.Excel File \n3.Word Document");
		byte choice = Utility.getInstance().readByteInput();
		if (choice == TEXT) {
			System.out.print("Enter the File Name:");
			fileName = Utility.getInstance().readStringInput();
			extension = fold + "\\" + fileName + ".txt";
			file = new File(filePath + extension);
			try {
				file.createNewFile();
				System.out.println("\u001B[32mFile SuccessFully Created...\u001B[0m");
			} catch (Exception e) {
				System.out.println(e);
			}
			fileInformation();
		} else if (choice == EXCEL) {
			System.out.print("Enter the File Name:");
			fileName = Utility.getInstance().readStringInput();
			extension = fold + "\\" + fileName + ".xlsx";
			file = new File(filePath + extension);
//			extension=user+"\\"+extension;
			try {
				file.createNewFile();
				System.out.println("\u001B[32mFile SuccessFully Created...\u001B[0m");
			} catch (Exception e) {
				System.out.println(e);
			}
			fileInformation();
		} else if (choice == WORD) {
			System.out.print("Enter the File Name:");
			fileName = Utility.getInstance().readStringInput();
			extension = fold + "\\" + fileName + ".docx";
			file = new File(filePath + extension);
			try {
				file.createNewFile();
				System.out.println("\u001B[32mFile SuccessFully Created...\u001B[0m");
			} catch (Exception e) {
				System.out.println(e);
			}
			fileInformation();
		} else {
			System.out.println("\u001B[31mEnter Valid Input...\u001B[0m");
			createFile(Users, fold);
		}
	}

	public void fileInformation() {
		DocManagement document=new DocManagement();
		ExistingFile existingfile=new ExistingFile();
		System.out.println();
		byte option = 0;
		System.out.println("From This File\n 1.Write \n 2.Read \n 3.Exit");
		option = Utility.getInstance().readByteInput();
		if (option == WRITE) {
			existingfile.write(filePath + extension);
			fileInformation();
		} else if (option == READ) {;
			existingfile.read(filePath + extension);
			fileInformation();
		}  else if (option == EXIT) {
			document.homePage();
		} else {
			System.err.println("Enter the Valid Input...");
			fileInformation();
		}
	}
	public void deleteFolder(String fileName) {
		DocManagement document=new DocManagement();
		byte input = 0;
		System.out.println("Are you Sure Delete This Folder..?\n1.Yes \n2.No");
		input = Utility.getInstance().readByteInput();
		if (input == YES) {
			try {
				Path folder = Paths.get(fileName);
				Files.walk(folder).sorted((a, b) -> b.compareTo(a)).forEach(path -> {
					try {
						Files.delete(path);
					} catch (IOException e) {
						System.out.println("\u001B[31mFailed To Deleted\u001B[0m");
					}
				});
				System.out.println("\u001B[32mSuccessFully Deleted...\u001B[0m");
			} catch (IOException e) {
				e.printStackTrace();
			}

			document.homePage();
		} else if (input == NO) {
			document.homePage();
		} else {
			System.err.println("Enter the valid input...");
			deleteFolder(fileName);
		}
	}
}
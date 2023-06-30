package documentmanagement;

import java.io.File;

public class FileCreation {
	public final static byte YES = 1;
	public final static byte NO = 2;
	public final static byte WRITE = 1;
	public final static byte READ = 2;
	public final static byte EXIT=3;
	String fileName, Users;
	String newFile, extension, writeFolder, readFolder, folder;
	File file;
	public void fileCreate(String user) {
		Users = user;
		boolean namecheck=true;
		System.out.println("Which type of File You Want...? \n1.Text File \n2.Excel File \n3.Word Document");
		byte n = Utility.getInstance().readByteInput();
		if (n == 1) {
			do {
			System.out.print("Enter the New File Name :");
			newFile = Utility.getInstance().readStringInput();
			boolean fileValidate=Utility.getInstance().fileNameValidate(newFile);
			if(fileValidate) 
				namecheck=false;
			else
				System.out.println("Please Enter the Valid FileName...");
			}while(namecheck);
			extension = newFile + ".txt";
			file = new File("C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + extension);
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.print("\u001B[32mFile SuccessFully Created...\u001B[0m");
			fileDetail(Users);
		} else if (n == 2) {
			do {
				System.out.print("Enter the New File Name :");
				newFile = Utility.getInstance().readStringInput();
				boolean fileValidate=Utility.getInstance().fileNameValidate(newFile);
				if(fileValidate) 
					namecheck=false;
				else
					System.out.println("Please Enter the Valid FileName...");
				}while(namecheck);
			extension = newFile + ".xlsx";
			file = new File("C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + extension);
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.print("\u001B[32mFile SuccessFully Created...\u001B[0m");
			fileDetail(Users);
		} else if (n == 3) {
			do {
				System.out.print("Enter the New File Name :");
				newFile = Utility.getInstance().readStringInput();
				boolean fileValidate=Utility.getInstance().fileNameValidate(newFile);
				if(fileValidate) 
					namecheck=false;
				else
					System.out.println("Please Enter the Valid FileName...");
				}while(namecheck);
			extension = newFile + ".docx";
			file = new File("C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + extension);
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.print("\u001B[32mFile SuccessFully Created...\u001B[0m");
			fileDetail(Users);
		}
	}

	public void fileDetail(String mail) {
		System.out.println();
		DocManagement document=new DocManagement();
		ExistingFile existingfile=new ExistingFile();
		byte option = 0;
			System.out.println("From This File\n1.Write \n2.Read \n3.Exit ");
			option=Utility.getInstance().readByteInput();

		if (option == WRITE) {
			String filePath="C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + extension;
			existingfile.write(filePath);
			fileDetail(Users);
		}
		else if (option == READ) {
			String filePath="C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\" + extension;
			existingfile.read(filePath);
			fileDetail(Users);
		}
		else if(option==EXIT)
			document.homePage();
		else {
			System.out.println("Enter Valid Input...");
			fileDetail(mail);
		}
	}
	public void delete(File fileName) {
		DocManagement document=new DocManagement();
		byte option;
		System.out.println("Are you Sure Delete This File..?\n1.Yes \n2.No");
		option=Utility.getInstance().readByteInput();
		if (option == YES) {
			fileName.delete();
			System.out.println("\u001B[32mSuccessFully Deleted...\u001B[0m");
			document.homePage();
		} else if(option==NO) {
			document.homePage();
		}else {
			System.out.println("Enter Valid Choice....");
			delete(fileName);
		}
	}
}
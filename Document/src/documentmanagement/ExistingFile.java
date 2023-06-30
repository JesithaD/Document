package documentmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class ExistingFile {
	String fileName, Users;
	String Folder = "";
	String filePath;
	File check;
	File[] files = null;

	public final static byte READ = 1;
	public final static byte WRITE = 2;
	public final static byte DOWNLOAD_FILE = 3;
	public final static byte FILE_TRANSFER = 4;
	public final static byte EXIT = 5;
	public final static byte OPEN = 1;
	public final static byte DELETE = 2;
	public final static byte YES = 1;
	public final static byte NO = 2;
	public final static byte MY_DOCUMENTS = 1;
	public final static byte RECEIVED_OTHERS = 2;
	public final static byte BACK = 3;

	public void exist(String mail) {
		DocManagement document = new DocManagement();
		Users = mail;
		byte chooseFolder;
		System.out.println("Choose the Folder \n1.My Documents \n2.Received From Others \n3.Back");
		chooseFolder = Utility.getInstance().readByteInput();
		if (chooseFolder == MY_DOCUMENTS) {
			filePath = "C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\My Documents\\";
			File directory = new File(filePath);
			if (directory.exists() && directory.isDirectory()) {
				files = directory.listFiles();
				if (files != null) {
					System.out.println("--------------------------");
					for (File file : files) {
						System.out.println("* " + file.getName());
					}
					System.out.println("--------------------------");
					openExistingFile();
				} else {
					System.out.println("Sorry You Have No files in this Folder");
					exist(Users);
				}
			}

		} else if (chooseFolder == RECEIVED_OTHERS) {
			filePath = "C:\\Users\\USER\\Documents\\jesi\\" + Users + "\\Received From Others\\";
			File directory = new File(filePath);
			if (directory.exists() && directory.isDirectory()) {
				files = directory.listFiles();
				if (files != null) {
					System.out.println("--------------------------");
					for (File file : files) {
						System.out.println("* " + file.getName());
					}
					System.out.println("--------------------------");
					openExistingFile();
				} else {
					document.homePage();
				}
			}

		} else if (chooseFolder == BACK) {
			document.homePage();
		} else {
			System.err.println("Enter Valid choice");
			exist(Users);
		}
	}

	public void folderDetails() {
		DocManagement document = new DocManagement();
		System.out.println(
				"Do you Want .... this File? \n1.Read \n2.Write \n3.Download \n4.File Transfer \n5.Exit");
		byte option = Utility.getInstance().readByteInput();
		if (option == READ) {
			System.out.println("\u001B[35mReading\u001B[0m");
			read(filePath + "\\" + fileName);
		} else if (option == WRITE) {
			write(filePath + "\\" + fileName);
		} else if (option == DOWNLOAD_FILE) {
			DownloadFile downloadFile = new DownloadFile();
//			System.out.println(filePath+"\\"+fileName);
			filePath = filePath + "\\" + fileName;
			System.out.println("\u001B[35mDownloading....\u001B[0m");
			downloadFile.download(filePath);
		}  else if (option == FILE_TRANSFER) {
			FileTransfer filetransfer = new FileTransfer();
			String folderFile = Folder + "\\" + fileName;
			filetransfer.receiverDetails(Users, folderFile);
		} else if (option == EXIT) {
			document.homePage();
		} else {
			System.out.println("Please Enter the Valid Input....");
			folderDetails();
		}
	}

	public void fileDetails() {
		DocManagement document = new DocManagement();
		System.out.println("Do you Want? \n1.Read \n2.Write \n3.Download \n4.File Transfer \n5.Exit ");
		byte choice = Utility.getInstance().readByteInput();
		if (choice == READ) {
			System.out.println("\u001B[35mReading....\u001B[0m");
			read(filePath+"\\"+fileName);
			fileDetails();
		} else if (choice == WRITE) {
			System.out.println("\u001B[35mWriting....\u001B[0m");
			write(filePath+"\\"+fileName);
			fileDetails();
		} else if (choice == DOWNLOAD_FILE) {
			DownloadFile downloadFile = new DownloadFile();
//			System.out.println(filePath+fileName);
			filePath = filePath + "\\" + fileName;
			System.out.println("\u001B[35mDownloading....\u001B[0m");
			downloadFile.download(filePath);
		} else if (choice == FILE_TRANSFER) {
			FileTransfer filetransfer = new FileTransfer();
			filetransfer.receiverDetails(Users, fileName);
		}  else if (choice == EXIT) {
			document.homePage();
		}else {
			System.out.println("Enter Valid Input....");
			fileDetails();
		}
	}

	public void read(String fileName) {
		try {
			Reader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			int f = br.read();
			while (f != -1) {
				System.out.print((char) f);
				f = br.read();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void write(String fileName) {
		try {
			Writer fw = new FileWriter(fileName, true);
			System.out.println("Write the Content in File..");
			String content = Utility.getInstance().readStringInput();
			fw.write(content);
			fw.write("\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void openExistingFile() {
		boolean flag = true;
		DocManagement document = new DocManagement();
		if (files.length <= 0) {
			System.out.println(
					"\u001B[34mSorry you have No file in this Folder... \nYou Create the Folder or File...\u001B[0m");
			document.homePage();
		} else {
			System.out.println(
					"If you any changes in the File...\n1.Open File Or Folder \n2.Delete File Or Folder \n3.Back");
			byte choice = Utility.getInstance().readByteInput();
			if (choice == OPEN) {
				flag = true;
				do {
					System.out.println("Enter the File (OR) Folder Name:");
					fileName = Utility.getInstance().readStringInput();
					check = new File(filePath + fileName);
					if (check.exists()) {
						String string = fileName.replace(".", "");
						if (fileName.equals(string)) {
							Folder = fileName;
							fileName = "";
						}
						flag = false;
					} else
						System.out.println("\u001B[33mPlease Enter the Correct File (OR) Folder Name....\u001B[0m");
				} while (flag);
				if (Folder != "") {
					filePath = filePath + Folder;
					File directory1 = new File(filePath);
					if (directory1.exists() && directory1.isDirectory()) {
						File[] files1 = directory1.listFiles();
						if (files1 != null) {
							System.out.println("--------------------------");
							for (File file1 : files1) {
								System.out.println("* " + file1.getName());
							}
							System.out.println("--------------------------");
						}
					}
					int option = 0;
					flag = true;
					do {
						System.out.println("If you want Create the new File in this Folder?\n1.Yes \n2.No");
						option = Utility.getInstance().readByteInput();
						if (option == YES) {
							Folder createFolder = new Folder();
							createFolder.createFile(Users, Folder);
							flag = false;
						} else if (option == NO) {
							boolean loop = true;
							do {
								System.out.println("Work in Exiting File Name:");
								fileName = Utility.getInstance().readStringInput();
								File file1 = new File(filePath + "\\" + fileName);
//						System.out.println(file1.toString());
								if (file1.exists()) {
									folderDetails();
									loop = false;
								} else
									System.out.println("\u001B[31mPlease Enter the Correct File Name...\u001B[0m");
							} while (loop);
							folderDetails();
							flag = false;
						} else {
							System.out.println("Enter Correct Input");
						}
					} while (flag);
					flag = true;
					do {
						System.out.println("Enter the File Name:");
						fileName = Utility.getInstance().readStringInput();
						File file = new File(filePath + Folder + "\\" + fileName);
						if (file.exists()) {
							folderDetails();
							flag = false;
						} else
							System.out.println("\u001B[31mPlease Enter the Correct File Name...\u001B[0m");
					} while (flag);
				} else {
					fileDetails();
				}
			} else if (choice == DELETE) {
				do {
					System.out.println("Enter the File (OR) Folder Name:");
					fileName = Utility.getInstance().readStringInput();
					check = new File(filePath + fileName);
					if (check.exists()) {
						String string = fileName.replace(".", "");
						if (fileName.equals(string)) {
							Folder = fileName;
							fileName = "";
						}
						flag = false;
					} else
						System.out.println("\u001B[33mPlease Enter the Correct File (OR) Folder Name....\u001B[0m");
				} while (flag);
				if(Folder !="") {
					Folder folder = new Folder();
					System.out.println(Folder);
					String path = filePath+"\\"+Folder;
					System.out.println(path);
					folder.deleteFolder(path);
				}
				else {
					FileCreation createFile = new FileCreation();
					File f1 = new File(filePath + fileName);
					createFile.delete(f1);
				}
			} 
			else if (choice == BACK) {
				document.homePage();
			}
		}
	}
}
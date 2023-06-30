package documentmanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileTransfer {
	String receiverMailID, senderMailID, fileName;
	Path receiverPath, senderPath;

	public void receiverDetails(String senderMail, String filename) {
		senderMailID = senderMail;
		fileName = filename;
		boolean flag=true;
		System.out.println("How many people to send this File..?:");
		byte numberOfReceivers = Utility.getInstance().readByteInput();
		byte i = 0;
		String receiversMail[] = new String[numberOfReceivers];
		System.out.println("Receiver Mail Id:");
		for (int j = 0; j < numberOfReceivers; j++) {
			receiversMail[j] = Utility.getInstance().readStringInput();
			flag=true;
			do {
			receiverMailID = receiversMail[j];
			boolean receiveMail = Utility.getInstance().mailValidate(receiverMailID);
			if (receiveMail)
				flag=false;
			else
				System.err.println("Enter the Correct Mail Id");
		} while (flag);
		boolean receiverID = false;
		File CSV = new File("D://Detail.csv");
		try {
			List<String> lines = Files.readAllLines(CSV.toPath());
			for (String l : lines) {
				String[] column = l.split(",");
//			    	System.out.println(column[i]);
				if (column[i].equalsIgnoreCase(receiverMailID)) {
					receiverID = true;
					break;
				} else {
					receiverID = false;
				}
			}
			if (receiverID) {
				fileTransfer();
			} else {
				System.out.println("The Receiver Mail is Not Found...Please Give Correct MailID");
				receiverDetails(senderMailID, fileName);
			}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		DocManagement document=new DocManagement();
		document.homePage();
	}
	public void fileTransfer() {
		File senderFile = new File("C:\\Users\\USER\\Documents\\jesi\\" + senderMailID + "\\My Documents\\" + fileName);
		File receiverFile = new File("C:\\Users\\USER\\Documents\\jesi\\" + receiverMailID + "\\Received From Others");
		receiverPath = receiverFile.toPath();
		senderPath = senderFile.toPath();
		try {
			Files.copy(senderPath, receiverPath.resolve(senderPath.getFileName()));
			System.out.println("\u001B[32m Transfer SuccessFully...\u001B[0m");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
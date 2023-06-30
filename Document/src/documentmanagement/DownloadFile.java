package documentmanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadFile {
	FileCreation createfile = new FileCreation();
	String extension, filePath;

	DownloadFile() {
		File downloadFile = new File("C:\\Users\\USER\\Documents\\jesi\\Download");
		downloadFile.mkdir();
	}

	public String download(String FileName) {
		DocManagement document=new DocManagement();
		extension = FileName;
		filePath = "C:\\Users\\USER\\Documents\\jesi\\Download";
		File file = new File(filePath);
		Path newOne = getFile().toPath();
		Path oldOne = file.toPath();
		try {
			Files.copy(newOne, oldOne.resolve(newOne.getFileName()));
			System.out.println("\u001B[32mDownload SuccessFully...\u001B[0m");
		} catch (IOException e) {
			System.out.println("No such this File");
		}
		document.homePage();

		return extension;
	}

	public File getFile() {
		File file = new File(extension);
		return file;
	}
}

//public String download(String user,String Folder,String File) {
//DocManagement document=new DocManagement();
//extension=user+"\\"+Folder+"\\"+File;
////System.out.println("Download File:"+extension);
//filePath="C:\\Users\\USER\\Documents\\jesi\\Download";
//	File file=new File(filePath);
//	Path newOne=getFile().toPath();
//	Path oldOne=file.toPath();
//	try {
//		Files.copy(newOne,oldOne.resolve(newOne.getFileName()));
//		System.out.println("\u001B[32mDownload SuccessFully...\u001B[0m");
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	document.details();
//	
//	return File;
//}
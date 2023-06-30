package documentmanagement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search {
	int count = 0;
	void wordSearching(String User) {
		DocManagement document=new DocManagement();
		String folderPath = "C:\\Users\\USER\\Documents\\jesi\\"+User;
		System.out.println("Enter the Searching word:");
		String searchWord = Utility.getInstance().readStringInput();
		List<String> filePaths = searchFiles(folderPath, searchWord);
		if (filePaths.isEmpty()) {
			System.out.println("No files found containing the search word.");
		} else {
			System.out.println("Files containing the search word:");
			for (String filePath : filePaths) {
				System.out.println(filePath);
			}
		}
		document.homePage();
	}

	public List<String> searchFiles(String folderPath, String searchWord) {
		List<String> filePaths = new ArrayList<>();
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			try {
				Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile).forEach(path -> {
					try {
						if (containsWord(path, searchWord)) {
							filePaths.add(path.toString());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid folder path.");
		}
		return filePaths;
	}

	public static boolean containsWord(Path filePath, String searchWord) throws IOException {
//		List<String> lines = Files.readAllLines(filePath);
//		for (String line : lines) {
//			if (line.contains(searchWord)) {
//				return true;
//			}
//		}
//		return false;
		 try {
		        List<String> lines = Files.readAllLines(filePath);
		        for (String line : lines) {
		            if (line.contains(searchWord)) {
		                return true;
		            }
		        }
		    } catch (MalformedInputException e) {
		        System.err.println("File " + filePath + " has unsupported encoding. Skipping...");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return false;

	}
}
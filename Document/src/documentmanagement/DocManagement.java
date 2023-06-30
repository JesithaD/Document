package documentmanagement;

public class DocManagement {
	public static final byte SIGNUP = 1;
	public static final byte LOGIN = 2;
	public static final byte EXIT = 3;
	public static final byte EXIST_FILE = 1;
	public static final byte CREATE_FILE = 3;
	public static final byte CREATE_FOLDER = 2;
	public static final byte SEARCH = 4;
	public static final byte LOGOUT = 5;

	public static void main(String[] args) {
		System.out.println("\u001B[35m***Document Management***\u001B[0m");
		UserUtility user = new UserUtility();
		boolean flag = true;
		do {
			System.out.println("1.SignUp \n2.LogIn \n3.Exit");
			byte option = Utility.getInstance().readByteInput();
			if (option == SIGNUP) {
				user.createNewUser();
				flag = false;
			} else if (option == LOGIN) {
				user.login();
				flag = false;
			} else if (option == EXIT) {
				System.exit(0);
				flag = false;
			} else
				System.out.println("\u001B[31mEnter Valid Input\u001B[0m");
		} while (flag);
	}
	public void homePage() {
		String Users = UserUtility.mail;
		boolean flag=true;
		do {
			System.out.println(
					"Enter Your Choice....\n1.Existing File\n2.Create Folder\n3.Create File " + "\n4.Search\n5.LogOut");
			byte select = Utility.getInstance().readByteInput();
			switch (select) {
			case EXIST_FILE:
				ExistingFile existfile = new ExistingFile();
				existfile.exist(Users);
				flag=false;
			case CREATE_FOLDER:
				Folder createfolder = new Folder();
				createfolder.createFolder(Users);
				flag=false;
			case CREATE_FILE:
				FileCreation createfile = new FileCreation();
				createfile.fileCreate(Users);
				flag=false;
			case SEARCH:
				Search search = new Search();
				search.wordSearching(Users);
				flag=false;
			case LOGOUT:
				DocManagement.main(null);
				flag=false;
			default:
				System.out.println("\u001B[31mEnter Valid Input..\u001B[0m");
			}
		} while (flag);
	}
}
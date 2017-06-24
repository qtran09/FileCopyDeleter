import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class FileDeleter {
	public static void main(String[] args) throws IOException{
		if(args.length != 2){
			help();
			return;
		}
		FileStat delete = new FileStat();
		switch(args[1]){
		case "deleteSub":
			delete.deleteDupeFiles(new File(args[0]));
			break;
		case "subdirectory":
			File[] fileArr = delete.subDirectory(new File(args[0]));
			System.out.println("ROOT DIRECTORY: " + args[0]);
			System.out.println("Name\tSize\tIs a file?");
			for(int i=0;i<fileArr.length;i++){
				File file = fileArr[i];
				System.out.println(file.getName() + "\t" + file.getTotalSpace() + "\t" + file.isFile());
			}
			break;
		case "deleteAllDupes":
			System.out.println("Are you sure? This could take awhile\nY/N");
			Scanner in2 = new Scanner(System.in);
			String all = in2.next();
			if(all.toLowerCase().equals("y")){
				delete.deleteAllDupes(new File(args[0]));
			}
			else if(all.toLowerCase().equals("n")){
				System.out.println("Mission abort, terminating program");
				return;
			}
			else{
				System.out.println("Invalid input, terminating program");
				return;
			}
			in2.close();
		default:
			help();
		}
	}
	private static void help(){
		System.out.println("Use the format as follows\nFiledeleter fileName command");
		System.out.println("List of commands:");
		for(int i=0;i<commands.length;i++) System.out.println(commands[i]);
	}
	
	private static String[] commands = {"deleteSub: Delete duplicate files in the root's subdirectory",
			"subdirectory: List files in the subdirectory, if able to"
			,"deleteAll: Delete's all duplicate files beginning from the root, branching to all other directories beneath. If a file is in directory A and in directory B, they will not be deleted from both."};
}

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
public class FileStat {
	public FileStat(){
	}
	public File[] subDirectory(File root){
		if(root.isDirectory())
			return root.listFiles();
		return null;
	}
	public void deleteDupeFiles(File root) throws IOException{
		File[] files = getFiles(root);
		for(int i=0;i<files.length-1;i++){
			if(!files[i].isFile()) continue;
			for(int j=i+1;j<files.length;j++){
				if(FileUtils.contentEquals(files[i], files[j])) {
					System.out.println("Deleted " + files[j].getName() + ", copy of " + files[i].getName());
					files[j].delete();
				}
			}
		}
	}
	private File[] getFiles(File root){
		ArrayList<File> temp = new ArrayList<File>();
		File[] files = subDirectory(root);
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()) temp.add(files[i]);
		}
		File[] toRet = new File[temp.size()];
		for(int j=0;j<toRet.length;j++) toRet[j] = temp.get(j);
		return  toRet;
	}
	private File[] getDirectories(File root){
		ArrayList<File> temp = new ArrayList<File>();
		File[] files = subDirectory(root);
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()) temp.add(files[i]);
		}
		File[] toRet = new File[temp.size()];
		for(int j=0;j<toRet.length;j++) toRet[j] = temp.get(j);
		return  toRet;
	}
	
	public void deleteAllDupes(File root) throws IOException{
		if(subDirectory(root).length == 0) return;
		System.out.println("Entering " + root.getName());
		deleteDupeFiles(root);		
		File[] dir = getDirectories(root);
		for(File recur : dir) deleteAllDupes(recur);
	}
}

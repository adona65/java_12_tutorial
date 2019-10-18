package tutorial_000.languageNewFeatures;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class _005_FilesMismatching {
	
	public static void main(String[] args) throws IOException {
		/*
		 * The utility class Files got a method mismatch(Path, Path) that compares the two specified files (designed by Path object) and returns the index of the first byte 
		 * where they differ, or -1 if they don’t.
		 */
		// Example with identical files content.
		long sameFiles = Files.mismatch(FileSystems.getDefault().getPath("resources", "same_1.txt"), FileSystems.getDefault().getPath("resources", "same_2.txt"));
		displayFilesSimilarities(sameFiles);
		
		System.out.println("=============================================");
		// Example with differents files content.
		long differentsFiles = Files.mismatch(FileSystems.getDefault().getPath("resources", "same_1.txt"), FileSystems.getDefault().getPath("resources", "different.txt"));
		displayFilesSimilarities(differentsFiles);
		
	}
	
	static void displayFilesSimilarities(long mismatchIndex ) {
		if (mismatchIndex == -1) {
		    System.out.println("Files are identical.");
		} else {
		    System.out.println("Files first difference is at index " + mismatchIndex);
		}
	}
}

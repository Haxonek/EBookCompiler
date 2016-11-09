import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class EBook {
	
	Scanner in = new Scanner(System.in);
	
	List<Section> chapters = new ArrayList<Section>();
	OverHead overHead;
	File book;
	
	public void main (String[] args) {
		
		System.out.println("Start of Ebook complimation.");
		
		// Read in static information
		System.out.print("Book Title: ");
		String title = in.nextLine();
		System.out.print("Author Name: ");
		String author = in.nextLine();
		
		overHead = new OverHead(title, author);
		
		// create a new section for each arg.  For now just use own text
		
		// add each to list of chapters
		
		// create ebook
		book = createEbook();
		
		// zip up ebook
		zipBook();
		
		// rename ebook file
		int PerLoc = book.getName().indexOf('.');
		
		// copies it to the new file name, minus old extention
		try {
			File newFile = new File(book.getParent(), book.getName().substring(0, PerLoc) + ".epub");
			Files.move(book.toPath(), newFile.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File createEbook() {

		try {
			// Create a folder OEPBS, META-INF, and file mimetype
			File OEPBS = new File("EPUB/EPUB");
			OEPBS.mkdir();

			File META_INF = new File("EPUB/META-INF");
			META_INF.mkdir();

			// (contents: "application/epub+zip")
			File mimetype = new File("EPUB/mimetype");

			// now we add the content:
			FileWriter f = new FileWriter(mimetype);
			f.write("application/epub+zip");
			f.close();
			
			// Now we add files to META_INF
			return overHead.makeContainer();

		} catch (Exception ie) {
			ie.printStackTrace();
			return null;
		}
	}
	
	public boolean zipBook() {
		return false;
	}
	
	public void setAuthor(String author) {
		overHead.setAuthor(author);
	}
	
	public void setTitle(String title) {
		overHead.setTitle(title);
	}
	
	public String getTitle() {
		return overHead.getTitle();
	}

	public String getAuthor() {
		return overHead.getAuthor();
	}
	
}

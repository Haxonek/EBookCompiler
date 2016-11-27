import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class EBook {
	
	List<String> fileList = new ArrayList<String>();
	Scanner in = new Scanner(System.in);
	OverHead overHead;
	
	public EBook() {
		this("","","");
	}
	
	public EBook(String title, String author, String epubName) {
		overHead = new OverHead(title, author, epubName);
	}
	
	public static void main(String args[]) {
		
		EBook eb = new EBook();
		
		System.out.println("Please enter some general information:");
		
		// Read in static information
		System.out.print("Book Title: ");
		String title = eb.in.nextLine();
		System.out.print("Author Name: ");
		String author = eb.in.nextLine();
		
		eb.overHead = new OverHead(title, author);
		Section sections = new Section();
		
		// create a new section for each arg.  For now just use own text
		
		// add each to list of chapters
		
		// create ebook
		eb.createEbook(eb.overHead);
//		eb.createSections(sections);
		
		// zip up ebook
//    	eb.generateFileList(new File("EPUB"));
//    	eb.zipEBook(eb.overHead.getEpubName() + ".epub");
		
		eb.in.close();
	}

	public void createEbook(OverHead oh) {

		// Set up initial folders and mimetype file
		try {
			// Create epub folder; will be book name
			File mainFolder = new File("EPUB");
			mainFolder.mkdir();
			
			// Create a folder OEPBS, META-INF, and file mimetype
			File OEPBS = new File("EPUB/OEPBS");
			OEPBS.mkdir();

			File META_INF = new File("EPUB/META-INF");
			META_INF.mkdir();

			// (contents: "application/epub+zip")
			File mimetype = new File("EPUB/mimetype");
			// now we add the content:
			FileWriter f = new FileWriter(mimetype);
			f.write("application/epub+zip");
			f.close();
			
			// create other files
			oh.makeOverhead(2);
			
			// create the three chapters; will automate later
			
			
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
		Section s = new Section();
		
		// Add each of the section(s)
		try {
			// WILL BE DIFFERENT LATER
			s.makeChapter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// zip up ebook and set file name
		generateFileList(new File("EPUB"));
    	zipEBook(overHead.getEpubName() + ".epub");
    	
	}
	
	public void createSections(Section s) {
		try {
			// WILL BE DIFFERENT LATER
			s.makeChapter();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    private void zipEBook(String zipFile){

    	byte[] buffer = new byte[1024];

    	System.out.println("Generating zip file");

    	try{

    		FileOutputStream fos = new FileOutputStream(zipFile);
    		ZipOutputStream zos = new ZipOutputStream(fos);

    		System.out.println("Output to Zip : " + zipFile);

    		for(String file : this.fileList){

    			System.out.println("File Added : " + file);
    			ZipEntry ze= new ZipEntry(file);
    			zos.putNextEntry(ze);

    			FileInputStream in =
    					new FileInputStream(File.separator + file);

    			int len;
    			while ((len = in.read(buffer)) > 0) {
    				zos.write(buffer, 0, len);
    			}

    			in.close();
    		}

    		zos.closeEntry();
    		//remember close it
    		zos.close();

    		System.out.println("Done");
    	}catch(IOException ex){
    		ex.printStackTrace();
    	}
    }

    /**
     * Traverse a directory and get all files,
     * and add the file into fileList
     * @param node file or directory
     */
    private void generateFileList(File node){
    	
    	//add file only
    	if(node.isFile()){
    		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
    	}

    	if(node.isDirectory()){
    		String[] subNote = node.list();
    		for(String filename : subNote){
    			generateFileList(new File(node, filename));
    		}
    	}

    }

    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file){
    	return file.substring(1, file.length());
    }

}

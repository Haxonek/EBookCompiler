import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class OverHead {

	// default epub name is equal to title
	private String title, author, epubName;
	
	/**
	 * Empty constructor, builds with everything null
	 */
	public OverHead() {
		this("","","");
	}

	/**
	 * Builds chapter with the filename set to be the same as the title
	 * regardless of whether the title has spaces in it.
	 * 
	 * @param title The chapter title and in this case the file name
	 * @param author The name of the author listed in the book
	 */
	public OverHead(String title, String author) {
		this(title, author, title);
	}
	
	/**
	 * Full constructor method setting the title, author name and the file name
	 * 
	 * @param title Name of the ebook
	 * @param author Name of the ebook author
	 * @param epubName Filename for the ebook
	 */
	public OverHead(String title, String author, String epubName) {
		this.title = title;
		this.author = author;
		this.epubName = title.trim();
	}
	
	/**
	 * This method runs all of the individual file creation methods and
	 * constructs the ebook using the set author and file name.
	 * 
	 * @param numSections The number of chapters being created in the book; should be
	 * represented with args.length + 1
	 */
	public void makeOverhead(int numSections) {
		try {
			makeOpf(numSections);
			makeCSS();
			makeTitlePage(numSections);
			makeToc(numSections);
			makeTox(numSections);
			makeContainer();
			makeCopyright();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeCSS() throws IOException {
		// Make CSS folder
		File CSS = new File("EPUB/OEPBS/CSS");
		CSS.mkdir();
		
		// make css template
		File css = new File("EPUB/OEPBS/CSS/template.css");
		FileWriter f = new FileWriter(css);
		f.write("h1, h2, h3, h4, h5 {\n\tcolor: #666;\n	font-family: sans-serif;\n	font-size: 2.2em;\n	text-align: right;\n}\n\nh6 {\n	color: #666;\n	font-family: sans-serif;\n	font-size: 2.2em;\n}\n\nh1 { font-size: 2.4em; }\nh2 { font-size: 2.2em; }\nh3 { font-size: 2em; }\nh4 { font-size: 1.4em; }\nh5 { font-size: 1.2em; }\nh6 { font-size: 1em; }\n\n\np {\n	color: #444;\n	font-family: serif;\n	font-size: 1.3em;\n}\n\na {\n	text-decoration: none;\n	color: #1EAEDB;\n}\n\nli {\n	list-style: upper-roman;\n	color: #444;\n	font-family: sans-serif;\n	font-size: 1.2em;\n	line-height: 1.4em;\n}\n\n.book_title {\n	border-bottom: .2em #666 solid;\n}");
		f.close();
		css.createNewFile();
	}
	
	private void makeOpf(int n) throws IOException {
		
		// generate the list of chapters in a string
		String manifest_links = "", spine_links = "\t\t<itemref idref=\"titlepage\" />\n\t\t<itemref idref=\"toc\" />\n";
		for (int i = 1; i <= n; ++i) {
			manifest_links += "\t\t<item id=\"chapter_" + i + "\" href=\"chapter_" + i + ".xhtml\" media-type=\"application/xhtml+xml\" />\n";
			spine_links += "\t\t<itemref idref=\"chapter_" + i + "\" />\n";
		}
		
		// create file and add chapters to string, then add to content.opf
		File content_opf = new File("EPUB/OEPBS/content.opf");
		FileWriter f = new FileWriter(content_opf);
		f.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<package xmlns=\"http://www.idpf.org/2007/opf\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" unique-identifier=\"db-id\" version=\"3.0\">\n\n\t<metadata>\n	    <dc:title id=\"t1\">" + title + "</dc:title>\n	    <dc:creator opf:role=\"aut\">" + author + "</dc:creator>\n	    <dc:identifier id=\"db-id\">isbn</dc:identifier>\n	    <meta property=\"dcterms:modified\">" + LocalDateTime.now() + "</meta>\n	    <dc:language>en</dc:language>\n	</metadata>\n\n	<manifest>\n	    <item id=\"toc\" properties=\"nav\" href=\"toc.xhtml\" media-type=\"application/xhtml+xml\" />\n	    <item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\" />\n	    <item id=\"template_css\" href=\"template.css\" media-type=\"text/css\" />\n\t\t<item id=\"titlepage\" href=\"titlepage.xhtml\" media-type=\"application/xhtml+xml\" />\n" + manifest_links + "	    <item id=\"copyright\" href=\"copyright.xhtml\" media-type=\"application/xhtml+xml\" />\n	</manifest>\n\n	<spine toc=\"ncx\">\n" + spine_links + "	    <itemref idref=\"copyright\" />\n	</spine>\n\n</package>");
		f.close();
		content_opf.createNewFile();
	}
	
	private void makeToc(int n) throws IOException {
		// creates the links and text in the table of contents
		String toc_links = "";
		for (int i = 1; i <= n; ++i) {
			toc_links += "\t\t\t<li><a href=\"chapter_" + i + ".xhtml\">Chapter " + i + "</a></li>\n";
		}
		
		File toc_xhtml = new File("EPUB/OEPBS/toc.xhtml");
		FileWriter f = new FileWriter(toc_xhtml);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n<title>toc.xhtml</title>\n<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n<body>\n    <nav id=\"toc\" epub:type=\"toc\">\n        <h1 class=\"frontmatter\">Table of Contents</h1>\n        <ol class=\"contents\">\n\t\t\t<li><a href=\"titlePage.xhtml\">Title page</a></li>\n" + toc_links + "\t\t\t<li><a href=\"copyright.xhtml\">Copyright</a></li>\n        </ol>\n    </nav>\n</body>\n</html>");
		f.close();
		toc_xhtml.createNewFile();
	}

	private void makeTox(int n) throws IOException {
		// creates the links and text in the table of contents
		
		String toc_tags = "";
		
		toc_tags += "<navMap>\n";
		toc_tags += "\t<navPoint id=\"titlepage\" playOrder=1>\n";
		toc_tags += "\t\t<navLabel><text>Title page</text></navLabel>\n";
		toc_tags += "\t\t<content src=\"titlepage.xhtml\" />\n";
		toc_tags += "\t</navPoint>\n";
		toc_tags += "</navMap>\n\n";
		
		for (int i = 2; i <= n+1; ++i) {
			toc_tags += "<navMap>\n";
			toc_tags += "\t<navPoint id=\"chapter_" + (i - 1) + "\" playOrder=" + i + ">\n";
			toc_tags += "\t\t<navLabel><text>Chapter " + (i - 1) + "</text></navLabel>\n";
			toc_tags += "\t\t<content src=\"chapter_" + (i - 1) + ".xhtml\" />\n";
			toc_tags += "\t</navPoint>\n";
			toc_tags += "</navMap>\n\n";
		}
		/* We're recreating this:
		<navMap>
		    <navPoint id="chapter_1" playOrder="1">
		        <navLabel><text>Chapter 1</text></navLabel>
		        <content src="chapter_1.xhtml" />
		    </navPoint>
		</navMap>
		*/
		
		File toc_ncx = new File("EPUB/OEPBS/toc.ncx");
		FileWriter f = new FileWriter(toc_ncx);
		f.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<ncx version=\"2005-1\" xml:lang=\"en\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">\n\n<head>\n    <meta name=\"dtb:uid\" content=\"isbn\"/>\n    <meta name=\"dtb:depth\" content=\"1\"/>\n</head>\n\n<docTitle>\n    <text>" + title + "</text>\n</docTitle>\n\n" + toc_tags + "<navMap>\n    <navPoint id=\"copyright\" playOrder=\"" + (n + 2) + "\">\n        <navLabel><text>Copyright</text></navLabel>\n        <content src=\"copyright.xhtml\" />\n    </navPoint>\n</navMap>\n\n</ncx>");
		f.close();
		toc_ncx.createNewFile();
	}
	
	/**
	 * Mat move this method to Section class, however we have more control over
	 * the copyright here so I may keep it in OverHead
	 * 
	 * @throws IOException Thrown if there's an error creating the file
	 */
	private void makeCopyright() throws IOException {
		File container = new File("EPUB/OEPBS/copyright.xhtml");
		FileWriter f = new FileWriter(container);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n\t<title>Copyright</title>\n	<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n<body>\n	<p>Copyright " + author + ", " + LocalDateTime.now().getYear() + "</p>\n</body>\n</html>\n");
		f.close();
	}
	
	/**
	 * This method should create the title page, which will display the title 
	 * and then the author's name, as well as the number of chapters
	 * 
	 * @throws IOException Thrown if there's an error creating the file
	 */
	private void makeTitlePage(int numsections) throws IOException {
		File container = new File("EPUB/OEPBS/titlepage.xhtml");
		FileWriter f = new FileWriter(container);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n\t<title>" + title + "</title>\n	<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n<body>\n	<h1 class=\"book_title\">" + title + "</h1>\n	<h3 class=\"author_name\">" + author + "</h3>\n	<h5 class=\"chapter_count\">" + numsections + " Chapters</h5>\n</body>\n</html>\n");
		f.close();
	}

	/**
	 * Creates the same container file for each 
	 * 
	 * @throws IOException Thrown if there's an error creating the file
	 */
	private void makeContainer() throws IOException {
		File container = new File("EPUB/META-INF/container.xml");
		FileWriter f = new FileWriter(container);
		// Content does NOT rely on chapters
		f.write("<?xml version=\"1.0\"?>\n<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n  <rootfiles>\n    <rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>\n  </rootfiles>\n</container>");
		f.close();
	}
	
	/**
	 * File sets the author; must be run BEFORE the makeOverhead method
	 * 
	 * @param author The name of the author that will be set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Returns the name of the author currently set in the method; not what was
	 * set when makeOverhead was ran
	 * 
	 * @return The string of the author set in the class
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * File sets the epub title; must be run BEFORE the makeOverhead method
	 * 
	 * @param author The name of the author that will be set
	 */
	public void setTitle(String title) {
		this.author = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setEpubName(String epubName) {
		this.epubName = epubName;
	}
	
	public String getEpubName() {
		return epubName;
	}
	
}

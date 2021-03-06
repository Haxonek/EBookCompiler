import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Section {

	/**
	 * Empty class constructor
	 */
	public Section() {}

	/**
	 * Takes in a list of file names (plain text; .txt) and re-adds them as
	 * .xhtml files, inputting the minimal required html for the project.
	 * 
	 * @param fn A list of plain text files containing the chapters.
	 * @throws IOException Likely thrown if you encounter an error opening a file
	 *  
	 * */
	public void makeChapters(String[] fn) throws IOException {
		File chpt;
		FileWriter f;
		Scanner out;
		String line;
		
		for (int i = 0; i < fn.length; i++) {
			// read in file from input
			out = new Scanner(new File(fn[i]));
			
			// create the new file & setup file
			chpt = new File("EPUB/OEPBS/chapter_" + (i + 1) + ".xhtml");
			f = new FileWriter(chpt);
			
			// Add file header (title)
			f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n<title>chapter_" + (i + 1) + ".xhtml</title>\n<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n\n<body>\n\n");
			f.write("\t<h1>Chapter " + (i + 1) + "</h1>\n\n");
			
			// add file, formatting as we progress
			while (out.hasNextLine()) {
				line = out.nextLine().trim();
				if (!line.equals("")) {
					f.write("\t<p>" + line + "</p>\n\n");
				} 
			}
			f.write("</body>\n</html>\n");
			out.close();
			f.close();
		}
	}
	
	public void exampleChapters() throws IOException {
		
		/**
		 * The way this will be set up is we'll pass a List of file names
		 * through, then we'll add each file name.  This list size will also 
		 * be passed to OverHead to form the TOC, since all the chapter names
		 * will follow a format.  Done in above method
		 * 
		 * */

		// Again we're just making three files; we'll update later
		File ch1 = new File("EPUB/OEPBS/chapter_1.xhtml");
		FileWriter f = new FileWriter(ch1);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n<title>chapter_1.xhtml</title>\n<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n\n<body>\n\n    <h1>Chapter 1</h1>\n\n    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id lectus dictum, lobortis urna a, luctus libero. Integer ultricies nisi nec nisi gravida, sit amet tempor diam posuere. Morbi consequat libero fringilla pellentesque venenatis. Donec ut porta metus. Etiam condimentum cursus elit pulvinar gravida. Nulla consequat interdum leo sed tincidunt. Suspendisse potenti. Integer pretium cursus libero, eu ornare erat dictum eu. Cras ultrices mi vel odio tempus fringilla. Nulla tristique nisi nisl, id scelerisque risus gravida a. Proin neque tellus, efficitur eu dictum hendrerit, lacinia vel neque. Proin augue risus, maximus ac eros consectetur, finibus mattis metus. Donec ut dictum diam. Vestibulum tempor orci id ultrices lacinia. Praesent bibendum, mi vitae euismod condimentum, eros enim tincidunt odio, id semper erat nisl vitae nisi. Nunc convallis mi sit amet velit venenatis vestibulum ac at mauris.</p>\n\n\t<p>Duis vestibulum elit non tortor luctus ultrices. Nulla facilisi. Aliquam rhoncus, est at elementum semper, tortor risus pretium diam, eget condimentum ipsum felis eget sapien. Nullam elit urna, lacinia a metus luctus, varius ultrices lectus. In pellentesque erat sit amet magna fringilla, in luctus lectus accumsan. Nulla tincidunt dignissim eleifend. Duis lacinia risus facilisis lacus tempor, et facilisis est ornare. Quisque vel sem congue, varius eros in, consequat purus. Nulla sodales id dui ut fermentum. Vestibulum sed justo sed sapien placerat congue. Nam in bibendum purus.</p>\n\n	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tincidunt, ex sed lobortis interdum, purus dui fermentum ex, non dictum ex quam eu nunc. Suspendisse potenti. Aenean ultricies commodo hendrerit. Etiam tristique sagittis tellus. Aliquam erat volutpat. Maecenas vitae tristique nulla. Sed vitae volutpat ex, et hendrerit ipsum. Nullam vehicula velit vitae vehicula maximus.</p>\n\n	<p>Praesent ut nisi pellentesque, cursus nulla ac, vulputate leo. Fusce ultricies, magna cursus vestibulum viverra, erat eros maximus ex, id faucibus risus augue in sapien. Quisque tortor augue, pulvinar ac felis at, bibendum sollicitudin massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mauris ipsum, sollicitudin facilisis velit non, tempus accumsan purus. Donec eget pharetra ipsum. Nullam vulputate malesuada ipsum sed mollis. Nulla porta elit nec egestas consequat. Fusce a purus viverra, ultrices sapien in, varius leo. Nam at tellus vel tortor pulvinar pulvinar sed sit amet ex. Quisque vestibulum placerat massa, ut porta eros blandit euismod. Nullam vitae nulla sed neque eleifend lobortis sed non lacus. Etiam vulputate mollis sapien sed ornare.</p>\n\n	<p>Nullam eros diam, hendrerit vel nibh in, malesuada aliquet massa. Nam blandit egestas massa, sit amet efficitur erat luctus vel. In eu leo at nibh egestas venenatis vel nec eros. Nulla bibendum sapien vel velit iaculis, in feugiat risus vestibulum. Suspendisse placerat laoreet eros, et ullamcorper est ornare eu. Praesent imperdiet lacus vel vehicula accumsan. Donec fringilla odio velit. Cras tempus est in lacus eleifend, cursus pellentesque lorem vehicula. Ut metus turpis, posuere eget imperdiet eget, bibendum in tortor. Mauris id dolor tellus. Suspendisse at nisi tellus. Duis lectus arcu, pulvinar et pretium in, tincidunt facilisis nulla. Nulla ullamcorper aliquam ullamcorper. Nunc ultricies nibh vitae urna hendrerit varius.</p>\n\n</body>\n</html> ");
		f.close();
		ch1.createNewFile();


		File ch2 = new File("EPUB/OEPBS/chapter_2.xhtml");
		f = new FileWriter(ch2);
		f.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\">\n<head>\n<title>chapter_2.xhtml</title>\n<link href=\"CSS/template.css\" rel=\"stylesheet\" type=\"text/css\" />\n</head>\n\n<body>\n\n    <h1>Chapter 2</h1>\n\n    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas id ex urna. Quisque at fringilla ex. Aliquam erat volutpat. Nullam ac dignissim eros, sed iaculis dolor. Nullam sit amet libero convallis, porttitor nisl sed, pulvinar dolor. Vivamus ornare libero ipsum, eget interdum lorem viverra sed. Praesent sollicitudin viverra mauris ut gravida. Cras nec lorem id velit malesuada auctor. Pellentesque dignissim tortor vel tempus mattis. Integer sapien tortor, dignissim non purus eget, laoreet convallis dolor. Morbi vitae ante non tortor pharetra rutrum. Fusce pretium sapien nulla, sed pretium tortor ultrices sit amet.</p>\n\n\t<p>Morbi sed euismod est, ut sodales erat. In hac habitasse platea dictumst. Mauris ipsum diam, venenatis vitae turpis sit amet, blandit consectetur arcu. Aliquam non aliquet augue, in pharetra velit. Quisque dapibus bibendum lorem sed rutrum. Maecenas blandit sit amet lorem tempus viverra. Nulla commodo, orci sed eleifend efficitur, felis est dapibus felis, non mollis nibh tellus quis nunc. Curabitur vulputate velit orci. Duis laoreet erat augue. Maecenas nisi enim, congue vitae accumsan quis, tincidunt quis velit. Morbi vel leo ultricies, bibendum nibh at, posuere massa. Integer iaculis scelerisque elit.</p>\n\n	<p>Cras id porttitor nulla, eget consequat dui. Praesent volutpat placerat lorem a semper. Nulla facilisi. Vestibulum at tincidunt velit, ac consectetur diam. Nunc non arcu eget orci bibendum fermentum sed eget sapien. Vivamus rutrum scelerisque consectetur. Mauris nec augue sed est dignissim sagittis. Praesent ac urna quis justo ornare convallis quis et justo. Proin nunc metus, malesuada a nibh sit amet, eleifend facilisis tortor.</p>\n\n	<p>Curabitur at consequat lorem, sollicitudin consequat elit. Morbi tempor non justo non hendrerit. Donec sit amet consectetur odio. Mauris cursus elit a vestibulum rutrum. Aliquam varius sit amet nulla vel lobortis. Curabitur dictum arcu velit, id feugiat turpis blandit a. Mauris euismod urna ac metus fringilla, sit amet vestibulum neque sagittis. Aenean et luctus nibh. Suspendisse sit amet scelerisque lacus, eget rutrum augue. Sed finibus id dolor vitae tempor. Duis convallis sit amet lacus eu porttitor. Mauris tempus, mi ut porta faucibus, ex enim mattis ante, et efficitur ipsum lectus at diam. Nunc ut ullamcorper odio. Nunc varius pulvinar elit nec faucibus. Donec nec diam tempus, congue lorem vel, elementum justo. Mauris auctor enim non nisi finibus placerat.</p>\n\n	<p>Fusce vel lobortis sem, id mollis dui. Fusce iaculis erat magna, quis dignissim sapien porttitor ultrices. Duis ornare ipsum neque, ut sodales nulla elementum vel. Ut quam nibh, laoreet in orci sit amet, tristique imperdiet arcu. Fusce vitae hendrerit metus, sit amet porta erat. Duis finibus egestas mauris, eu volutpat nisi tempus in. Sed porttitor leo massa, ut condimentum dui lacinia sed. Morbi varius, augue et dignissim venenatis, erat ligula luctus nibh, et vulputate quam arcu at quam. Praesent faucibus ex lorem, a volutpat quam varius eu. Fusce efficitur iaculis diam, non sagittis neque imperdiet volutpat. Ut in velit dignissim, malesuada nunc non, iaculis massa. In hac habitasse platea dictumst.</p>\n\n	<p>In tincidunt urna in est dapibus, non gravida orci lobortis. Pellentesque quis sem nec felis dictum sollicitudin a in magna. Integer tristique placerat est ut congue. Maecenas tincidunt purus vel purus convallis, id molestie enim tincidunt. Curabitur lobortis ac sapien sed accumsan. Quisque dapibus lacus lacus, nec rhoncus mauris viverra at. Integer leo magna, porta at euismod vel, placerat at lorem. Cras consequat sapien et scelerisque faucibus. Integer ultrices fermentum lacus, sed posuere nisl lobortis efficitur. Morbi aliquet lorem ut ante consequat consectetur. Quisque vel velit sed nisi tristique placerat quis in massa.</p>\n\n	<p>Cras in est sem. Aliquam feugiat odio lorem, vel tempor massa laoreet at. Fusce ac porttitor felis. Donec ultrices urna mi, vitae vulputate mi faucibus sed. Curabitur in dui diam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam eu finibus elit. Aenean laoreet pharetra erat, sit amet fermentum erat interdum sodales. Vestibulum ut lectus condimentum, vestibulum mi non, venenatis nunc. Fusce elementum arcu leo, ac rhoncus neque eleifend a.</p>\n\n	<p>Proin massa arcu, interdum quis nibh volutpat, mattis pharetra leo. Sed tempus elit ac nunc feugiat, vel pharetra lorem dapibus. Aenean gravida neque sed orci viverra sagittis. Nunc semper sed ipsum quis laoreet. Cras sodales lacinia ornare. Proin sed mauris tortor. Ut sagittis diam augue, nec porta ante vehicula id. Cras gravida magna sollicitudin felis consequat, gravida posuere diam pulvinar. Vestibulum aliquam justo luctus ultrices iaculis. Nam vel hendrerit libero.</p>\n\n	<p>Integer tincidunt facilisis dapibus. Integer viverra porta risus, a sagittis nulla congue sit amet. Cras placerat pulvinar pharetra. Nam malesuada tempor odio at suscipit. Morbi est mauris, facilisis vel dolor in, tincidunt rutrum erat. Phasellus quis ex feugiat, pharetra nisi eu, dapibus lacus. Sed tempus a felis id rhoncus. Pellentesque vel pellentesque lacus. Ut ut consectetur diam. Maecenas vitae rutrum erat, quis porta massa. Proin in tellus at metus tempus condimentum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam ac egestas est. Vivamus hendrerit tempor dui, eu vehicula elit. Quisque felis felis, faucibus sit amet ligula at, porta elementum metus. Suspendisse iaculis metus vitae enim tincidunt, quis fermentum leo cursus.</p>\n\n	<p>Proin a lobortis justo. Morbi metus ante, tempor id augue sit amet, finibus luctus nibh. Sed efficitur vehicula posuere. Fusce rutrum placerat auctor. Vestibulum ut sollicitudin purus. Praesent ullamcorper ex nisi, vitae finibus risus congue eu. Fusce volutpat efficitur commodo. Fusce pulvinar lobortis sodales. Proin eget eros ac augue pulvinar efficitur. Mauris at condimentum mi. Sed sit amet mi vel tortor interdum cursus.</p>\n\n</body>\n</html> ");
		f.close();
		ch2.createNewFile();

	}

}

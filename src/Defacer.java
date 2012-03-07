/*
The MIT License

Copyright (c) 2012 Ludwik Trammer <ludwik at trammer.pl>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

import java.io.*;

public class Defacer {
	/** 
	 * A recursive function that finds all files with .html and .htm 
	 * extension within a folder and its subfolders and runs
	 * Converter.convertFile() function on them.
	 * 
	 * @param file HTML file or folder with HTML files
	 * @param originals place for copies of original unmodified files 
	 */
	private static void convertHTMLFiles(File file, File originals) {
		
		if(file.isFile()) {
			System.out.println(file);
			file.renameTo(originals);
			Converter.convertFile(originals, file);
		} else if(file.isDirectory()) {	
			originals.mkdir();
			
			File[] files = file.listFiles(new FileFilter() {
				  public boolean accept(File f) {
					  return (f.isDirectory() && !f.getName().equals("defacer-originals"))
							  	|| f.getName().toLowerCase().endsWith(".html")
							  	|| f.getName().toLowerCase().endsWith(".htm");
				  }
			});
			
			for(File f : files) {
				convertHTMLFiles(f, new File(originals.getAbsolutePath() + File.separator + f.getName()));
			}
		}
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("You need to give a file or a directory as a parameter to the program.");
		}
		
		File file, originals;
		for(String arg : args) {
			file = new File(arg);
			if(!file.exists()) {
				System.err.println(file + " does not exist!");
			} else {
				if(file.isDirectory()) {
					originals = new File(file.getAbsoluteFile() + File.separator + "defacer-originals");
				} else {
					File orginals_dir = new File(file.getAbsoluteFile().getParent() + File.separator + "defacer-originals");
					orginals_dir.mkdir();
					originals = new File(orginals_dir.getAbsolutePath() + File.separator + file.getName());
				}
				convertHTMLFiles(file, originals);
			}
		}
	}

}

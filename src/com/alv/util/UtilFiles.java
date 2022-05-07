package com.alv.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileDeleteStrategy;

public class UtilFiles {

	private static final int BUFFER_SIZE = 4096;

	public static String getCurrentPath() {
		String path = System.getProperty("user.dir");
		return path;
	}
	public static boolean Delete(String path) {

		boolean eliminado = false;
		File archivo = new File(path);

		if (archivo.isFile()) {
			archivo.deleteOnExit();
		}

		return eliminado;
	}

	public static String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	public static void Save(String content, String path, boolean recreate) throws Exception {
		int lastIndexOf = path.lastIndexOf("\\") > path.lastIndexOf("/") ? path.lastIndexOf("\\") : path.lastIndexOf("/");
		if (lastIndexOf >= 0) {
			String toCreate = path.substring(0, lastIndexOf);

			File toCreateFile = new File(toCreate);
			if (!toCreateFile.exists()) {
				toCreateFile.mkdirs();
			}
		}

		File archivo = new File(path);
		if (recreate) {
			FileDeleteStrategy.FORCE.delete(archivo);
		}
		(new File(archivo.getParent())).mkdirs();
		Path iPath = Paths.get(path);
		try {// (BufferedWriter br = Files.newBufferedWriter(iPath, Charset.defaultCharset(),
				// (recreate ? StandardOpenOption.CREATE_NEW : StandardOpenOption.APPEND))) {

			//Writer fstream = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.ISO_8859_1);
			Writer fstream = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8);

			fstream.append(content);
			fstream.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public static void SaveBack(String content, String path, boolean recreate) {

		int lastIndexOf = path.lastIndexOf("\\") > path.lastIndexOf("/") ? path.lastIndexOf("\\") : path.lastIndexOf("/");
		if (lastIndexOf >= 0) {
			String toCreate = path.substring(0, lastIndexOf);

			File toCreateFile = new File(toCreate);
			if (!toCreateFile.exists()) {
				toCreateFile.mkdirs();
			}
		}

		// try {
		//
		// CharsetEncoder encoder = Charset.forName("UNICODE").newEncoder();
		// BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		// FileOutputStream(path),encoder));
		// out.write(content);
		// out.close();
		// } catch (Exception ex){
		// ex.printStackTrace();
		// }

		FileWriter fichero = null;
		PrintWriter pw = null;
		if (recreate) {
			if ((new File(path)).exists()) {
				(new File(path)).delete();
			}
		}

		try {

			fichero = new FileWriter(path);
			pw = new PrintWriter(fichero);

			pw.println(content);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static String load(String path) {
		return load(path, null);
	}

	public static String load(String path, String textCode) {

		BufferedReader br = null;
		StringBuilder cons = new StringBuilder();

		try {
			if (textCode == null || textCode.equals("")) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			} else {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(path), textCode));
			}

			String linea;
			while ((linea = br.readLine()) != null) {
				cons.append(linea + "\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cons.toString();
	}

	public static String loadPlantilla(String path) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuilder cons = new StringBuilder();

		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.indexOf("--//") < 0) {
					cons.append(linea + "\r\n");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cons.toString();
	}

	public static void FolderCopy(String sourceFile, String destinationFolder) {
		File f1 = new File(sourceFile);
		File f2 = new File(destinationFolder);

		if (!f2.exists()) {
			f2.mkdirs();
		}

		if (f1.isDirectory()) {
			for (File item : f1.listFiles()) {
				if (!"dist".equals(f1.getName())) {
					FolderCopy(item.getAbsolutePath(), destinationFolder + "\\" + f1.getName());
					/*
					 * Thread t1 = new Thread(new Runnable() { public void run() {
					 * FolderCopy(item.getAbsolutePath(), destinationFolder+"\\"+f1.getName()); }
					 * }); t1.start();
					 */
				} else {
					FolderCopy(item.getAbsolutePath(), destinationFolder);
					/*
					 * Thread t1 = new Thread(new Runnable() { public void run() {
					 * FolderCopy(item.getAbsolutePath(), destinationFolder); } }); t1.start();
					 */

				}
			}
		} else {
			FileCopy(f1.getAbsolutePath(), destinationFolder + "\\" + f1.getName());
			/*
			 * Thread t1 = new Thread(new Runnable() { public void run() { FileCopy(
			 * f1.getAbsolutePath() , destinationFolder + "\\" + f1.getName()); } });
			 * t1.start();
			 */
		}
	}

	public static void FileCopy(String sourceFile, String destinationFile) {
		// System.out.println("Desde: " + sourceFile);
		// System.out.println("Hacia: " + destinationFile);

		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while ((c = in.read()) != -1)
				out.write(c);

			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}

	public static void EjecutarComandoBATCH(String comando) {
		try {
			String linea;
			Process p = Runtime.getRuntime().exec(comando);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((linea = input.readLine()) != null) {
				System.out.println(linea);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public static String GetTokenToday() {

		StringBuilder cons = new StringBuilder();

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int aux = calendar.get(Calendar.YEAR);
		cons.append(aux);
		aux = calendar.get(Calendar.MONTH);
		cons.append(aux < 10 ? "0" + aux : aux);
		aux = calendar.get(Calendar.DAY_OF_MONTH);
		cons.append(aux < 10 ? "0" + aux : aux);
		aux = calendar.get(Calendar.HOUR_OF_DAY);
		cons.append(aux < 10 ? "0" + aux : aux);
		aux = calendar.get(Calendar.MINUTE);
		cons.append(aux < 10 ? "0" + aux : aux);
		aux = calendar.get(Calendar.SECOND);
		cons.append(aux < 10 ? "0" + aux : aux);
		return cons.toString();
	}

	private static void zipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {
		zos.putNextEntry(new ZipEntry(file.getName()));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		long bytesRead = 0;
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = bis.read(bytesIn)) != -1) {
			zos.write(bytesIn, 0, read);
			bytesRead += read;
		}
		zos.closeEntry();
	}

	private static void zipDirectory(File folder, String parentFolder, ZipOutputStream zos) throws FileNotFoundException, IOException {
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				zipDirectory(file, parentFolder + "/" + file.getName(), zos);
				continue;
			}
			zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			long bytesRead = 0;
			byte[] bytesIn = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = bis.read(bytesIn)) != -1) {
				zos.write(bytesIn, 0, read);
				bytesRead += read;
			}
			zos.closeEntry();
		}

	}

	public static void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException, IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
		for (File file : listFiles) {
			if (file.isDirectory()) {
				zipDirectory(file, file.getName(), zos);
			} else {
				zipFile(file, zos);
			}
		}
		zos.flush();
		zos.close();
	}
	
	public static void zipFullPath(List<File> filesToZip,String path, String destZipFile) throws FileNotFoundException, IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
		for (File file : filesToZip) {			
			zos.putNextEntry(new ZipEntry(file.getAbsolutePath().replace(path+"\\", "")));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			long bytesRead = 0;
			byte[] bytesIn = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = bis.read(bytesIn)) != -1) {
				zos.write(bytesIn, 0, read);
				bytesRead += read;
			}
			zos.closeEntry();
		}
		zos.flush();
		zos.close();

	}
	
	
	public static void zip(String[] files, String destZipFile) throws FileNotFoundException, IOException {
        List<File> listFiles = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            listFiles.add(new File(files[i]));
        }
        zip(listFiles, destZipFile);
    }
	
	
	public static void DeleteFolder(String folderPath) {
		File folder = new File(folderPath);
		folder.delete();		
		
	}
	
	public static void cleanFolder(String path) {
		List<File> folders = new ArrayList<File>();
		List<File> files = new ArrayList<File>();
		recursively(new File(path) ,folders, files);
		for (File file : files) {
			file.delete();
		}
		for(int i = 0 ; i < folders.size() ; i++) {
			for(File folder : folders) {
				folder.delete();
			}
		}
		
	}
	
	public static void recursively(File current, List<File> folders,List<File> files) {
		
		for (File file : current.listFiles()) {
			if(file.isDirectory()) {
				folders.add(file);
				recursively(file, folders, files);
			} else {
				files.add(file);
			}
		}
		
	}
	
	public static void setNewModifiedDate(File file, Date newLastModified) {
        file.setLastModified(newLastModified.getTime());
	}
	
	public static boolean isExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	
	public static List<File> deepLoadFiles(String base) {
		List<File> files = new ArrayList<File>();
		File[] root = new File(base).listFiles();
		
		for (File file : root) {
			if(file.isFile()) {
				files.add(file);
			} else {
				files.addAll(deepLoadFiles(file.getAbsolutePath()));
			}
		}
		
		return files;
	}
	

}

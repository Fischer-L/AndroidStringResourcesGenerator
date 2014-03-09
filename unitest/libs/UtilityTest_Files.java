package libs;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import unitestLibs.Constants;

public class UtilityTest_Files {
	
	@Test
	public void testAccesExistingFile() {
		this.testAccesExistingFile_S1_OpenFile();
		this.testAccesExistingFile_S2_readFileAll();
	}
	
	@Test
	public void testAccesNewFile() {
		this.testAccesNewFile_S1_WriteFile();
		this.testAccesNewFile_S2_readFileAll();
		this.testAccesNewFile_S3_deleteAll();
	}
	
	@Test
	public void testReadFileAll_ReadDirCase() {
		assertEquals(null, Utility.Files.readFileAll(Constants.TEST_RES_ROOT));
	}
	
	@Test
	public void testReadFileAll_ReadNothingCase() {
		assertEquals(null, Utility.Files.readFileAll(this.newFilesRoot));
	}
	
	@Ignore
	public void testBufferFileWriter() {
		// Testing this is equal to test the Java API, so not doing the redundant test again.
	}
	
	@Ignore
	public void testBufferFileReader() {
		// Testing this is equal to test the Java API, so not doing the redundant test again.		
	}
	
	@Ignore
	public void testDeleteAll_Type2() {
		// Too simple to test.
	}
	
	
	/*
	 * Assistive fields
	 **********/
	private String existingFilePath = Constants.TEST_RES_JSON;
	private String text = "This is a unit test on Uitility.Files class.";
	private String newFilesRoot = Constants.RES_ROOT;
	private String[] newFilesPaths = Constants.RES_PATHS;
	
	/*
	 * Assistive methods
	 **********/

	public void testAccesExistingFile_S1_OpenFile() {
		assertTrue(null != Utility.Files.openFile(this.existingFilePath, true, false, false, false));
	}
	
	private void testAccesExistingFile_S2_readFileAll() {
		String reads = Utility.Files.readFileAll(Utility.Files.openFile(this.existingFilePath, true, false, false, false));
		assertEquals(Constants.TEST_RES_JSON_CONTENT, reads);
	}
	
	private void testAccesNewFile_S1_WriteFile() {
		BufferedWriter bw;
		for (String path : this.newFilesPaths) {
			bw = Utility.Files.bufferFileWriter(Utility.Files.openFile(path, true, true, false, false));
			try {
				bw.write(this.text);
				bw.close();
				assertTrue(true);
			} catch (IOException e) {e.printStackTrace();
				assertTrue(false);
			}
		}
	}	

	private void testAccesNewFile_S2_readFileAll() {
		File f;
		for (String path : this.newFilesPaths) {
			f = Utility.Files.openFile(path, true, true, false, false);
			Assert.assertEquals(this.text, Utility.Files.readFileAll(f));
		}		
	}	

	private void testAccesNewFile_S3_deleteAll() {
		File f = new File(this.newFilesRoot);
		Utility.Files.deleteAll(f);
		assertTrue(!f.exists());		
	}
}

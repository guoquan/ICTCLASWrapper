/**
 * 
 */
package org.milab.dataMining.ICTCLAS.ICTCLAS50.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.milab.dataMining.ICTCLAS.ICTCLASResult;
import org.milab.dataMining.ICTCLAS.ICTCLASWrapper;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.ICTCLAS50Impl;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.WordResultHelper;

/**
 * @author Guoquan
 *
 */
public class TestICTCLAS50Impl {
	private static ICTCLASWrapper wrapper;
	public static final String TESTSOURCE = "./test_file.txt";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ICTCLAS50Impl instance = new ICTCLAS50Impl();
		instance.setDir("./config/");
		instance.setHelper(new WordResultHelper());
		wrapper = instance;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.milab.dataMining.ICTCLAS.ICTCLAS50.ICTCLAS50Impl#split(java.lang.String)}.
	 */
	@Test
	public void testSplit() {
		try {
			File sourceFile = new File(TESTSOURCE);
			FileInputStream fis = new FileInputStream(sourceFile);
			byte[] buffer = new byte[(int) sourceFile.length()];
			fis.read(buffer);
			fis.close();
			
			List<ICTCLASResult> results = wrapper.split(new String(buffer));
			assertNotNull(results);
			assertTrue(!results.isEmpty());
			for(ICTCLASResult result : results)
			{
				assertNotNull(results);
				System.out.println(result);
			}
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

}

package org.milab.dataMining.ICTCLAS.ICTCLAS50;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.milab.dataMining.ICTCLAS.ICTCLASResult;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.util.Converter;

/**
 * Helper for word result. Add original word string to normal result.
 * @author Guoquan
 *
 */
public class WordResultHelper extends ResultHelper {
	/**
	 * Get a list of result with original word.
	 */
	public List<ICTCLASResult> bytesToResults(ICTCLAS50Impl wrapper, String source, byte[] nativeBytes) throws IllegalArgumentException
	{
		// check size of input
		if (0 != nativeBytes.length % RESULT_SIZE)
			throw new IllegalArgumentException("Wrong result package size.");
		
		// get charset information from wrapper instance
		Charset charset = CHARSETS[wrapper.getCodeType().ordinal()];
		
		// get original bytes with wrapper coding
		byte[] sourceByte = source.getBytes(charset);
		
		// initial a list
		List<ICTCLASResult> results = new ArrayList<ICTCLASResult>(nativeBytes.length / RESULT_SIZE);

		// loop for extracting each result
		for (int i = 0; i < nativeBytes.length; i += RESULT_SIZE) {
			// get normal result
			Result result = bytesToResult(Arrays.copyOfRange(nativeBytes, i, i + RESULT_SIZE));
			
			// get bytes of original word
			byte[] w = Arrays.copyOfRange(sourceByte, result.getByteStart(), result.getByteStart() + result.getByteLength());
			
			// construct result with original word
			WordResult wr = new WordResult(result, Converter.bytesToString(w, wrapper.getCodeType()));
			
			// add to list
			results.add(wr);
		}
		
		// return list
		return results;
	}
}

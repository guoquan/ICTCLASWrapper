package org.milab.dataMining.ICTCLAS.ICTCLAS50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.milab.dataMining.ICTCLAS.ICTCLASResult;
import org.milab.dataMining.ICTCLAS.ICTCLAS50.util.Converter;

/**
 * Helper class for generating result object from bytes result.
 * @author Guoquan
 *
 */
public class ResultHelper implements Constants {
	/**
	 * Get a result object from {@link Constants#RESULT_SIZE} bytes.
	 * @param singleResultBytes 
	 * bytes containing single result.
	 * @return result object with all information from parameter bytes.
	 * @throws IllegalArgumentException
	 * if length of parameter byte array is not {@link Constants#RESULT_SIZE}.
	 */
	public Result bytesToResult(byte[] singleResultBytes) throws IllegalArgumentException {
		// check size of input
		if (RESULT_SIZE != singleResultBytes.length)
			throw new IllegalArgumentException("Single result byte with wrong size.");
		
		int i = 0;
		
		// get byte-wise start position in source sentence of the word
		byte a[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int start = Converter.reversedBytesToInt(a);
		
		// get byte-wise length of word
		byte b[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int length = Converter.reversedBytesToInt(b);
		
		// get pos of word
		byte sall[] = Arrays.copyOfRange(singleResultBytes, i, i += POS_SIZE);
		String sPOS = Converter.systemBytesToString(sall).trim();
		
		// get pos id of word
		byte c[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int iPOS = Converter.reversedBytesToInt(c);
		
		// get word id
		byte j[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int word_ID = Converter.reversedBytesToInt(j);

		// check if the word is from user dictionary
		byte k[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int word_type = Converter.reversedBytesToInt(k);
		
		// get weight of the word
		byte w[] = Arrays.copyOfRange(singleResultBytes, i, i += 4);
		int weight = Converter.reversedBytesToInt(w);

		// construct a result object with above information
		return new Result(start, length, sPOS, iPOS, word_ID, word_type > 0, weight);
	}

	/**
	 * Get a list of result from byte array, whose size should be integral times of {@link Constants#RESULT_SIZE}.
	 * @param wrapper
	 * wrapper instance generating the byte array.
	 * @param source
	 * source string to be split.
	 * @param nativeBytes
	 * result by ICTCLAS50, in byte array, whose size should be integral times of {@link Constants#RESULT_SIZE}.
	 * @return
	 * a list of results from bytes result.
	 * @throws IllegalArgumentException
	 * if size of byte array is not integral times of {@link Constants#RESULT_SIZE}.
	 */
	public List<ICTCLASResult> bytesToResults(ICTCLAS50Impl wrapper, String source, byte[] nativeBytes) throws IllegalArgumentException
	{
		// check size of input
		if (0 != nativeBytes.length % RESULT_SIZE)
			throw new IllegalArgumentException("Wrong result package size.");
		
		// initialize a list
		List<ICTCLASResult> results = new ArrayList<ICTCLASResult>(nativeBytes.length / RESULT_SIZE);
		
		// loop for extracting each result
		for (int i = 0; i < nativeBytes.length;)
			results.add(bytesToResult(Arrays.copyOfRange(nativeBytes, i, i += RESULT_SIZE)));
		
		// return the list
		return results;
	}

}

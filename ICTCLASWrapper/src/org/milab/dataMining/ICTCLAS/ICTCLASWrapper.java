package org.milab.dataMining.ICTCLAS;

import java.io.IOException;
import java.util.List;

/**
 * The Wrapper interface. Wrapping implementation should implement this interface. 
 * @author Guoquan
 *
 */
public interface ICTCLASWrapper {
	/**
	 * The split method of the wrapper.
	 * @param source
	 * the source string to be split.
	 * @return the splitting result as a list of result objects.
	 * @throws IOException
	 * if IO error occurred
	 * @see ICTCLASResult
	 */
	public List<ICTCLASResult> split(String source) throws IOException;
}

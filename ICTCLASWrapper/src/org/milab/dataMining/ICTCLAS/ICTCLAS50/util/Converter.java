package org.milab.dataMining.ICTCLAS.ICTCLAS50.util;

import org.milab.dataMining.ICTCLAS.ICTCLAS50.Constants;

/**
 * Converter utility for the project. 
 * @author Guoquan
 *
 */
public class Converter implements Constants{
	/**
	 * Convert string to bytes for ICTCLAS50 internal use.
	 * @param input
	 * string to be converted.
	 * @return
	 * bytes of the string in ICTCLAS50 internal code type.
	 * @see {@link Converter#stringToBytes(String, CodeType)}, {@link Converter#systemBytesToString(byte[])}
	 */
	public static byte[] stringToSystemBytes(String input) {
		return stringToBytes(input, CodeType.SYSTEM);
	}
	
	/**
	 * Convert string to bytes in system (not ICTCLAS50) default code type.
	 * @param input
	 * string to be converted.
	 * @return
	 * bytes of the string in system default code type.
	 * @see {@link Converter#stringToBytes(String, CodeType)}
	 */
	public static byte[] stringToBytes(String input) {
		return stringToBytes(input, CodeType.DEFAULT);
	}
	
	/**
	 * Convert string to bytes in specific code type.
	 * @param input
	 * string to be converted.
	 * @param codeType
	 * code type to be used.
	 * @return
	 * bytes of the string in specific code type.
	 * @see {@link Converter#stringToBytes(String)}, {@link Converter#stringToSystemBytes(String)}
	 */
	public static byte[] stringToBytes(String input, CodeType codeType) {
		return input.getBytes(CHARSETS[codeType.ordinal()]);
	}

	/**
	 * Convert bytes in ICTCLAS50 code type to string.
	 * @param input
	 * bytes to be converted.
	 * @return
	 * string from input byte in ICTCLAS50 code type.
	 * @see {@link Converter#bytesToString(byte[], CodeType)}, {@link Converter#stringToSystemBytes(String)}
	 */
	public static String systemBytesToString(byte[] input) {
		return bytesToString(input, CodeType.SYSTEM);
	}
	
	/**
	 * Convert bytes in system (not ICTCLAS50) default code type to string.
	 * @param input
	 * bytes to be converted.
	 * @return
	 * string from input byte in system default code type.
	 * @see {@link Converter#bytesToString(byte[], CodeType)}
	 */
	public static String bytesToString(byte[] input) {
		return bytesToString(input, CodeType.DEFAULT);
	}
	
	/**
	 * Convert bytes in specific code type to string.
	 * @param input
	 * bytes to be converted.
	 * @param codeType
	 * code type used in bytes.
	 * @return
	 * converted result.
	 */
	public static String bytesToString(byte[] input, CodeType codeType) {
		return new String(input, CHARSETS[codeType.ordinal()]);
	}

	/**
	 * Convert integer coded in bytes whose higher byte come after lower byte,
	 * that is, in reversed order.
	 * 
	 * @param input
	 * bytes to be converted.
	 * @return
	 * integer result.
	 */
    public static int reversedBytesToInt(byte[] input) {
		int retval = Converter.byteToInt(input);
		retval = Integer.reverseBytes(retval);
    	return retval;
    }
    
    /**
     * Convert bytes to integer.
     * @param input
     * bytes to be converted.
     * @return
     * converted integer.
     */
    public static int byteToInt(byte[] input) {
        return byteToInt2(input, 0);
    }

    /**
     * Convert bytes to integer, from offset position.
     * @param input
     * bytes to be converted.
     * @param offset
     * position to start.
     * @return
     * converted integer.
     */
    public static int byteToInt2(byte[] input, int offset) {
        return rightShift(input[offset + 0], 24) + rightShift(input[offset + 1], 16) + rightShift(input[offset + 2], 8) + rightShift(input[offset + 3], 0);
    }

    /**
     * Right shift byte by offset number.
     * @param input
     * byte to be operate.
     * @param offset
     * amount to shift.
     * @return
     * integer coded by byte after shift.
     */
    protected static int rightShift(byte input, int offset) {
        return (input >= 0) ? ((int) input << offset) : ((int) (256 + input) << offset);
    }
}

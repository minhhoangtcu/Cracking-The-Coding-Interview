package ctcInterview.sortingAndSearching;

/**
 * Imagine you have a 20GB file with one string per line. Explain how you would
 * sort the file.
 * 
 * @author minhhoang
 *
 */
public class SS1104_HugeFileSort {

	/**
	 * Break down the file into smaller chunks that fit into main memory. For
	 * example, if we have an 1GB memory, then we need to break the file into 20
	 * files. We load each chunk and sort it with any sorting method (quick
	 * sort). Next, we read 20 files together, but only the first few MB of
	 * them, and allocate the remaining memory for output buffer. Then, we do a
	 * merge sort on 20 files together, fill up the output buffer, write out to
	 * file, and then empty the buffer.
	 * 
	 * This is known as external sort.
	 */

}

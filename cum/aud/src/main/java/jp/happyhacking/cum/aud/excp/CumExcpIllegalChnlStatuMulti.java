/**
 * 
 */
package jp.happyhacking.cum.aud.excp;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpIllegalChnlStatuMulti extends CumExcpAbst {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ArrayList<CumExcpIllegalChnlStatus> multi = new ArrayList<CumExcpIllegalChnlStatus>();

	public void add(CumExcpIllegalChnlStatus e) {
		multi.add(e);
	}

	@Override
	public void printStackTrace() {

		super.printStackTrace();

		for (CumExcpIllegalChnlStatus e : multi) {
			e.printStackTrace();
		}
	}

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		for (CumExcpIllegalChnlStatus e : multi) {
			e.printStackTrace(s);
		}
	}

	/**
	 * @return number of exceptions
	 */
	public int size() {

		return multi.size();
	}

}

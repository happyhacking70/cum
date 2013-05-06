/**
 * 
 */
package jp.happyhacking70.cum.excp.cmd.rsc;

import java.io.IOException;

import jp.happyhacking70.cum.excp.cmd.CumExcpCmdAbst;

/**
 * Channel Resource can not be binarized.
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpRscBinariseFailed extends CumExcpCmdAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 */
	public CumExcpRscBinariseFailed(IOException e) {
		super(e);
	}

}

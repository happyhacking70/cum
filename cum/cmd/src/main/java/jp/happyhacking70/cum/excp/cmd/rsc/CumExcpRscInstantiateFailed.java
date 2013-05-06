/**
 * 
 */
package jp.happyhacking70.cum.excp.cmd.rsc;

import jp.happyhacking70.cum.excp.cmd.CumExcpCmdAbst;

/**
 * Channel Resource can not be binarized.
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcpRscInstantiateFailed extends CumExcpCmdAbst {

	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 */
	public CumExcpRscInstantiateFailed(Exception e) {
		super(e);
	}

}

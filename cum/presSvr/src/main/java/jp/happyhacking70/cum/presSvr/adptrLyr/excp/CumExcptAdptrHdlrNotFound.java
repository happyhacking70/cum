/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.excp;

import jp.happyhacking70.cum.cmd.CmdAbst;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CumExcptAdptrHdlrNotFound extends CumExcpAbst {
	protected CmdAbst cmd;

	private static final long serialVersionUID = 1L;

	/**
	 * @param cmd
	 */
	public CumExcptAdptrHdlrNotFound(CmdAbst cmd) {
		this.cmd = cmd;
	}

}

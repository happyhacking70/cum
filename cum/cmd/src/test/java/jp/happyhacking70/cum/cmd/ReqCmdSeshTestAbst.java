/**
 * 
 */
package jp.happyhacking70.cum.cmd;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst.CmdTypes;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class ReqCmdSeshTestAbst extends CmdSeshTestAbst {

	/**
	 * @throws IOException
	 */
	public ReqCmdSeshTestAbst() throws IOException {
		super();
	}

	@Override
	protected final CmdTypes getCmdType() {
		return CmdTypes.REQ;
	}

}

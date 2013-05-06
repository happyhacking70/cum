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
abstract public class ReqCmdChnlAudTestAbst extends CmdChnlAudTestAbst {

	/**
	 * @throws IOException
	 */
	public ReqCmdChnlAudTestAbst() throws IOException {
		super();
	}

	@Override
	final protected CmdTypes getCmdType() {
		return CmdTypes.REQ;
	}

}

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
public abstract class ReqCmdChnlTestAbst extends CmdChnlTestAbst {

	/**
	 * @throws IOException
	 */
	public ReqCmdChnlTestAbst() throws IOException {
		super();
	}

	@Override
	final protected CmdTypes getCmdType() {
		return CmdTypes.REQ;
	}

}

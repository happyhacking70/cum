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
abstract public class NtfyCmdChnlTestAbst extends CmdChnlTestAbst {

	/**
	 * @throws IOException
	 */
	public NtfyCmdChnlTestAbst() throws IOException {
		super();
	}

	@Override
	final protected CmdTypes getCmdType() {
		return CmdTypes.NTFY;
	}

}

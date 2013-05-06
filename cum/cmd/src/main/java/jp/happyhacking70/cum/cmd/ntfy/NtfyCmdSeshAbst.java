/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy;

import jp.happyhacking70.cum.cmd.CmdSeshAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class NtfyCmdSeshAbst extends CmdSeshAbst implements
		NtfyCmdIntf {

	/**
	 * @param seshName
	 */
	public NtfyCmdSeshAbst(String seshName) {
		super(seshName);
	}

	/**
	 * @param doc
	 * @throws CumExcpIllegalCmdDoc
	 */
	public NtfyCmdSeshAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	final protected void setCmdType() {
		cmdType = CmdTypes.NTFY;
	}
}

/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public abstract class NtfyCmdChnlAbst extends CmdChnlAbst implements
		NtfyCmdIntf {

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public NtfyCmdChnlAbst(String seshName, String chnlType, String chnlName) {
		super(seshName, chnlType, chnlName);
	}

	public NtfyCmdChnlAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	final protected void setCmdType() {
		cmdType = CmdTypes.NTFY;

	}

}

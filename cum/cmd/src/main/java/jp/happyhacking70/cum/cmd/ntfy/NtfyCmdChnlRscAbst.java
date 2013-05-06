/**
 * 
 */
package jp.happyhacking70.cum.cmd.ntfy;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.CmdChnlRscAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class NtfyCmdChnlRscAbst extends CmdChnlRscAbst implements
		NtfyCmdIntf {
	final protected ArrayList<ChnlRscIntf> chnlRsces = new ArrayList<ChnlRscIntf>();

	/**
	 * @param seshName
	 * @param chnlName
	 * @param rsces
	 */
	public NtfyCmdChnlRscAbst(String seshName, String chnlName,
			ArrayList<ChnlRscIntf> rsces) {
		super(seshName, chnlName, rsces);
	}

	/**
	 * @param seshName
	 * @param chnlName
	 */
	public NtfyCmdChnlRscAbst(String seshName, String chnlName) {
		super(seshName, chnlName);
	}

	/**
	 * @throws CumExcpIllegalCmdDoc
	 * 
	 */
	public NtfyCmdChnlRscAbst(Document doc) throws CumExcpIllegalCmdDoc {
		super(doc);
	}

	@Override
	protected void setCmdType() {
		cmdType = CmdTypes.NTFY;

	}

}

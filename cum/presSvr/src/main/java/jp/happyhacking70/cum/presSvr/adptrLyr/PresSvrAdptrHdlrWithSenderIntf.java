/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PresSvrAdptrHdlrWithSenderIntf extends
		PresSvrAdptrHdlrIntfBase {
	public ResCmdIntf hndlCmd(CmdAbst cmd, CmdSenderIntf sender,
			SeshMgrPresSvrAllIntf seshMgr) throws CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc, CumExcpXMLGenFailed;
}

/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr;

import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrDisconIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class DiscnHdlrAbst {
	SeshMgrPresSvrDisconIntf seshMgr;

	public DiscnHdlrAbst(SeshMgrPresSvrDisconIntf seshMgr) {
		super();
		this.seshMgr = seshMgr;
	}

	abstract public void notifyDiscon() throws CumExcpSeshNotExist,
			CumExcpXMLGenFailed;
}

/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdLvChnl;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdLvChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrLvChnl implements PresSvrAdptrHdlrIntf {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.comLyr.adaptor.PresSvrAdaptorHdlrIntf#
	 * hndlCmd(java.lang.String)
	 */
	@Override
	public ResCmdIntf hndlCmd(CmdAbst cmd, SeshMgrPresSvrAllIntf seshMgr)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpXMLGenFailed {

		ReqCmdLvChnl reqCmd = (ReqCmdLvChnl) cmd;
		ResCmdLvChnl resCmd;

		try {
			seshMgr.lvChnl(reqCmd.getSeshName(), reqCmd.getChnlName(),
					reqCmd.getAudName());

			resCmd = new ResCmdLvChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(), ResCmdLvChnl.RsltTypes.Left.name());

		} catch (CumExcpAudNotExist e) {
			resCmd = new ResCmdLvChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(), ResCmdLvChnl.RsltTypes.NotExist.name());
		} catch (CumExcpChnlNotExist e) {
			resCmd = new ResCmdLvChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(),
					ResCmdLvChnl.RsltTypes.ChnlNotExist.name());
		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdLvChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getAudName(),
					ResCmdLvChnl.RsltTypes.SeshNotExist.name());
		}

		return resCmd;
	}
}

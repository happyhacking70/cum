/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr.hdlr.impl;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.req.impl.ReqCmdRegChnl;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.PresSvrAdptrHdlrIntf;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.seshLyr.SeshMgrPresSvrAllIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresSvrAdptrHdlrRegChnl implements PresSvrAdptrHdlrIntf {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.comLyr.adaptor.SvrAdaptorHdlrWithRscesIntf
	 * #hndlCmd(jp.happyhacking70.cum3.cmd.CmdAbst, java.util.ArrayList)
	 */
	@Override
	public ResCmdIntf hndlCmd(CmdAbst cmd, SeshMgrPresSvrAllIntf seshMgr)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc,
			CumExcpXMLGenFailed {

		ReqCmdRegChnl reqCmd = (ReqCmdRegChnl) cmd;
		ResCmdRegChnl resCmd;

		try {
			seshMgr.regChnl(reqCmd.getSeshName(), reqCmd.getChnlType(),
					reqCmd.getChnlName(), reqCmd.getRscData());
			resCmd = new ResCmdRegChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getRscData(), ResCmdRegChnl.RsltTypes.Reged);

		} catch (CumExcpRscExists e) {
			resCmd = new ResCmdRegChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getRscData(), ResCmdRegChnl.RsltTypes.DupRsc);
		} catch (CumExcpChnlExists e) {
			resCmd = new ResCmdRegChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getRscData(), ResCmdRegChnl.RsltTypes.Exists);
		} catch (CumExcpRscNull e) {
			resCmd = new ResCmdRegChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getRscData(), ResCmdRegChnl.RsltTypes.RscNull);
		} catch (CumExcpSeshNotExist e) {
			resCmd = new ResCmdRegChnl(reqCmd.getSeshName(),
					reqCmd.getChnlType(), reqCmd.getChnlName(),
					reqCmd.getRscData(), ResCmdRegChnl.RsltTypes.SeshNotExist);
		}
		return resCmd;
	}
}

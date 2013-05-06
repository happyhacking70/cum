/**
 * 
 */
package jp.happyhacking70.cum.presSvr.adptrLyr;

import java.util.ArrayList;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdDoc;
import jp.happyhacking70.cum.excp.cmd.CumExcpIllegalCmdXML;
import jp.happyhacking70.cum.presSvr.adptrLyr.discnHdlr.DiscnHdlrAbst;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;

import org.javatuples.Pair;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PresSvrAdptrIntf {

	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc;

	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml, CmdSenderIntf sender)
			throws CumExcpIllegalCmdXML, CumExcpIllegalCmdDoc;

	public Pair<String, DiscnHdlrAbst> hndlCmd(String xml,
			ArrayList<ChnlRscIntf> rsces) throws CumExcpIllegalCmdXML,
			CumExcpIllegalCmdDoc;

}
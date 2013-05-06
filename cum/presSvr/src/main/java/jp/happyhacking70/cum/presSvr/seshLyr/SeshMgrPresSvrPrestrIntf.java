/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr;

import java.util.List;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsSesh;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpChnlNotExist;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpRscNull;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshExists;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;
import jp.happyhacking70.cum.presSvr.comLyr.CmdSenderIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface SeshMgrPresSvrPrestrIntf {

	/**
	 * <UL>
	 * <LI>create new session</LI>
	 * <LI>register to sessions</LI>
	 * </UL>
	 * 
	 * @param seshName
	 * @param sender
	 * @throws CumExcpSeshExists
	 */
	public void regSesh(String seshName, CmdSenderIntf sender)
			throws CumExcpSeshExists;

	/**
	 * <UL>
	 * <LI>create new channel</LI>
	 * <LI>register to appropriate session</LI>
	 * <LI>send {@link NtfyCmdRegChnl} to <U>session</U> audiences</LI>
	 * </UL>
	 * 
	 * 
	 * @param seshName
	 * @param chnlName
	 * @param lrscesist
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpChnlExists
	 * @throws CumExcpRscExists
	 * @throws CumExcptNullRsces
	 * @throws CumExcpRscNull
	 * @throws CumExcpXMLGenFailed
	 */
	public void regChnl(String seshName, String chnlName,
			List<ChnlRscIntf> lrscesist) throws CumExcpSeshNotExist,
			CumExcpChnlExists, CumExcpRscExists, CumExcpRscNull,
			CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send {@link NtfyCmdClsSesh} to <U>session</U> audiences</LI>
	 * <LI>remove session from sessions</LI>
	 * </UL>
	 * 
	 * @param seshName
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void clsSesh(String seshName) throws CumExcpSeshNotExist,
			CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send command to specific audience of specific <U>channel</U></LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @param audName
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd, String audName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist,
			CumExcpAudNotExist, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send command to all audiences of specific <U>channel</U></LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd) throws CumExcpSeshNotExist,
			CumExcpChnlNotExist, CumExcpAudNotExist, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send {@link NtfyCmdClsChnl} to all <U>channel</U> audiences</LI>
	 * <LI>close channel</LI>
	 * </UL>
	 * 
	 * @param seshName
	 * @param chnlName
	 * @throws CumExcpSeshNotExist
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void clsChnl(String seshName, String chnlName)
			throws CumExcpSeshNotExist, CumExcpChnlNotExist,
			CumExcpXMLGenFailed;

}

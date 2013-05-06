/**
 * 
 */
package jp.happyhacking70.cum.presSvr.chnlLyr;

import jp.happyhacking70.cum.cmd.CmdChnlAbst;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdClsChnl;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdRegChnl;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpAudNotExist;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface ChnlPresSvrPrestrIntf {

	/**
	 * <UL>
	 * <LI>send command to <U>specific</U> audience</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @param name
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd, String name)
			throws CumExcpAudNotExist, CumExcpXMLGenFailed;

	/**
	 * <UL>
	 * <LI>send command to all <U>channel</U> audiences</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @throws CumExcpAudNotExist
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendChnlCmd(CmdChnlAbst cmd) throws CumExcpAudNotExist,
			CumExcpXMLGenFailed;

	/**
	 * returns NtfyCmdRegChnl so that session can send it to all <U>session</U>
	 * audiences
	 * 
	 * @return NtfyCmdRegChnl
	 */
	public NtfyCmdRegChnl getNtfyCmdRegChnl();

	/**
	 * returns NtfyCmdClsChnl so that session can send it to all <U>session</U>
	 * audiences
	 * 
	 * @return NtfyCmdClsChnl
	 */
	public NtfyCmdClsChnl getNtfyCmdClsChnl();
}

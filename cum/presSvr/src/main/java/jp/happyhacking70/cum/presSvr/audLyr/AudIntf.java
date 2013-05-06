/**
 * 
 */
package jp.happyhacking70.cum.presSvr.audLyr;

import jp.happyhacking70.cum.cmd.CmdAbst;
import jp.happyhacking70.cum.cmd.ntfy.impl.NtfyCmdAudDisconned;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudIntf {

	/**
	 * @return Audience Name
	 */
	String getAudName();

	/**
	 * <UL>
	 * <LI>send command to audience</LI>
	 * </UL>
	 * <BR>
	 * <BR>
	 * 
	 * if communication error occurs:<BR>
	 * <UL>
	 * <LI>send {@link NtfyCmdAudDisconned} to presenter</LI>
	 * <LI>remove audience from session and channels</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @throws CumExcpXMLGenFailed
	 */
	public void sendCmd(CmdAbst cmd) throws CumExcpXMLGenFailed;

}

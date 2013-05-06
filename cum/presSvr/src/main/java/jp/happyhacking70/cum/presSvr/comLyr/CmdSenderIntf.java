/**
 * 
 */
package jp.happyhacking70.cum.presSvr.comLyr;

import jp.happyhacking70.cum.cmd.XMLableCmdIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface CmdSenderIntf {
	/**
	 * <UL>
	 * <LI>send command</LI>
	 * <LI>When {@link CumExcpXMLGenFailed} is detected</LI>
	 * </UL>
	 * 
	 * @param cmd
	 * @throws CumExcpXMLGenFailed
	 */
	void sendCmd(XMLableCmdIntf cmd) throws CumExcpXMLGenFailed;
}

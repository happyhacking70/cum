/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc.factory;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscInstantiateFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface ChnlRscfactoryHdlrIntf {
	public ChnlRscIntf getChnlRsc(String name, byte[] bin)
			throws CumExcpRscInstantiateFailed;
}

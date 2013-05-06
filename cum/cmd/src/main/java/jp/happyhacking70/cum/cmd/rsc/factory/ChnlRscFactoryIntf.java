/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc.factory;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscInstantiateFailed;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpUnknowDataTypeForChnlRsc;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface ChnlRscFactoryIntf {
	/**
	 * Generate {@link ChnlRscIntf} by mime-type, name, and byte[].
	 * 
	 * @param mimeType
	 * @param name
	 * @param bin
	 * @return {@link ChnlRscIntf}
	 * @throws CumExcpUnknowDataTypeForChnlRsc
	 * @throws CumExcpRscInstantiateFailed
	 */
	ChnlRscIntf getChnlRsc(String mimeType, String name, byte[] bin)
			throws CumExcpUnknowDataTypeForChnlRsc, CumExcpRscInstantiateFailed;

	/**
	 * 
	 * You can add more {@link ChnlRscfactoryHdlrIntf} so that you can handle
	 * your own implementation of {@link ChnlRscIntf}
	 * 
	 * @param mimeType
	 * @param hdlrClass
	 */
	void addHdlr(String mimeType,
			Class<? extends ChnlRscfactoryHdlrIntf> hdlrClass);
}

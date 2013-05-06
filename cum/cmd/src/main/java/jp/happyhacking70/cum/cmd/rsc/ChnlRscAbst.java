/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc;

import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscBinariseFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public abstract class ChnlRscAbst implements ChnlRscIntf {
	protected String name;

	public ChnlRscAbst(String name) {
		super();
		this.name = name;
	}

	@Override
	abstract public String getMimeType();

	@Override
	public String getName() {
		return name;
	}

	@Override
	abstract public byte[] getBinary() throws CumExcpRscBinariseFailed;

}

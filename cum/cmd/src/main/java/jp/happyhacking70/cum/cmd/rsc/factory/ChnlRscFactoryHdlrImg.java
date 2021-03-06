/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc.factory;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscAbst;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscImg;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscInstantiateFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ChnlRscFactoryHdlrImg implements ChnlRscfactoryHdlrIntf {

	@Override
	public ChnlRscIntf getChnlRsc(String name, byte[] bin)
			throws CumExcpRscInstantiateFailed {
		ChnlRscAbst chnlRsc = null;

		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new ByteArrayInputStream(bin));
		} catch (IOException e) {
			throw new CumExcpRscInstantiateFailed(e);
		}

		chnlRsc = new ChnlRscImg(name, bImg);

		return chnlRsc;
	}
}

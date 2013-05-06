/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc.factory;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.test.CumTestAbst;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscBinariseFailed;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscInstantiateFailed;
import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpUnknowDataTypeForChnlRsc;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class ChnlRscFactoryTest extends CumTestAbst {

	/**
	 * @throws IOException
	 */
	public ChnlRscFactoryTest() throws IOException {
		super();
	}

	/**
	 * Test method for
	 * {@link jp.happyhacking70.cum3.chnlLyr.rsc.factory.ChnlRscFactory#getChnlRsc(java.lang.String, java.lang.String, byte[])}
	 * .
	 * 
	 * @throws CumExcpRscBinariseFailed
	 * @throws CumExcpRscInstantiateFailed
	 * @throws CumExcpUnknowDataTypeForChnlRsc
	 */
	@Test
	public void testGetChnlRsc() throws CumExcpUnknowDataTypeForChnlRsc,
			CumExcpRscInstantiateFailed, CumExcpRscBinariseFailed {
		ChnlRscFactory f = new ChnlRscFactory();

		ChnlRscIntf rsc = f.getChnlRsc(rscImgA.getMimeType(),
				rscImgA.getName(), rscImgA.getBinary());

		assertEquals(rscImgA.getClass(), rsc.getClass());

	}
}

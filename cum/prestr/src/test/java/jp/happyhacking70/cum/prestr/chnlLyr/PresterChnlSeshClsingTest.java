/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlSeshClsingTest extends PrestrChnlTestAbst {
	@Test
	public void TEST_seshClsing_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.seshClsing();
		assertTrue(chnlView.isSeshClsing());

	}

	@Test
	public void TEST_seshClsing_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoReged();
		chnl.seshClsing();
		assertTrue(chnlView.isSeshClsing());

	}

	@Test
	public void TEST_seshClsing_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.seshClsing();
		assertFalse(chnlView.isSeshClsing());
	}

	@Test
	public void TEST_seshClsing_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.seshClsing();
		assertFalse(chnlView.isSeshClsing());
	}

	@Test
	public void TEST_seshClsing_DISCNED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoDscned();
		chnl.seshClsing();
		assertFalse(chnlView.isSeshClsing());
	}
}

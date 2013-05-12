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
public class PresterChnlRegedTest extends PrestrChnlTestAbst {
	@Test
	public void TEST_chnlReged_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.chnlReged();
		assertTrue(chnlView.isReged());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlReged_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoReged();
		chnl.chnlReged();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlReged_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.chnlReged();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlReged_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.chnlReged();
	}

	@Test
	public void TEST_chnlReged_DISCNED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoDscned();
		chnl.chnlReged();
		assertFalse(chnlView.isReged());
	}
}

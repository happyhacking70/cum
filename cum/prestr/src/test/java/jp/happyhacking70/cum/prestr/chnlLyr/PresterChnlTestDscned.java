/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestDscned extends PrestrChnlTestAbst {
	@Test
	public void TEST_discnded_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.discnded();
		assertTrue(chnlView.isDiscned);
	}

	@Test
	public void TEST_discnded_REGED() throws TestExcp, CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoReged();
		chnl.discnded();
		assertTrue(chnlView.isDiscned);
	}

	@Test
	public void TEST_discnded_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.discnded();
		assertTrue(chnlView.isDiscned);
	}

	@Test
	public void TEST_discnded_CLSED() throws TestExcp, CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.discnded();
		assertTrue(chnlView.isDiscned);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_discnded_DISCNED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoDscned();
		chnl.discnded();
	}

}

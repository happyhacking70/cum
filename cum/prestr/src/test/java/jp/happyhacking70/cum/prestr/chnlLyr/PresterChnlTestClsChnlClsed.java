/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestClsChnlClsed extends PrestrChnlTestAbst {
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlClsed_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.chnlClsed();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlClsed_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoReged();
		chnl.chnlClsed();
	}

	@Test
	public void TEST_chnlClsed_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.chnlClsed();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_chnlClsed_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.chnlClsed();
	}

	@Test
	public void TEST_chnlClsed_DISCNED() throws CumExcpIllegalChnlStatus,
			TestExcp {
		PrestrChnl chnl = gotoDscned();
		chnl.chnlClsed();
	}

}

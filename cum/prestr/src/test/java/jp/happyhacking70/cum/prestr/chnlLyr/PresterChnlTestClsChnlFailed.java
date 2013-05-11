/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestClsChnlFailed extends PrestrChnlTestAbst {
	protected String rslt = "rslt";

	@Test
	public void TEST_clsChnlFailed_REGING() throws CumExcpIllegalChnlStatus {
		PrestrChnl chnl = getChnl();
		chnl.clsChnlFailed(rslt);
		assertEquals(rslt, chnlView.getRslt());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnlFailed_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoReged();
		chnl.clsChnlFailed(rslt);

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnlFailed_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.clsChnlFailed(rslt);

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnlFailed_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.clsChnlFailed(rslt);

	}

	@Test
	public void TEST_clsChnlFailed_DISCNED() throws CumExcpIllegalChnlStatus,
			TestExcp {
		PrestrChnl chnl = gotoDscned();
		chnl.clsChnlFailed(rslt);
		assertNull(chnlView.getRslt());
	}

}

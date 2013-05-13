/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertNull;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlJoinedFailed_Test extends AudChnlTestAbst {

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
	}

	@Test
	public void test_joining() throws CumExcpIllegalChnlStatus, TestExcp {
		AudChnl chnl = goto_joining();
		assertNull(chnlView.getRslt());
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
		assertNull(chnlView.getRslt());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlJoinedFailed(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name());
	}

}

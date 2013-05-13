/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRjctChnl;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlRjctFailed_Test extends AudChnlTestAbst {

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());
	}

	@Test
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlRjctFailed(ResCmdRjctChnl.RsltTypes.Exists.name());
	}
}

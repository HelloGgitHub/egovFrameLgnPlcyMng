package egovframework.web.user;

import egovframework.com.cmm.CIDRUtils;

public class test {

	public static void main(String[] args) throws Exception {
		
		CIDRUtils cidrUtils = new CIDRUtils("10.77.12.11/18");
		String networkAddress = cidrUtils.getNetworkAddress();
		String broadcastAddress = cidrUtils.getBroadcastAddress();
		System.out.println(networkAddress);
		System.out.println(broadcastAddress);
		
		CIDRUtils cidrUtils2 = new CIDRUtils("435:23f::45:23/101");
		String networkAddress2 = cidrUtils2.getNetworkAddress();
		String broadcastAddress2 = cidrUtils2.getBroadcastAddress();
		
		System.out.println(networkAddress2);
		System.out.println(broadcastAddress2);
	}
	
}



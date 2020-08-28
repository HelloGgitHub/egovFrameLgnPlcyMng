package egovframework.com.cmm;

public class IpCheckUtil {

	// ip check
    public boolean ipCheck(String clntIp, String startIp, String endIp) {
    	boolean rtn = false;

    	Long clntIpLong = ipToInt(clntIp);
    	System.out.println(clntIpLong);
    	
    	Long startIpLong = ipToInt(startIp);
    	Long endIpLong = ipToInt(endIp);

    	if( clntIpLong >= startIpLong  && clntIpLong  <= endIpLong ){
    		rtn = true;
    	}
    	return rtn;
    }

    // String(IP) to long
    public static Long ipToInt(String ip) {
    	String[] addrArray = ip.split("\\.");
    	long num = 0;

    	for (int i=0; i<addrArray.length; i++) {
    		int power = 3-i;
    		num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
    	}
    	return num;
    }

	
}

package egovframework.web.user;

import org.apache.commons.net.util.SubnetUtils;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

	
	 

	 
	    public static void test2() {
	 
	        SubnetUtils subnetUtils = new SubnetUtils("120.131.5.130/26");
	        subnetUtils.setInclusiveHostCount(true);    //network,broadcast ip 포함, false:불포함
	 
	        System.out.println("넷마스크:" + subnetUtils.getInfo().getNetmask());
	        System.out.println("네트워크 IP:" + subnetUtils.getInfo().getNetworkAddress());
	        System.out.println("브로드캐스트 IP:" + subnetUtils.getInfo().getBroadcastAddress());
	 
	        System.out.println("시작 IP:" + subnetUtils.getInfo().getLowAddress());
	        System.out.println("끝 IP:" + subnetUtils.getInfo().getHighAddress());
	        System.out.println("120.131.5.130 해당 대역의 포함여부: " + subnetUtils.getInfo().isInRange("120.131.5.130"));
	        System.out.println("120.131.5.234 해당 대역의 포함여부: " + subnetUtils.getInfo().isInRange("120.131.5.234"));

	        
	        
	        String[] allAddresses = subnetUtils.getInfo().getAllAddresses();
	        System.out.println("===사용가능한 IP 리스트 시작===");
	        for (String allAddress : allAddresses) {
	            System.out.println(allAddress);
	        }
	    }



}

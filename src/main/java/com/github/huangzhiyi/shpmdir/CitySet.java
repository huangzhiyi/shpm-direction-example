package com.github.huangzhiyi.shpmdir;

import java.util.BitSet;

/**
 * China Mainland city code set.
 * 
 * @author huangzhiyi
 *
 */
public class CitySet {

	private final static int DEFAULT_END=999;
	
	private int start=0;
	private int end=DEFAULT_END;
	private BitSet bs=new BitSet(DEFAULT_END);

	/**
	 * Build from binary string like '10100010'
	 * @param bin Binary String
	 * @return
	 */
	public static CitySet ofBinStr(String bin){
		CitySet cs=emptySet();
		if(bin!=null){
			char[] ca=bin.toCharArray();
			int size=Math.min(cs.end, bin.length());
			for(int i=cs.start;i<=size;i++){
				if(ca[i]=='1'){
					cs.bs.set(i);
				}
			}
		}
		return cs;
	}
	
	/**
	 * Build with city codes
	 * 
	 * @param cityCodes
	 * @return
	 */
	public static CitySet ofCityCodes(String... cityCodes){
		return emptySet().add(cityCodes);
	}
	
	/**
	 * 
	 * @return An empty set
	 */
	public static CitySet emptySet(){
		return new CitySet();
	}
	
	private CitySet(){
		bs.set(0);//The binary string should be started from 1
	}
	
	/**
	 * Add city code.
	 * @param cityCodes
	 * @return
	 */
	public CitySet add(String... cityCodes){
		for(String cityCode:cityCodes){
			bs.set(Integer.valueOf(cityCode));
		}
		return this;
	}
	
	/**
	 * Convert to binary string.
	 * @return
	 */
	public String toBinStr(){
		StringBuilder b = new StringBuilder();

        int cur = bs.nextSetBit(0);
        if (cur != -1) {
        	int last=cur+1;
            b.append('1');
            for (cur = bs.nextSetBit(cur+1); cur >= 0; cur = bs.nextSetBit(cur+1)) {
                while(last++<cur){
                	b.append('0');
                }
            	
            	int endOfRun = bs.nextClearBit(cur);
                do { b.append('1'); }
                while (++cur < endOfRun);
                last=cur;
            }
        }

        return b.toString();
	}
}
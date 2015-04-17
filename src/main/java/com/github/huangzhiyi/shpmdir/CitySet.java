package com.github.huangzhiyi.shpmdir;

import java.util.Arrays;
import java.util.BitSet;

/**
 * China Mainland city code set.
 * 
 * @author huangzhiyi
 *
 */
public class CitySet {

	private final static int DEFAULT_END=1000;
	
	private final int end=DEFAULT_END;
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
			for(int i=0;i<size;i++){
				if(ca[i]=='1'){
					cs.bs.set(i+1);
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
	 * True if city set contains all the specified city codes.
	 * @param cityCodes
	 * @return
	 */
	public boolean contains(String... cityCodes){
		if(cityCodes==null){
			return false;
		}
		
		for(String cityCode:cityCodes){
			if(!bs.get(Integer.valueOf(cityCode))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return true if no city code contained.
	 * @return
	 */
	public boolean isEmpty(){
		return "".equals(this.toBinStr());
	}
	
	public boolean isNotEmpty(){
		return !isEmpty();
	}
	
	/**
	 * 
	 * @return An empty set
	 */
	public static CitySet emptySet(){
		return new CitySet();
	}
	
	private CitySet(){
		
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
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if(obj instanceof CitySet){
			result=bs.equals(((CitySet) obj).getBs());
		}
		return result;
	}
	
	@Override
	public int hashCode(){
		return bs.hashCode()+23;
	}
	
	@Override
	public String toString(){
		return bs.toString();
	}
	
	/**
	 * Convert to binary string.
	 * @return
	 */
	public String toBinStr(){
		StringBuilder b = new StringBuilder();

        int cur = bs.nextSetBit(0);
        if (cur != -1) {
        	if(cur>1){
        		char[] zeroArr=new char[cur-1];
        		Arrays.fill(zeroArr, '0');
        		b.append(zeroArr);
        	}
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

	public BitSet getBs() {
		return bs;
	}
}

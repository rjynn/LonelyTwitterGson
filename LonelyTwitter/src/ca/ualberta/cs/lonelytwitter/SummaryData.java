package ca.ualberta.cs.lonelytwitter;

import java.io.Serializable;

public class SummaryData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -636293832363800242L;
	private int numTweets;
	private long aveLength;
	
	public SummaryData(int num, long aveLe){
		this.numTweets = num;
		this.aveLength = aveLe;
	}
	
	public SummaryData() {
		// TODO Auto-generated constructor stub
	}

	public void setTweetNum(int num){
		this.numTweets = num;
	}
	
	public void setAveLength(long num){
		this.aveLength = num;
	}
	public int getTweetNum(){
		return numTweets;
	}
	
	public long getLengthave(){
		return aveLength;
	}
	

}

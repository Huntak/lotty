package com.lotty.first.vo;

public class LottoVO {
	private String returnValue;		// 실행결과
	private long drwNo;				// 회차
	private long drwtNo1;			// 당첨번호1
	private long drwtNo2;			// 당첨번호2
	private long drwtNo3;			// 당첨번호3
	private long drwtNo4;			// 당첨번호4
	private long drwtNo5;			// 당첨번호5
	private long drwtNo6;			// 당첨번호6
	private long bnusNo;			// 보너스번호
	private long firstWinamnt;		// 1등 당첨금
	private long firstPrzwnerCo;	// 1등 당첨 인원
	private long firstAccumamnt;	// 1등 당첨금 총액
	private long totSellamnt;		// 누적금
	private String drwNoDate;		// 당첨일
	
	public String getReturnValue() {
		return returnValue;
	}
	
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	
	public long getDrwNo() {
		return drwNo;
	}
	
	public void setDrwNo(long drwNo) {
		this.drwNo = drwNo;
	}
	
	public long getDrwtNo1() {
		return drwtNo1;
	}
	
	public void setDrwtNo1(long drwtNo1) {
		this.drwtNo1 = drwtNo1;
	}
	
	public long getDrwtNo2() {
		return drwtNo2;
	}
	
	public void setDrwtNo2(long drwtNo2) {
		this.drwtNo2 = drwtNo2;
	}
	
	public long getDrwtNo3() {
		return drwtNo3;
	}
	
	public void setDrwtNo3(long drwtNo3) {
		this.drwtNo3 = drwtNo3;
	}
	
	public long getDrwtNo4() {
		return drwtNo4;
	}
	
	public void setDrwtNo4(long drwtNo4) {
		this.drwtNo4 = drwtNo4;
	}
	
	public long getDrwtNo5() {
		return drwtNo5;
	}
	
	public void setDrwtNo5(long drwtNo5) {
		this.drwtNo5 = drwtNo5;
	}
	
	public long getDrwtNo6() {
		return drwtNo6;
	}
	
	public void setDrwtNo6(long drwtNo6) {
		this.drwtNo6 = drwtNo6;
	}
	
	public long getBnusNo() {
		return bnusNo;
	}
	
	public void setBnusNo(long bnusNo) {
		this.bnusNo = bnusNo;
	}
	
	public long getFirstWinamnt() {
		return firstWinamnt;
	}
	
	public void setFirstWinamnt(long firstWinamnt) {
		this.firstWinamnt = firstWinamnt;
	}
	
	public long getFirstPrzwnerCo() {
		return firstPrzwnerCo;
	}
	
	public void setFirstPrzwnerCo(long firstPrzwnerCo) {
		this.firstPrzwnerCo = firstPrzwnerCo;
	}
	
	public long getFirstAccumamnt() {
		return firstAccumamnt;
	}
	
	public void setFirstAccumamnt(long firstAccumamnt) {
		this.firstAccumamnt = firstAccumamnt;
	}
	
	public long getTotSellamnt() {
		return totSellamnt;
	}
	
	public void setTotSellamnt(long totSellamnt) {
		this.totSellamnt = totSellamnt;
	}
	
	public String getDrwNoDate() {
		return drwNoDate;
	}
	
	public void setDrwNoDate(String drwNoDate) {
		this.drwNoDate = drwNoDate;
	}
	
	@Override
	public String toString() {
		return "LottoVO [returnValue=" + returnValue + ", drwNo=" + drwNo + ", drwtNo1=" + drwtNo1 + ", drwtNo2=" + drwtNo2 + ", drwtNo3=" + drwtNo3 + ", drwtNo4=" + drwtNo4 + ", drwtNo5=" + drwtNo5 + ", drwtNo6=" + drwtNo6 + ", bnusNo=" + bnusNo + ", firstWinamnt=" + firstWinamnt + ", firstPrzwnerCo=" + firstPrzwnerCo + ", firstAccumamnt=" + firstAccumamnt + ", totSellamnt=" + totSellamnt + ", drwNoDate=" + drwNoDate + "]";
	}
}

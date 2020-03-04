package com.lotty.first.vo;

public class LottoVO {
	private String returnValue;		// 실행결과
	private Long drwNo;			// 회차
	private Long drwtNo1;			// 당첨번호1
	private Long drwtNo2;			// 당첨번호2
	private Long drwtNo3;			// 당첨번호3
	private Long drwtNo4;			// 당첨번호4
	private Long drwtNo5;			// 당첨번호5
	private Long drwtNo6;			// 당첨번호6
	private Long bnusNo;			// 보너스번호
	private Long firstWinamnt;	// 1등 당첨금
	private Long firstPrzwnerCo;	// 1등 당첨 인원
	private Long firstAccumamnt;	// 1등 당첨금 총액
	private Long totSellamnt;		// 누적금
	private String drwNoDate;		// 당첨일

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public Long getDrwNo() {
		return drwNo;
	}

	public void setDrwNo(Long drwNo) {
		this.drwNo = drwNo;
	}

	public Long getDrwtNo1() {
		return drwtNo1;
	}

	public void setDrwtNo1(Long drwtNo1) {
		this.drwtNo1 = drwtNo1;
	}

	public Long getDrwtNo2() {
		return drwtNo2;
	}

	public void setDrwtNo2(Long drwtNo2) {
		this.drwtNo2 = drwtNo2;
	}

	public Long getDrwtNo3() {
		return drwtNo3;
	}

	public void setDrwtNo3(Long drwtNo3) {
		this.drwtNo3 = drwtNo3;
	}

	public Long getDrwtNo4() {
		return drwtNo4;
	}

	public void setDrwtNo4(Long drwtNo4) {
		this.drwtNo4 = drwtNo4;
	}

	public Long getDrwtNo5() {
		return drwtNo5;
	}

	public void setDrwtNo5(Long drwtNo5) {
		this.drwtNo5 = drwtNo5;
	}

	public Long getDrwtNo6() {
		return drwtNo6;
	}

	public void setDrwtNo6(Long drwtNo6) {
		this.drwtNo6 = drwtNo6;
	}

	public Long getBnusNo() {
		return bnusNo;
	}

	public void setBnusNo(Long bnusNo) {
		this.bnusNo = bnusNo;
	}

	public Long getFirstWinamnt() {
		return firstWinamnt;
	}

	public void setFirstWinamnt(Long firstWinamnt) {
		this.firstWinamnt = firstWinamnt;
	}

	public Long getFirstPrzwnerCo() {
		return firstPrzwnerCo;
	}

	public void setFirstPrzwnerCo(Long firstPrzwnerCo) {
		this.firstPrzwnerCo = firstPrzwnerCo;
	}

	public Long getFirstAccumamnt() {
		return firstAccumamnt;
	}

	public void setFirstAccumamnt(Long firstAccumamnt) {
		this.firstAccumamnt = firstAccumamnt;
	}

	public Long getTotSellamnt() {
		return totSellamnt;
	}

	public void setTotSellamnt(Long totSellamnt) {
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

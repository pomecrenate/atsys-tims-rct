package com.atsys.model;

public class TbEvalScoreVo extends TbEvalScoreBo {
    
    private static final long serialVersionUID = 1L;
    
    private String scoreCd;
    private String resultCd;
    private int resultScore;
    private String opinion;
    private String minutes;
    
	public String getScoreCd() {
		return scoreCd;
	}
	public void setScoreCd(String scoreCd) {
		this.scoreCd = scoreCd;
	}
	public String getResultCd() {
		return resultCd;
	}
	public void setResultCd(String resultCd) {
		this.resultCd = resultCd;
	}
	public int getResultScore() {
		return resultScore;
	}
	public void setResultScore(int resultScore) {
		this.resultScore = resultScore;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
}

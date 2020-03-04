package com.lotty.first.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lotty.first.vo.LottoVO;

@RestController
public class TestController {

	@GetMapping("/test")
	public ArrayList<LottoVO> test() {
		// 지난회차 로또번호 조회
		ArrayList<LottoVO> prevLottoList = getPrevLottoData();

		// TODO : 로또번호 분석 analysisPrevLotto()

		// TODO : 로또번호 추천 recommandLottoNum()

		return prevLottoList;
	}

	// TODO : 1회차만 가져오지말고 전체 회차 가져와서 ArrayList에 담기
	private ArrayList<LottoVO> getPrevLottoData() {
		// 지난회차 로또 정보 담을 리스트
		ArrayList<LottoVO> prevLottoList = new ArrayList<LottoVO>();

		// RestTemplate 에 MessageConverter 세팅
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);

		// parameter 세팅
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("str", "thisistest");

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		int index = 1;

		// 리턴값이 success가 아닌 값이 나올때까지 계속
		// TODO : 무한 루프 조심
		while(true){
			// REST API 호출
			String resultText = restTemplate.getForObject("https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=" + index, String.class);

			// 피싱 실패 시 예외처리
			try {
				obj = (JSONObject) parser.parse(resultText);
			} catch (ParseException e) {
				System.out.println("변환에 실패");
				e.printStackTrace();
			}

			System.out.println(obj);

			if(!((String) obj.get("returnValue")).equals("success")) {
				break;
			}

			// 회차별로 객체에 담기
			LottoVO lotto = new LottoVO();
			lotto.setReturnValue((String) obj.get("returnValue"));
			lotto.setDrwNo((Long) obj.get("drwNo"));
			lotto.setDrwtNo1((Long) obj.get("drwtNo1"));
			lotto.setDrwtNo2((Long) obj.get("drwtNo2"));
			lotto.setDrwtNo3((Long) obj.get("drwtNo3"));
			lotto.setDrwtNo4((Long) obj.get("drwtNo4"));
			lotto.setDrwtNo5((Long) obj.get("drwtNo5"));
			lotto.setDrwtNo6((Long) obj.get("drwtNo6"));
			lotto.setBnusNo((Long) obj.get("bnusNo"));
			lotto.setFirstWinamnt((Long) obj.get("firstWinamnt"));
			lotto.setFirstPrzwnerCo((Long) obj.get("firstPrzwnerCo"));
			lotto.setFirstAccumamnt((Long) obj.get("firstAccumamnt"));
			lotto.setTotSellamnt((Long) obj.get("totSellamnt"));
			lotto.setDrwNoDate((String) obj.get("drwNoDate"));
			prevLottoList.add(lotto);

			// 다음 회차
			index++;
		}

		return prevLottoList;
	}



	// 복사 필요시 사용
	// returnValue
	// drwNo
	// drwtNo1
	// drwtNo2
	// drwtNo3
	// drwtNo4
	// drwtNo5
	// drwtNo6
	// bnusNo
	// firstWinamnt
	// firstPrzwnerCo
	// firstAccumamnt
	// totSellamnt
	// drwNoDate
}
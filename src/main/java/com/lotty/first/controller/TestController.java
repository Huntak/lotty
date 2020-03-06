package com.lotty.first.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
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

@RestController
public class TestController {
	JSONParser parser = new JSONParser();
	String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	String apiUrl = "https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=";
	String src = "src/main/resources/static/";
	String jsonFileName = "prevLottoList_";

	@GetMapping("/make")
	public String make() {
		// 지난회차 로또번호 조회 후 csv 파일로 저장
		// 성공 : SUCCESS, 실패 : FAIL
		return makePrevLottoData();
	}

	@GetMapping("/test")
	public String test() {
		// 지난회차 로또번호 조회
		JSONArray prevLottoList = readPrevLottoData();

		// 로또번호 카운트
		ConcurrentHashMap<Integer, Integer> lottoNumCount = countPrevLotto(prevLottoList);

		// 상위 10개 최대값, 인덱스 구하기
		List<Entry<Integer, Integer>> topNLottoNum = getTopNLottoNum(lottoNumCount);

		// 하위 10개 최대값, 인덱스 구하기
		List<Entry<Integer, Integer>> bottomNLottoNum = getBottomNLottoNum(lottoNumCount);

		// TODO : 삭제 (값 확인용)
//		topNLottoNum.forEach(l -> {
//			System.out.print(l.getKey() + "번:" + l.getValue() + "회, ");
//		});
//		System.out.println("");
//		bottomNLottoNum.forEach(l -> {
//			System.out.print(l.getKey() + "번:" + l.getValue() + "회, ");
//		});
//		System.out.println("");

		// TODO : 당첨금 수령액 높은 날 top 10

		// TODO : 당첨자가 제일 많은 날 top 10

		// 로또번호 추천
		String result = recommandLottoNum(topNLottoNum);

		// TODO : 삭제 (값 확인용)
		System.out.println(result);

		return result;
	}


	@SuppressWarnings("unchecked")
	private String makePrevLottoData() {
		// RestTemplate 에 MessageConverter 세팅
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);

		// parameter 세팅
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("str", "thisistest");

		JSONArray prevLottoList = new JSONArray();
		JSONObject lotto = new JSONObject();
		int index = 1;

		// 리턴값이 success가 아닌 값이 나올때까지 계속
		// TODO : 무한 루프 조심
		while (true) {
			// REST API 호출
			String resultText = restTemplate.getForObject(apiUrl + index, String.class);

			// 파싱 실패 시 예외처리
			try {
				lotto = (JSONObject) parser.parse(resultText);
			} catch (ParseException e) {
				System.out.println("JSON 파싱 실패");
				e.printStackTrace();
				return "FAIL";
			}

			// TODO : 삭제 (값 확인용)
			System.out.println(lotto);

			// 리턴값이 success가 아닌 값이 나올때까지 계속
			if (!((String) lotto.get("returnValue")).equals("success")) {
				break;
			}

			// json array에 저장
			prevLottoList.add(lotto);

			// 다음 회차
			index++;
		}

		// csv 파일로 저장
		// try (FileWriter prevLottoListFile = new FileWriter(src + jsonFileName + today + ".json")) {
		try (FileWriter prevLottoListFile = new FileWriter(src + jsonFileName + ".json")) {
			prevLottoListFile.write(prevLottoList.toJSONString());
		} catch (IOException e) {
			System.out.println("CSV 파일 변환 실패");
			e.printStackTrace();
			return "FAIL";
		}

		return "SUCCESS";
	}

	private JSONArray readPrevLottoData() {
		JSONArray prevLottoList = new JSONArray();

		// 이름이 'prevLottoList_yyyymmdd'인(오늘날짜) csv 파일 읽기
		// try (Reader prevLottoListFile = new FileReader(src + jsonFileName + today + ".json")) {
		try (Reader prevLottoListFile = new FileReader(src + jsonFileName + ".json")) {
			prevLottoList = (JSONArray) parser.parse(prevLottoListFile);
		} catch (IOException e) {
			System.out.println("CSV 파일 읽기 실패");
			e.printStackTrace();

			// TODO : 오늘날짜로 만든 파일 없으면 그 전날 파일로 읽기

		} catch (ParseException e) {
			System.out.println("JSON 파싱 실패");
			e.printStackTrace();
		}

		return prevLottoList;
	}

	@SuppressWarnings("unchecked")
	private ConcurrentHashMap<Integer, Integer> countPrevLotto(JSONArray prevLottoList) {
		JSONObject lotto = new JSONObject();

		// 번호별 카운트 변수
		ConcurrentHashMap<Integer, Integer> lottoNumCount = new ConcurrentHashMap<>();

		// 회차별로 쪼개서 번호별 카운트
		Iterator<JSONObject> iterator = prevLottoList.iterator();
		while (iterator.hasNext()) {
			// 로또 한 회차분
			lotto = iterator.next();

			// TODO : 삭제 (값 확인용)
//			System.out.println(lotto.get("drwtNo1") + " " + lotto.get("drwtNo2") + " " + lotto.get("drwtNo3") + " " + lotto.get("drwtNo4") + " " + lotto.get("drwtNo5") + " " + lotto.get("drwtNo6") + " " + lotto.get("bnusNo"));

			// 번호별 카운트
			int drwtNo;
			for (int i = 1; i < 7; i++) {
				drwtNo = (int) (long) lotto.get("drwtNo" + i);

				if (lottoNumCount.get(drwtNo) == null) {
					lottoNumCount.put(drwtNo, 1);
				} else {
					lottoNumCount.put(drwtNo, lottoNumCount.get(drwtNo) + 1);
				}
			}

			// TODO : (옵션) 보너스 번호 카운트
//			drwtNo = (int) (long) lotto.get("bnusNo");
//			if (lottoNumCount.get(drwtNo) == null) {
//				lottoNumCount.put(drwtNo, 1);
//			} else {
//				lottoNumCount.put(drwtNo, lottoNumCount.get(drwtNo) + 1);
//			}
		}

		return lottoNumCount;
	}

	private List<Entry<Integer, Integer>> getTopNLottoNum(ConcurrentHashMap<Integer, Integer> lottoNumCount) {
		return lottoNumCount.entrySet()
			.stream()
			.sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())) // Sort by value.
			.limit(10)
			.collect(Collectors.toList());
	}

	private List<Entry<Integer, Integer>> getBottomNLottoNum(ConcurrentHashMap<Integer, Integer> lottoNumCount) {
		return lottoNumCount.entrySet()
			.stream()
			.sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())) // Sort by value.
			.limit(10)
			.collect(Collectors.toList());
	}

	private String recommandLottoNum(List<Entry<Integer, Integer>> topNLottoNum) {
		return topNLottoNum.stream()
			.collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
			    Collections.shuffle(collected);
			    return collected.stream();
			}))
			.limit(6)
			.map(Map.Entry::getKey)
			.sorted((e1, e2) -> Integer.compare(e1, e2))
			.map(n -> n.toString())
			.collect(Collectors.joining( " " ));
	}










	// TODO : 삭제 (복사 필요시 사용)
	// private ArrayList<LottoVO> dd() {
	// // 지난회차 로또 정보 담을 리스트
	// ArrayList<LottoVO> prevLottoList = new ArrayList<LottoVO>();
	//
	// // 회차별로 객체에 담기
	// LottoVO lotto = new LottoVO();
	// lotto.setReturnValue((String) obj.get("returnValue"));
	// lotto.setDrwNo((Long) obj.get("drwNo"));
	// lotto.setDrwtNo1((Long) obj.get("drwtNo1"));
	// lotto.setDrwtNo2((Long) obj.get("drwtNo2"));
	// lotto.setDrwtNo3((Long) obj.get("drwtNo3"));
	// lotto.setDrwtNo4((Long) obj.get("drwtNo4"));
	// lotto.setDrwtNo5((Long) obj.get("drwtNo5"));
	// lotto.setDrwtNo6((Long) obj.get("drwtNo6"));
	// lotto.setBnusNo((Long) obj.get("bnusNo"));
	// lotto.setFirstWinamnt((Long) obj.get("firstWinamnt"));
	// lotto.setFirstPrzwnerCo((Long) obj.get("firstPrzwnerCo"));
	// lotto.setFirstAccumamnt((Long) obj.get("firstAccumamnt"));
	// lotto.setTotSellamnt((Long) obj.get("totSellamnt"));
	// lotto.setDrwNoDate((String) obj.get("drwNoDate"));
	// prevLottoList.add(lotto);
	// }

	// TODO : 삭제 (복사 필요시 사용)
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
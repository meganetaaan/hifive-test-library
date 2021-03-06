/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.it.screenshot.partialapge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.htmlhifive.testlib.core.MrtTestBase;
import com.htmlhifive.testlib.core.config.MrtTestConfig;
import com.htmlhifive.testlib.core.model.CompareTarget;
import com.htmlhifive.testlib.core.model.ScreenArea;
import com.htmlhifive.testlib.core.model.SelectorType;

/**
 * スクロールが無いページのスクリーンショット取得のテスト
 */
public class TakeNoScrollPageTest extends MrtTestBase {

	private static final String BASE_URL = MrtTestConfig.getInstance().getTestAppConfig().getBaseUrl();

	/**
	 * スクロールが無いページ(ヘッダのみ表示した状態のページ)の<br>
	 * 部分スクリーンショットが正しく取得できることを確認する。<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：スクリーンショット取得が正しく実行され、<br>
	 * 「ヘッダのみが表示されたページ」のスクリーンショットが取得される。
	 */
	@Test
	public void takeNoScrollPage() {
		driver.get(BASE_URL);

		//ヘッダ以外の領域を非表示にする
		driver.executeScript("document.getElementById('contentcontainer').style.display = 'none';");

		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, ".download")));
		assertionView.assertView("takeNoScroll", targets);
	}
}
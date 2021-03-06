/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.it.assertion.partialpage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.htmlhifive.testlib.core.MrtTestBase;
import com.htmlhifive.testlib.core.config.MrtTestConfig;
import com.htmlhifive.testlib.core.model.CompareTarget;
import com.htmlhifive.testlib.core.model.ScreenArea;
import com.htmlhifive.testlib.core.model.SelectorType;

/**
 * 複数ターゲット指定時、比較が正しく行われるかのテスト
 */
public class CompareMultipleTargetsTest extends MrtTestBase {

	private static final String BASE_URL = MrtTestConfig.getInstance().getTestAppConfig().getBaseUrl();

	/**
	 * 複数ターゲットを比較するテスト。<br>
	 * 前提条件：このテストをSET_EXPECTEDモードで1度実行し、スクリーンショットを取得している<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：assertViewが正解と判定される。
	 */
	@Test
	public void compareMultipleTargets() {
		driver.get(BASE_URL);
		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#news")));
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		assertionView.assertView("multipleTargets", targets);
	}

	/**
	 * 単一要素からなるターゲットを複数指定時にスクリーンショットが正しくとれているかのテスト<br>
	 * ただし各ターゲットは同じ要素を指す。<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：テストが正しく実行でき、各ターゲットのスクリーンショットが正しくとれていることを目視で確認する
	 */
	@Test
	public void takeMultipleTargetsSame() {
		driver.get(BASE_URL);
		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		assertionView.assertView("multipleTargetsSame", targets);
	}
}
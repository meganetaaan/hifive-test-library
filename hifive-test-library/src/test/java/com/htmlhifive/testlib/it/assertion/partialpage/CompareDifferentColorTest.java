/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.it.assertion.partialpage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.htmlhifive.testlib.core.MrtTestBase;
import com.htmlhifive.testlib.core.config.ExecMode;
import com.htmlhifive.testlib.core.config.MrtTestConfig;
import com.htmlhifive.testlib.core.model.CompareTarget;
import com.htmlhifive.testlib.core.model.ScreenArea;
import com.htmlhifive.testlib.core.model.SelectorType;

/**
 * 要素の色が異なる場合に正しく比較が行われるかのテスト
 */
public class CompareDifferentColorTest extends MrtTestBase {

	private static final MrtTestConfig config = MrtTestConfig.getInstance();
	private static final String BASE_URL = config.getTestAppConfig().getBaseUrl();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * ExpectedとActualで要素の文字色が違う場合に正しく比較が行われるかのテスト<br>
	 * 前提条件：このテストをSET_EXPECTEDモードで1度実行し、スクリーンショットを取得している<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：assertViewが不正解と判定されて、差分画像が生成される。
	 */

	@Test
	public void compareDifferentFontColor() {
		driver.get(BASE_URL);

		// ACTUALモードで実行時に、意図的に差分を作り出す
		if (config.getEnvironment().getExecMode() == ExecMode.RUN_TEST) {
			driver.executeJavaScript("document.getElementById('about').style.color = 'orange';");
			thrown.expect(AssertionError.class);
		}

		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		assertionView.assertView("compareDifferentFontColor", targets);

	}

	/**
	 * ExpectedとActualで要素の背景色が違う場合に正しく比較が行われるかのテスト<br>
	 * 前提条件：このテストをSET_EXPECTEDモードで1度実行し、スクリーンショットを取得している<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：assertViewが不正解と判定されて、差分画像が生成される。
	 */
	@Test
	public void compareDifferentBackgroundColor() {
		driver.get(BASE_URL);

		// ACTUALモードで実行時に、意図的に差分を作り出す
		if (config.getEnvironment().getExecMode() == ExecMode.RUN_TEST) {
			driver.executeJavaScript("document.getElementById('about').style.background = 'orange';");
			thrown.expect(AssertionError.class);
		}

		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#about")));
		assertionView.assertView("compareDifferentBackgroundColor", targets);

	}
}

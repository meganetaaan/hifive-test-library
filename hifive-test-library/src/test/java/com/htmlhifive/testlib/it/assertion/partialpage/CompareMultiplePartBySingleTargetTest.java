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
import com.htmlhifive.testlib.core.config.MrtTestConfig;
import com.htmlhifive.testlib.core.model.CompareTarget;
import com.htmlhifive.testlib.core.model.ScreenArea;
import com.htmlhifive.testlib.core.model.SelectorType;

/**
 * 単一のターゲットで複数の要素を指定時、比較が正しく行われるかのテスト
 */
public class CompareMultiplePartBySingleTargetTest extends MrtTestBase {

	private static final String BASE_URL = MrtTestConfig.getInstance().getTestAppConfig().getBaseUrl();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * 1つのセレクタで複数要素選択時にスクリーンショットが正しくとれているかのテスト<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：テストが正しく実行でき、スクリーンショットが正しくとれていることを目視で確認する
	 */
	@Test
	public void compareMultiplePartBySingleTarget() {
		driver.get(BASE_URL);
		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.CSS_SELECTOR, "#news tr")));
		assertionView.assertView("compareMultiplePartBySingleTarget", targets);
	}
}
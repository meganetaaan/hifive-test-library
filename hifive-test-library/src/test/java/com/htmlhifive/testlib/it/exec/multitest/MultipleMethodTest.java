/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.it.exec.multitest;

import org.junit.Test;

import com.htmlhifive.testlib.core.MrtTestBase;

/**
 * １つのテストケース内に複数のテストメソッドがあるテスト
 */
public class MultipleMethodTest extends MrtTestBase {

	/**
	 * 1つめのテスト<br>
	 * 前提条件：このテストをSET_EXPECTEDモードで1度実行し、スクリーンショットを取得している<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：テストが正しく実行でき、assertViewの比較で一致する
	 */
	@Test
	public void firstTest() {
		driver.get(null);
		assertionView.assertView("firstTest");
	}

	/**
	 * 2つめのテスト<br>
	 * 前提条件：このテストをSET_EXPECTEDモードで1度実行し、スクリーンショットを取得している<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：テストが正しく実行でき、assertViewの比較で一致する
	 */
	@Test
	public void secondTest() {
		driver.get(null);
		assertionView.assertView("secondTest");
	}
}

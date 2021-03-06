/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.core.config;

/**
 * 実行モードを表す定数クラス
 */
public enum ExecMode {

	/**
	 * 画像の取得のみを行い、比較は行いません。取得した画像は以降のテストの期待画像として扱われます。
	 */
	SET_EXPECTED,
	/**
	 * 画像の取得のみを行い、比較は行いません。取得した画像を以降のテストで期待画像として扱うこともしません。
	 */
	TAKE_SCREENSHOT,
	/**
	 * 画像の取得、および期待画像との比較を行い、テストの合否判定をします。
	 */
	RUN_TEST {
		@Override
		public boolean isRunTest() {
			return true;
		}
	};

	/**
	 * 当該モードでは合否判定を行うかどうかを返します。
	 * 
	 * @return 判定を行う場合はtrue。結果を保存するのみの場合はfalse。
	 */
	public boolean isRunTest() {
		return false;
	}

}

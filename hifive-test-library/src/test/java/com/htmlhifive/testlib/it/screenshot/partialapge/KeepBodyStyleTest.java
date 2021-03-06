/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.it.screenshot.partialapge;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.htmlhifive.testlib.core.MrtTestBase;
import com.htmlhifive.testlib.core.config.MrtTestConfig;
import com.htmlhifive.testlib.core.model.CompareTarget;
import com.htmlhifive.testlib.core.model.ScreenArea;
import com.htmlhifive.testlib.core.model.SelectorType;

/**
 * 座標が固定されている要素に対するスクリーンショット取得のテスト
 */
public class KeepBodyStyleTest extends MrtTestBase {

	private static final String BASE_URL = MrtTestConfig.getInstance().getTestAppConfig().getBaseUrl();

	/**
	 * bodyにスタイルが定義されている固定されている要素に対してisMoveオプションを指定して<br>
	 * 正しくスクリーンショットがとれていることを確認する。<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：スクリーンショット取得後もbodyのスタイルが変わっていない
	 */
	@Test
	public void keepBodyStyleOffset() {
		driver.get(BASE_URL);

		final String position = "absolute";
		final int offsetLeft = 20;
		final int offsetTop = 30;

		// 取得対象要素の座標を固定する。
		StringBuilder sb = new StringBuilder();
		sb.append("document.body.style.position = '");
		sb.append(position);
		sb.append("'; document.body.style.top = ");
		sb.append(offsetTop);
		sb.append("; document.body.style.left = ");
		sb.append(offsetLeft);
		sb.append(";");
		System.out.println(sb.toString());
		driver.executeJavaScript(sb.toString());

		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.ID, "about"), null, true));
		assertionView.assertView("CssSelector", targets);

		// スクリーンショット取得後もbody座標が保存されている
		WebElement body = driver.findElement(By.tagName("body"));
		//body.getCssValue("position");
		assertThat(body.getCssValue("position"), is(position));
		assertThat(body.getLocation().y, is(offsetTop));
		assertThat(body.getLocation().x, is(offsetLeft));
	}

	/**
	 * bodyにスタイルが定義されている固定されている要素に対してisMoveオプションを指定して<br>
	 * 正しくスクリーンショットがとれていることを確認する。<br>
	 * 実行環境：IE7～11/FireFox/Chrome/Android 2.3, 4.0, 4.4/iOS 8.1<br>
	 * 期待結果：スクリーンショット取得後もbodyのスタイルが変わっていない
	 */
	@Test
	public void keepBodyStyleMargin() {
		driver.get(BASE_URL);

		final String position = "absolute";
		final String marginWidth = "20px";

		// 取得対象要素の座標を固定する。
		StringBuilder sb = new StringBuilder();
		sb.append("document.body.style.position = '");
		sb.append(position);
		sb.append("'; document.body.style.margin = '");
		sb.append(marginWidth);
		sb.append("';");
		System.out.println(sb.toString());
		driver.executeJavaScript(sb.toString());

		List<CompareTarget> targets = new ArrayList<CompareTarget>();
		targets.add(new CompareTarget(ScreenArea.of(SelectorType.ID, "about"), null, true));
		assertionView.assertView("CssSelector", targets);

		// スクリーンショット取得後もbody座標が保存されている
		WebElement body = driver.findElement(By.tagName("body"));
		//body.getCssValue("position");
		assertThat(body.getCssValue("position"), is(position));
		assertThat(body.getCssValue("margin"), is(String.valueOf(marginWidth)));
	}
}
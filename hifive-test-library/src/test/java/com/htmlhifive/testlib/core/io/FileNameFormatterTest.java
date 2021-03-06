/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.core.io;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.openqa.selenium.Platform;

import com.htmlhifive.testlib.core.model.IndexDomSelector;
import com.htmlhifive.testlib.core.model.SelectorType;
import com.htmlhifive.testlib.core.selenium.MrtCapabilities;
import com.htmlhifive.testlib.image.model.RectangleArea;

public class FileNameFormatterTest {

	/**
	 * 通常のフォーマットテスト（セレクタ）
	 */
	@Test
	public void testFormat_selector() throws Exception {
		MrtCapabilities capabilities = new MrtCapabilities(new HashMap<String, Object>());
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setBrowserName("firefox");
		capabilities.setVersion("38");

		PersistMetadata metadata = new PersistMetadata("testId", "testClass", "testMethod", "scId",
				new IndexDomSelector(SelectorType.TAG_NAME, "body", 1), null, capabilities);

		FileNameFormatter formatter = new FileNameFormatter(
				"{platformName}_{platformVersion}_{browserName}_{version}_{screenArea}.png");
		String result = formatter.format(metadata);
		assertThat(result, is("testMethod_scId_WINDOWS_firefox_38_TAG_NAME_body_[1].png"));
	}

	/**
	 * 通常のフォーマットテスト（矩形）
	 */
	@Test
	public void testFormat_rectangle() throws Exception {
		MrtCapabilities capabilities = new MrtCapabilities(new HashMap<String, Object>());
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setBrowserName("firefox");
		capabilities.setVersion("38");

		PersistMetadata metadata = new PersistMetadata("testId", "testClass", "testMethod", "scId", null,
				new RectangleArea(0, 10, 100, 1000), capabilities);

		FileNameFormatter formatter = new FileNameFormatter(
				"{platformName}_{platformVersion}_{browserName}_{version}_{screenArea}.png");
		String result = formatter.format(metadata);
		assertThat(result, is("testMethod_scId_WINDOWS_firefox_38_rect_0_10_100_1000.png"));
	}

	/**
	 * selendroidのフォーマットテスト
	 */
	@Test
	public void testFormat_selendroid() throws Exception {
		MrtCapabilities capabilities = new MrtCapabilities(new HashMap<String, Object>());
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setBrowserName("");
		capabilities.setCapability("deviceName", "ASUS Pad");
		capabilities.setCapability("platformVersion", "4.0.3");
		capabilities.setCapability("automationName", "Selendroid");

		PersistMetadata metadata = new PersistMetadata("testId", "testClass", "testMethod", "scId",
				new IndexDomSelector(SelectorType.TAG_NAME, "body", 1), null, capabilities);

		FileNameFormatter formatter = new FileNameFormatter(
				"{platformName}_{platformVersion}_{browserName}_{version}_{screenArea}.png");
		String result = formatter.format(metadata);
		assertThat(result, is("testMethod_scId_ANDROID_4.0.3_Selendroid_TAG_NAME_body_[1].png"));
	}

	/**
	 * ファイル名に利用できない文字のエスケープテスト
	 */
	@Test
	public void testFormat_escape() throws Exception {
		MrtCapabilities capabilities = new MrtCapabilities(new HashMap<String, Object>());
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setBrowserName("firefox");
		capabilities.setVersion("38");

		PersistMetadata metadata = new PersistMetadata("testId", "testClass", "testMethod", "scId",
				new IndexDomSelector(SelectorType.CSS_SELECTOR, "1\\\\2/3:4*5?6\"7<8>9|0", 1), null, capabilities);

		FileNameFormatter formatter = new FileNameFormatter(
				"{platformName}_{platformVersion}_{browserName}_{version}_{screenArea}.png");
		String result = formatter.format(metadata);
		assertThat(result, is("testMethod_scId_WINDOWS_firefox_38_CSS_SELECTOR_1-2-3-4-5-6-7-8-9-0_[1].png"));
	}

}
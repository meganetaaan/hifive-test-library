/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.core.selenium;

import java.awt.image.BufferedImage;
import java.net.URL;

import com.htmlhifive.testlib.common.exception.TestRuntimeException;

/**
 * iPhoneのSafariで利用する{@link org.openqa.selenium.WebDriver}
 */
class MrtIPhoneDriver extends SplitScreenshotWebDriver {

	private final int headerHeight;
	private final int footerHeight;

	/**
	 * コンストラクタ
	 * 
	 * @param remoteAddress RemoteWebDriverServerのアドレス
	 * @param capabilities Capability
	 */
	MrtIPhoneDriver(URL remoteAddress, MrtCapabilities capabilities) {
		super(remoteAddress, capabilities);

		Object headerHeightCapability = capabilities.getCapability("headerHeight");
		if (headerHeightCapability == null) {
			throw new TestRuntimeException("Capability \"headerHeight\" is required");
		} else {
			if (headerHeightCapability instanceof Number) {
				headerHeight = ((Number) headerHeightCapability).intValue();
			} else {
				headerHeight = Integer.parseInt(headerHeightCapability.toString());
			}
		}

		Object footerHeightCapability = capabilities.getCapability("footerHeight");
		if (footerHeightCapability == null) {
			throw new TestRuntimeException("Capability \"footerHeight\" is required");
		} else {
			if (footerHeightCapability instanceof Number) {
				footerHeight = ((Number) footerHeightCapability).intValue();
			} else {
				footerHeight = Integer.parseInt(footerHeightCapability.toString());
			}
		}
	}

	@Override
	protected boolean canHideScrollbar() {
		return false;
	}

	@Override
	protected int getHeaderHeight(long pageHeight, long scrollTop) {

		int currentHeaderHeight = headerHeight;
		if (scrollTop > 0) {
			// 最上部以外はヘッダを影ごと切り取る
			currentHeaderHeight += 2;
		}
		return currentHeaderHeight;
	}

	@Override
	protected int getFooterHeight(long pageHeight, long scrollTop, long windowHeight) {
		int currentFooterHeight = footerHeight;
		if (scrollTop + windowHeight < pageHeight) {
			// 最下部以外はフッタを影ごと切り取る
			currentFooterHeight += 2;
		}
		return currentFooterHeight;
	}

	@Override
	protected BufferedImage trimCaptureTop(double captureTop, long windowHeight, double scale, BufferedImage img) {
		// 下端の推定位置（次スクロール時にトップに来る位置）と、実際のキャプチャに写っている下端の位置を比較
		long calculatedBottomValue = Math.round((captureTop + windowHeight - 1) * scale);
		long actualBottomValue = Math.round(captureTop * scale) + img.getHeight();
		// 余分にキャプチャに写っていたら切り取っておく
		if (calculatedBottomValue < actualBottomValue) {
			return img.getSubimage(0, 0, img.getWidth(),
					(int) (img.getHeight() - (actualBottomValue - calculatedBottomValue)));
		} else {
			return img;
		}
	}

	@Override
	protected double calcScrollIncrementWithHeader(int imageHeight, double scale) {
		double scrollIncrement = super.calcScrollIncrementWithHeader(imageHeight, scale);
		return scrollIncrement - 1;
	}

	@Override
	protected int calcTrimTop(int imageNum, long windowHeight, long pageHeight, double scale) {
		// スクロール幅をずらした分、切り取り位置を調整する
		// 1スクロールにつき2pxずつずれていく
		return (int) Math.round((windowHeight - (pageHeight % windowHeight) - (imageNum - 1) * 2) * scale);
	}

	@Override
	protected double calcScale(double windowWidth, double imageWidth) {
		return imageWidth / windowWidth;
	}

	@Override
	protected MrtWebElement newMrtWebElement() {
		return new MrtIPhoneWebElement();
	}

}

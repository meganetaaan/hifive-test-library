/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.core.model;

import java.util.ArrayList;
import java.util.List;

import com.htmlhifive.testlib.core.selenium.MrtWebElement;
import com.htmlhifive.testlib.image.model.RectangleArea;

/**
 * スクリーンショット撮影用のパラメータークラス
 */
public class ScreenshotParams {

	private final ScreenAreaWrapper target;
	private final List<ScreenAreaWrapper> excludes;
	private final List<MrtWebElement> hiddenElements;
	private final boolean moveTarget;
	private final Integer index;

	private RectangleArea initialTargetArea;
	private List<RectangleArea> initialExcludeAreas;

	/**
	 * パラメータオブジェクトを生成します。
	 * 
	 * @param target 撮影対象の領域
	 * @param excludes 比較時に除外する領域
	 * @param hiddenElements 撮影時に非表示にする要素
	 * @param moveTarget 撮影時に対象を定位置に移動させるか否か
	 * @param index インデックス
	 */
	public ScreenshotParams(ScreenAreaWrapper target, List<ScreenAreaWrapper> excludes,
			List<MrtWebElement> hiddenElements, boolean moveTarget, Integer index) {
		this.target = target;
		this.excludes = excludes;
		this.hiddenElements = hiddenElements;
		this.moveTarget = moveTarget;
		this.index = index;
	}

	/**
	 * 撮影対象範囲、除外領域の矩形情報を更新します。
	 */
	public void updateInitialArea() {
		initialTargetArea = target.getArea();
		initialExcludeAreas = new ArrayList<RectangleArea>(excludes.size());
		for (ScreenAreaWrapper exclude : excludes) {
			initialExcludeAreas.add(exclude.getArea());
		}
	}

	/**
	 * 撮影対象の領域を取得します。
	 * 
	 * @return 撮影対象領域
	 */
	public ScreenAreaWrapper getTarget() {
		return target;
	}

	/**
	 * 比較時に除外する領域を取得します。
	 * 
	 * @return 比較時に除外する領域のリスト
	 */
	public List<ScreenAreaWrapper> getExcludes() {
		return excludes;
	}

	/**
	 * 撮影時に非表示にする要素を取得します。
	 * 
	 * @return 撮影時に非表示にする要素のリスト
	 */
	public List<MrtWebElement> getHiddenElements() {
		return hiddenElements;
	}

	/**
	 * 撮影時に対象を定位置に移動させるか否かを取得します。
	 * 
	 * @return 移動させる場合はtrue、させない場合はfalse
	 */
	public boolean isMoveTarget() {
		return moveTarget;
	}

	/**
	 * インデックスを取得します。
	 * 
	 * @return インデックス番号
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * 撮影の対象となる範囲を取得します。
	 * 
	 * @return 撮影対象の矩形範囲
	 */
	public RectangleArea getInitialTargetArea() {
		return initialTargetArea;
	}

	/**
	 * 比較時に除外する範囲を取得します。
	 * 
	 * @return 除外範囲のリスト
	 */
	public List<RectangleArea> getInitialExcludeAreas() {
		return initialExcludeAreas;
	}

}

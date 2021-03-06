/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.image.util;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * マーカーの範囲を表すクラス
 */
public class MarkerGroup {

	/**
	 * グループ化可能な距離 高速化メモリ節約のため、四角形を作るため誤差が出る
	 */
	private static final int GROUP_DISTANCE = 10;

	/**
	 * マーカーを付ける範囲を表す四角形
	 */
	private Rectangle rectangle;

	/**
	 * 中心点を指定してマーカーを生成します。
	 * 
	 * @param p 中心点
	 */
	public MarkerGroup(Point p) {
		rectangle = new Rectangle(p.x - GROUP_DISTANCE / 2, p.y - GROUP_DISTANCE / 2, GROUP_DISTANCE, GROUP_DISTANCE);
	}

	/**
	 * 中心点の座標を指定してマーカーを生成します。
	 * 
	 * @param x 中心点のx座標
	 * @param y 中心点のy座標
	 */
	public MarkerGroup(int x, int y) {
		this(new Point(x, y));
	}

	/**
	 * 指定されたマーカーと結合します。事前に、結合可能かどうかを{@link #canMarge(MarkerGroup)}で確認する必要があります。
	 * 
	 * @param markerGroup 結合するマーカー
	 */
	public void union(MarkerGroup markerGroup) {
		// 二つの四角形を結合する。事前に結合可能かチェックする。
		rectangle = rectangle.union(markerGroup.getRectangle());
	}

	/**
	 * 結合の条件を満たすかどうかチェックします。一方がもう一方を内包している、または交差している場合に結合可能です。
	 * 
	 * @param markerGroup 対象マーカー
	 * @return 結合できるか否か。結合可能な場合はtrue
	 */
	public boolean canMarge(MarkerGroup markerGroup) {

		// お互いを内包している場合は結合可能
		if (markerGroup.getRectangle().contains(this.getRectangle())
				|| this.getRectangle().contains(markerGroup.getRectangle())) {
			return true;
		}

		// 交差している場合は結合可能
		if (markerGroup.getRectangle().intersects(this.getRectangle())) {
			return true;
		}

		// それ以外は結合不可能
		return false;
	}

	/**
	 * マーカーの範囲を取得します。
	 * 
	 * @return マーカーを付ける矩形領域
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}

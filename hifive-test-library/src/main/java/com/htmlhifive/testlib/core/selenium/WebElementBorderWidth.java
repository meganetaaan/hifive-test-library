/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.core.selenium;

import java.io.Serializable;

/**
 * DOM要素のボーダーを表すクラス
 */
public class WebElementBorderWidth implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 上ボーダー幅
	 */
	private final double top;
	/**
	 * 右ボーダー幅
	 */
	private final double right;
	/**
	 * 下ボーダー幅
	 */
	private final double bottom;
	/**
	 * 左ボーダー幅
	 */
	private final double left;

	/**
	 * 指定したボーダー幅を持ったオブジェクトを生成します。
	 * 
	 * @param top 上ボーダー幅
	 * @param right 右ボーダー幅
	 * @param bottom 下ボーダー幅
	 * @param left 左ボーダー幅
	 */
	public WebElementBorderWidth(double top, double right, double bottom, double left) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	/**
	 * 上ボーダー幅を取得します。
	 * 
	 * @return 上ボーダー幅
	 */
	public double getTop() {
		return top;
	}

	/**
	 * 右ボーダー幅を取得します。
	 * 
	 * @return 右ボーダー幅
	 */
	public double getRight() {
		return right;
	}

	/**
	 * 下ボーダー幅を取得します。
	 * 
	 * @return 下ボーダー幅
	 */
	public double getBottom() {
		return bottom;
	}

	/**
	 * 左ボーダー幅を取得します。
	 * 
	 * @return 左ボーダー幅
	 */
	public double getLeft() {
		return left;
	}

	/**
	 * 同一かどうか調べます。
	 * 
	 * @param o 比較対象
	 * @return 上下左右全てのボーダー幅が一致すればtrue
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		WebElementBorderWidth that = (WebElementBorderWidth) o;

		if (Double.compare(that.top, top) != 0) {
			return false;
		}
		if (Double.compare(that.right, right) != 0) {
			return false;
		}
		if (Double.compare(that.bottom, bottom) != 0) {
			return false;
		}
		return Double.compare(that.left, left) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		final int hashPrime = 31;
		final int shiftBit = 32;
		temp = Double.doubleToLongBits(top);
		result = (int) (temp ^ (temp >>> shiftBit));
		temp = Double.doubleToLongBits(right);
		result = hashPrime * result + (int) (temp ^ (temp >>> shiftBit));
		temp = Double.doubleToLongBits(bottom);
		result = hashPrime * result + (int) (temp ^ (temp >>> shiftBit));
		temp = Double.doubleToLongBits(left);
		result = hashPrime * result + (int) (temp ^ (temp >>> shiftBit));
		return result;
	}

	@Override
	public String toString() {
		return "WebElementBorderWidth{" + "top=" + top + ", right=" + right + ", bottom=" + bottom + ", left=" + left
				+ '}';
	}

}

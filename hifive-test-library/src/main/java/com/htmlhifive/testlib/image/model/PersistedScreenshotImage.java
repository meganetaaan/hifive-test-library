/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */

package com.htmlhifive.testlib.image.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.htmlhifive.testlib.common.exception.TestRuntimeException;
import com.htmlhifive.testlib.core.io.PersistMetadata;
import com.htmlhifive.testlib.core.io.Persister;
import com.htmlhifive.testlib.core.io.ResourceUnavailableException;

/**
 * {@link Persister}からの読み込みが可能なスクリーンショット画像を表すクラス
 */
public class PersistedScreenshotImage extends ScreenshotImage {

	private static final Logger LOG = LoggerFactory.getLogger(PersistedScreenshotImage.class);

	protected final Persister persister;
	protected final PersistMetadata metadata;

	/**
	 * メタデータのみを持ったオブジェクトを生成します。
	 *
	 * @param persister 保存に用いるPersister
	 * @param metadata スクリーンショットのメタデータ
	 */
	public PersistedScreenshotImage(Persister persister, PersistMetadata metadata) {
		this(persister, metadata, null);
	}

	/**
	 * メタデータ、画像を持ったオブジェクトを生成します。
	 *
	 * @param persister 保存に用いるPersister
	 * @param metadata スクリーンショットのメタデータ
	 * @param image スクリーンショット画像
	 */
	public PersistedScreenshotImage(Persister persister, PersistMetadata metadata, BufferedImage image) {
		if (persister == null) {
			throw new IllegalArgumentException("persister");
		}
		if (metadata == null) {
			throw new IllegalArgumentException("metadata");
		}

		this.persister = persister;
		this.metadata = metadata;
		this.image = image;
	}

	/**
	 * メタデータを取得します。
	 *
	 * @return メタデータ
	 */
	public PersistMetadata getMetadata() {
		return metadata;
	}

	/**
	 * スクリーンショットの画像を取得します。
	 *
	 * @return スクリーンショットの画像
	 */
	@Override
	public BufferedImage get() {
		if (image != null) {
			return image;
		}

		InputStream in = null;
		try {
			in = getImageStream();
			image = ImageIO.read(in);
		} catch (IOException e) {
			throw new TestRuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOG.error("", e);
				}
			}
		}

		return image;
	}

	/**
	 * スクリーンショット画像を取得できるストリームを取得します。
	 *
	 * @return 画像の入力ストリーム
	 */
	@Override
	public InputStream getAsStream() {
		try {
			return getImageStream();
		} catch (ResourceUnavailableException e) {
			LOG.debug("getAsStream from resource failed.");
		}

		// Create stream from image
		if (image != null) {
			return getInputStreamFromImage(image);
		}

		throw new ResourceUnavailableException("Screenshot not found");
	}

	/**
	 * スクリーンショット画像のストリームを取得します。
	 *
	 * @return スクリーンショット画像のストリーム
	 */
	protected InputStream getImageStream() {
		return persister.getImageStream(metadata);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PersistedScreenshotImage that = (PersistedScreenshotImage) o;

		if (!persister.equals(that.persister)) {
			return false;
		}
		return metadata.equals(that.metadata);
	}

	@Override
	public int hashCode() {
		int result = persister.hashCode();
		final int hashPrime = 31;
		result = hashPrime * result + metadata.hashCode();
		return result;
	}

}

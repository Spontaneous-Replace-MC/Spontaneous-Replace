/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.sr;

import java.util.Random;

/**
 * <h2 style="color:FFC800">生成柏林噪声</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class PerlinNoise {
	private final int sideSize;
	private final int octaves;
	private final float[] seed;
	private final float bias;
	
	public PerlinNoise(long seed, int noiseSize, int octaves, float bias) {
		this.sideSize = noiseSize;
		this.octaves = octaves;
		this.seed = createNoiseSeed(seed, (int) Math.pow(this.sideSize, 2));
		this.bias = bias;
	}
	
	public float[] createNoiseSeed(long seed, int size) {
		float[] noiseSeed = new float[size];
		Random random = new Random(seed);
		for (int count = 0; count < size; count++) noiseSeed[count] = random.nextFloat(1);
		return noiseSeed;
	}
	
	public float[][] perlinNoise() {
		float[][] perlinNoise = new float[sideSize][sideSize];
		
		// 遍历每一个噪声节点
		for (int x = 0; x < sideSize; x++) {
			for (int y = 0; y < sideSize; y++) {
				float noise = 0;
				float scale = 1;
				float scaleAcc = 0;
				// 使用八度系统进行噪声采样
				for (int o = 0; o < octaves; o++) {
					// 获取取样间隔，取样间隔从高到低
					int pitch = sideSize >> o;
					// 获取噪声节点前的取样点
					int frontPointX = (x / pitch) * pitch;
					int frontPointY = (y / pitch) * pitch;
					// 获取噪声节点后的取样点
					int backPointX = (frontPointX + pitch) % sideSize;
					int backPointY = (frontPointY + pitch) % sideSize;
					// 节点离前取样点的距离百分比
					float distanceX = (float) (x - frontPointX) / pitch;
					float distanceY = (float) (y - frontPointY) / pitch;
					// 获取当前节点的取样值
					float sampleT = (1 - distanceX) * seed[frontPointY * sideSize + frontPointX]
							+ distanceX * seed[frontPointY * sideSize + backPointX];
					float sampleB = (1 - distanceX) * seed[backPointY * sideSize + frontPointX]
							+ distanceX * seed[backPointY * sideSize + backPointX];
					// 叠加当前取样
					noise += (distanceY * (sampleB - sampleT) + sampleT) * scale;
					// 叠加当前倍率
					scaleAcc += scale;
					// 减少叠加倍率
					scale /= bias;
				}
				perlinNoise[x][y] = noise / scaleAcc;
			}
		}
		
		return perlinNoise;
	}
}

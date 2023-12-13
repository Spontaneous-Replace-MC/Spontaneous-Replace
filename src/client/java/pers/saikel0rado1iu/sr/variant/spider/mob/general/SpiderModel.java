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

package pers.saikel0rado1iu.sr.variant.spider.mob.general;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

import static pers.saikel0rado1iu.silk.util.MathUtil.toFloat;
import static pers.saikel0rado1iu.silk.util.MathUtil.toRadians;

/**
 * <h2 style="color:FFC800">自然更替的蜘蛛模型类</h2>
 * <p style="color:FFC800">自然更替的蜘蛛的基础实体模型和基础动作分配</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public abstract class SpiderModel<S extends SpiderEntity> extends SinglePartEntityModel<S> {
	protected final ModelPart root;
	protected final ModelPart base;
	protected final ModelPart body;
	protected final ModelPart head;
	protected final ModelPart rightJaw;
	protected final ModelPart leftJaw;
	protected final ModelPart rightChelicera;
	protected final ModelPart leftChelicera;
	protected final ModelPart abdomen;
	protected final ModelPart rightHindLeg;
	protected final ModelPart leftHindLeg;
	protected final ModelPart rightMiddleLeg;
	protected final ModelPart leftMiddleLeg;
	protected final ModelPart rightMiddleFrontLeg;
	protected final ModelPart leftMiddleFrontLeg;
	protected final ModelPart rightFrontLeg;
	protected final ModelPart leftFrontLeg;
	
	/**
	 * 构建添加模组组件
	 *
	 * @param root 根组件
	 */
	public SpiderModel(ModelPart root) {
		this.root = root;
		
		base = root.getChild("base");
		body = base.getChild("body");
		head = body.getChild("head");
		rightJaw = head.getChild("rightJaw");
		leftJaw = head.getChild("leftJaw");
		rightChelicera = head.getChild("rightChelicera");
		leftChelicera = head.getChild("leftChelicera");
		body.getChild("chest");
		abdomen = body.getChild("abdomen");
		rightHindLeg = base.getChild("rightHindLeg");
		leftHindLeg = base.getChild("leftHindLeg");
		rightMiddleLeg = base.getChild("rightMiddleLeg");
		leftMiddleLeg = base.getChild("leftMiddleLeg");
		rightMiddleFrontLeg = base.getChild("rightMiddleFrontLeg");
		leftMiddleFrontLeg = base.getChild("leftMiddleFrontLeg");
		rightFrontLeg = base.getChild("rightFrontLeg");
		leftFrontLeg = base.getChild("leftFrontLeg");
	}
	
	/**
	 * 设置组件大小，纹理偏移和模型数据
	 *
	 * @return 纹理大小
	 */
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		
		getBodyTexturedModelData(modelData);
		getHeadTexturedModelData(modelData);
		getLegsTexturedModelData(modelData);
		
		return TexturedModelData.of(modelData, 64, 32);
	}
	
	/**
	 * 设置身体组件大小，纹理偏移和模型数据
	 *
	 * @param modelData 模型数据
	 */
	public static void getBodyTexturedModelData(ModelData modelData) {
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.0F, 0.0F));
		ModelPartData body = base.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		body.addChild("head", ModelPartBuilder.create().uv(32, 4).cuboid(-4.0F, -7.0F, -8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(2, 13).cuboid(-3.0F, -6.999F, -8.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 13).cuboid(1.0F, -6.0F, -8.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 13).cuboid(-2.0F, -6.0F, -8.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 13).cuboid(2.0F, -6.999F, -8.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 15).cuboid(3.0F, -5.0F, -8.75F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 15).cuboid(-4.5F, -5.0F, -8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 15).cuboid(3.5F, -5.0F, -8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(6, 15).cuboid(-4.0F, -5.0F, -8.75F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(1, 20).cuboid(-2.0F, -4.0F, -9.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, -3.0F));
		body.addChild("chest", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		body.addChild("abdomen", ModelPartBuilder.create().uv(0, 12).cuboid(-5.0F, -7.0F, 0.0F, 10.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 3.0F));
	}
	
	/**
	 * 设置头部肢体组件大小，纹理偏移和模型数据
	 *
	 * @param modelData 模型数据
	 */
	public static void getHeadTexturedModelData(ModelData modelData) {
		ModelPartData head = modelData.getRoot().getChild("base").getChild("body").getChild("head");
		head.addChild("rightJaw", ModelPartBuilder.create().uv(56, 1).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, -8.0F));
		head.addChild("leftJaw", ModelPartBuilder.create().uv(56, 6).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, -8.0F));
		head.addChild("rightChelicera", ModelPartBuilder.create().uv(36, 20).cuboid(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -2.0F, -1.0F));
		head.addChild("leftChelicera", ModelPartBuilder.create().uv(36, 20).cuboid(-1.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -2.0F, -1.0F));
	}
	
	/**
	 * 设置肢体组件大小，纹理偏移和模型数据
	 *
	 * @param modelData 模型数据
	 */
	public static void getLegsTexturedModelData(ModelData modelData) {
		ModelPartData base = modelData.getRoot().getChild("base");
		base.addChild("rightHindLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 4.0F));
		base.addChild("leftHindLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, 4.0F));
		base.addChild("rightMiddleLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 1.0F));
		base.addChild("leftMiddleLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, 1.0F));
		base.addChild("rightMiddleFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -2.0F));
		base.addChild("leftMiddleFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, -2.0F));
		base.addChild("rightFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -5.0F));
		base.addChild("leftFrontLeg", ModelPartBuilder.create().uv(18, 0).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, -5.0F));
	}
	
	/**
	 * 设置动画
	 */
	@Override
	public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		base.setAngles(0, 0, 0);
		base.setPivot(0, 15F, 0);
		body.setAngles(0, 0, 0);
		body.setPivot(0, 2, 0);
		head.setAngles(toRadians(-5), 0, 0);
		head.setPivot(0.0F, 3.0F, -3.0F);
		rightJaw.setAngles(toRadians(15), 0, toRadians(-15));
		rightJaw.setPivot(-2.0F, 0.0F, -8.0F);
		leftJaw.setAngles(toRadians(15), 0, toRadians(15));
		leftJaw.setPivot(2.0F, 0.0F, -8.0F);
		rightChelicera.setAngles(toRadians(65), toRadians(-55), toRadians(-60));
		rightChelicera.setPivot(-5.0F + 1, -2.0F - 2, -1.0F);
		leftChelicera.setAngles(toRadians(65), toRadians(55), toRadians(60));
		leftChelicera.setPivot(5.0F - 1, -2.0F - 2, -1.0F);
		abdomen.setAngles(toRadians(12.5), 0, 0);
		abdomen.setPivot(0.0F, 3.0F, 3.0F);
		abdomen.xScale = abdomen.yScale = abdomen.zScale = 1;
		rightHindLeg.setAngles(toRadians(-20), toRadians(50), toRadians(-60));
		rightHindLeg.setPivot(-4.0F - 2, 0, 4.0F);
		leftHindLeg.setAngles(toRadians(-20), toRadians(-50), toRadians(60));
		leftHindLeg.setPivot(4.0F + 2, 0, 4.0F);
		rightMiddleLeg.setAngles(toRadians(0), toRadians(20), toRadians(-42.5));
		rightMiddleLeg.setPivot(-4.0F, -1, 1.0F);
		leftMiddleLeg.setAngles(toRadians(0), toRadians(-20), toRadians(42.5));
		leftMiddleLeg.setPivot(4.0F, -1, 1.0F);
		rightMiddleFrontLeg.setAngles(toRadians(0), toRadians(-12.5), toRadians(-40));
		rightMiddleFrontLeg.setPivot(-4.0F, -1, -2.0F);
		leftMiddleFrontLeg.setAngles(toRadians(0), toRadians(12.5), toRadians(40));
		leftMiddleFrontLeg.setPivot(4.0F, -1, -2.0F);
		rightFrontLeg.setAngles(toRadians(0), toRadians(-37.5), toRadians(-55));
		rightFrontLeg.setPivot(-4.0F, -1, -5.0F + 1);
		leftFrontLeg.setAngles(toRadians(0), toRadians(37.5), toRadians(55));
		leftFrontLeg.setPivot(4.0F, -1, -5.0F + 1);
		
		setTurn(headYaw, headPitch);
	}
	
	/**
	 * 设置蜘蛛转头
	 */
	protected void setTurn(float headYaw, float headPitch) {
		// 俯仰设置
		if (headPitch != 0) {
			// 身体俯仰设置
			body.pitch = toRadians(headPitch * 0.666);
			// 腹部俯仰设置
			if (headPitch > 0) {
				abdomen.pitch = toRadians(headPitch * 0.333 + 12.5);
				// 头部俯视缩头
				head.setPivot(head.pivotX, head.pivotY - headPitch / 30, head.pivotZ + headPitch / 10);
			} else
				abdomen.pitch = toRadians(-headPitch + 12.5);
			// 头部俯仰设置
			head.pitch = toRadians(headPitch * 0.333 - 5);
			// 肢体俯仰设置
			if (headPitch > 0)
				headPitch = -headPitch;
			rightChelicera.setAngles(
					rightChelicera.pitch - toRadians(headPitch * 0.417),
					rightChelicera.yaw - toRadians(headPitch * 0.667),
					rightChelicera.roll + toRadians(headPitch * 0.667));
			leftChelicera.setAngles(
					leftChelicera.pitch - toRadians(headPitch * 0.417),
					leftChelicera.yaw + toRadians(headPitch * 0.667),
					leftChelicera.roll - toRadians(headPitch * 0.667));
			rightFrontLeg.setPivot(rightFrontLeg.pivotX, toFloat(rightFrontLeg.pivotY + headPitch * 0.067), rightFrontLeg.pivotZ);
			rightFrontLeg.setAngles(
					rightFrontLeg.pitch,
					rightFrontLeg.yaw - toRadians(headPitch / 2),
					rightFrontLeg.roll + toRadians(headPitch * 0.25));
			leftFrontLeg.setPivot(leftFrontLeg.pivotX, toFloat(leftFrontLeg.pivotY + headPitch * 0.067), leftFrontLeg.pivotZ);
			leftFrontLeg.setAngles(
					leftFrontLeg.pitch,
					leftFrontLeg.yaw + toRadians(headPitch / 2),
					leftFrontLeg.roll - toRadians(headPitch * 0.25));
			rightMiddleFrontLeg.setPivot(rightMiddleFrontLeg.pivotX, toFloat(rightMiddleFrontLeg.pivotY + headPitch * 0.033), toFloat(rightMiddleFrontLeg.pivotZ + headPitch * 0.016));
			rightMiddleFrontLeg.setAngles(
					rightMiddleFrontLeg.pitch,
					rightMiddleFrontLeg.yaw,
					rightMiddleFrontLeg.roll + toRadians(headPitch * 0.25));
			leftMiddleFrontLeg.setPivot(leftMiddleFrontLeg.pivotX, toFloat(leftMiddleFrontLeg.pivotY + headPitch * 0.033), toFloat(leftMiddleFrontLeg.pivotZ - headPitch * 0.016));
			leftMiddleFrontLeg.setAngles(
					leftMiddleFrontLeg.pitch,
					leftMiddleFrontLeg.yaw,
					leftMiddleFrontLeg.roll - toRadians(headPitch * 0.25));
		}
		// 转头设置
		if (headYaw != 0) {
			// 如果转头角度大于 50° 则停止转头, 理论上蜘蛛几乎不转头
			if (headYaw > 50)
				headYaw = 50;
			else if (headYaw < -50)
				headYaw = -50;
			// 身体转头设置
			body.yaw = toRadians(headYaw);
			// 肢体转头设置
			if (headYaw > 0) {
				rightJaw.setPivot(rightJaw.pivotX, toFloat(rightJaw.pivotY - headYaw * 0.017), rightJaw.pivotZ);
				rightJaw.setAngles(
						rightJaw.pitch + toRadians(-headYaw * 1.667),
						rightJaw.yaw + toRadians(0),
						rightJaw.roll + toRadians(headYaw * 0.333));
				rightChelicera.setAngles(
						rightChelicera.pitch + toRadians(headYaw * 0.222),
						rightChelicera.yaw + toRadians(headYaw * 0.778),
						rightChelicera.roll + toRadians(-headYaw * 0.333));
				rightHindLeg.setPivot(toFloat(rightHindLeg.pivotX + headYaw * 0.111), toFloat(rightHindLeg.pivotY + headYaw * 0.022), toFloat(rightHindLeg.pivotZ + headYaw * 0.067));
				rightHindLeg.setAngles(
						rightHindLeg.pitch + toRadians(headYaw * 0.22),
						rightHindLeg.yaw + toRadians(-headYaw * 0.333),
						rightHindLeg.roll + toRadians(headYaw * 0.5));
				leftHindLeg.setPivot(toFloat(leftHindLeg.pivotX + headYaw * 0.022), toFloat(leftHindLeg.pivotY + headYaw * 0.022), toFloat(leftHindLeg.pivotZ - headYaw * 0.022));
				leftHindLeg.setAngles(
						leftHindLeg.pitch + toRadians(headYaw * 0.111),
						leftHindLeg.yaw + toRadians(-headYaw * 0.222),
						leftHindLeg.roll + toRadians(0));
				rightMiddleLeg.setPivot(toFloat(rightMiddleLeg.pivotX + headYaw * 0.039), toFloat(rightMiddleLeg.pivotY + headYaw * 0.022), toFloat(rightMiddleLeg.pivotZ + headYaw * 0.056));
				rightMiddleLeg.setAngles(
						rightMiddleLeg.pitch + toRadians(headYaw * 0.056),
						rightMiddleLeg.yaw + toRadians(-headYaw * 0.167),
						rightMiddleLeg.roll + toRadians(headYaw * 0.167));
				leftMiddleLeg.setPivot(toFloat(leftMiddleLeg.pivotX + headYaw * 0.022), toFloat(leftMiddleLeg.pivotY + headYaw * 0.022), toFloat(leftMiddleLeg.pivotZ - headYaw * 0.089));
				leftMiddleLeg.setAngles(
						leftMiddleLeg.pitch + toRadians(headYaw * 0.033),
						leftMiddleLeg.yaw + toRadians(-headYaw * 0.278),
						leftMiddleLeg.roll + toRadians(-headYaw * 0.056));
				rightMiddleFrontLeg.setPivot(toFloat(rightMiddleFrontLeg.pivotX - headYaw * 0.022), toFloat(rightMiddleFrontLeg.pivotY + headYaw * 0.022), toFloat(rightMiddleFrontLeg.pivotZ + headYaw * 0.067));
				rightMiddleFrontLeg.setAngles(
						rightMiddleFrontLeg.pitch + toRadians(0),
						rightMiddleFrontLeg.yaw + toRadians(-headYaw * 0.278),
						rightMiddleFrontLeg.roll + toRadians(0));
				leftMiddleFrontLeg.setPivot(toFloat(leftMiddleFrontLeg.pivotX - headYaw * 0.044), toFloat(leftMiddleFrontLeg.pivotY + headYaw * 0.044), toFloat(leftMiddleFrontLeg.pivotZ - headYaw * 0.044));
				leftMiddleFrontLeg.setAngles(
						leftMiddleFrontLeg.pitch + toRadians(-headYaw * 0.033),
						leftMiddleFrontLeg.yaw + toRadians(-headYaw * 0.017),
						leftMiddleFrontLeg.roll + toRadians(-headYaw * 0.222));
				rightFrontLeg.setPivot(toFloat(rightFrontLeg.pivotX - headYaw * 0.067), toFloat(rightFrontLeg.pivotY + headYaw * 0.067), toFloat(rightFrontLeg.pivotZ + headYaw * 0.078));
				rightFrontLeg.setAngles(
						rightFrontLeg.pitch + toRadians(-headYaw * 0.111),
						rightFrontLeg.yaw + toRadians(-headYaw * 0.5),
						rightFrontLeg.roll + toRadians(0));
				leftFrontLeg.setPivot(toFloat(leftFrontLeg.pivotX - headYaw * 0.078), toFloat(leftFrontLeg.pivotY + headYaw * 0.033), toFloat(leftFrontLeg.pivotZ - headYaw * 0.056));
				leftFrontLeg.setAngles(
						leftFrontLeg.pitch + toRadians(-headYaw * 0.167),
						leftFrontLeg.yaw + toRadians(-headYaw * 0.222),
						leftFrontLeg.roll + toRadians(-headYaw * 0.389));
			} else {
				headYaw = -headYaw;
				leftJaw.setPivot(leftJaw.pivotX, toFloat(leftJaw.pivotY - headYaw * 0.017), leftJaw.pivotZ);
				leftJaw.setAngles(
						leftJaw.pitch + toRadians(-headYaw * 1.667),
						leftJaw.yaw - toRadians(0),
						leftJaw.roll - toRadians(headYaw * 0.333));
				leftChelicera.setAngles(
						leftChelicera.pitch + toRadians(headYaw * 0.222),
						leftChelicera.yaw - toRadians(headYaw * 0.778),
						leftChelicera.roll - toRadians(-headYaw * 0.333));
				leftHindLeg.setPivot(toFloat(leftHindLeg.pivotX - headYaw * 0.111), toFloat(leftHindLeg.pivotY + headYaw * 0.022), toFloat(leftHindLeg.pivotZ + headYaw * 0.067));
				leftHindLeg.setAngles(
						leftHindLeg.pitch + toRadians(headYaw * 0.22),
						leftHindLeg.yaw - toRadians(-headYaw * 0.333),
						leftHindLeg.roll - toRadians(headYaw * 0.5));
				rightHindLeg.setPivot(toFloat(rightHindLeg.pivotX - headYaw * 0.022), toFloat(rightHindLeg.pivotY + headYaw * 0.022), toFloat(rightHindLeg.pivotZ - headYaw * 0.022));
				rightHindLeg.setAngles(
						rightHindLeg.pitch + toRadians(headYaw * 0.111),
						rightHindLeg.yaw - toRadians(-headYaw * 0.222),
						rightHindLeg.roll - toRadians(0));
				leftMiddleLeg.setPivot(toFloat(leftMiddleLeg.pivotX - headYaw * 0.039), toFloat(leftMiddleLeg.pivotY + headYaw * 0.022), toFloat(leftMiddleLeg.pivotZ + headYaw * 0.056));
				leftMiddleLeg.setAngles(
						leftMiddleLeg.pitch + toRadians(headYaw * 0.056),
						leftMiddleLeg.yaw - toRadians(-headYaw * 0.167),
						leftMiddleLeg.roll - toRadians(headYaw * 0.167));
				rightMiddleLeg.setPivot(toFloat(rightMiddleLeg.pivotX - headYaw * 0.022), toFloat(rightMiddleLeg.pivotY + headYaw * 0.022), toFloat(rightMiddleLeg.pivotZ - headYaw * 0.089));
				rightMiddleLeg.setAngles(
						rightMiddleLeg.pitch + toRadians(headYaw * 0.033),
						rightMiddleLeg.yaw - toRadians(-headYaw * 0.278),
						rightMiddleLeg.roll - toRadians(-headYaw * 0.056));
				leftMiddleFrontLeg.setPivot(toFloat(leftMiddleFrontLeg.pivotX + headYaw * 0.022), toFloat(leftMiddleFrontLeg.pivotY + headYaw * 0.022), toFloat(leftMiddleFrontLeg.pivotZ + headYaw * 0.067));
				leftMiddleFrontLeg.setAngles(
						leftMiddleFrontLeg.pitch + toRadians(0),
						leftMiddleFrontLeg.yaw - toRadians(-headYaw * 0.278),
						leftMiddleFrontLeg.roll - toRadians(0));
				rightMiddleFrontLeg.setPivot(toFloat(rightMiddleFrontLeg.pivotX + headYaw * 0.044), toFloat(rightMiddleFrontLeg.pivotY + headYaw * 0.044), toFloat(rightMiddleFrontLeg.pivotZ - headYaw * 0.044));
				rightMiddleFrontLeg.setAngles(
						rightMiddleFrontLeg.pitch + toRadians(-headYaw * 0.033),
						rightMiddleFrontLeg.yaw - toRadians(-headYaw * 0.017),
						rightMiddleFrontLeg.roll - toRadians(-headYaw * 0.222));
				leftFrontLeg.setPivot(toFloat(leftFrontLeg.pivotX + headYaw * 0.067), toFloat(leftFrontLeg.pivotY + headYaw * 0.067), toFloat(leftFrontLeg.pivotZ + headYaw * 0.078));
				leftFrontLeg.setAngles(
						leftFrontLeg.pitch + toRadians(-headYaw * 0.111),
						leftFrontLeg.yaw - toRadians(-headYaw * 0.5),
						leftFrontLeg.roll - toRadians(0));
				rightFrontLeg.setPivot(toFloat(rightFrontLeg.pivotX + headYaw * 0.078), toFloat(rightFrontLeg.pivotY + headYaw * 0.033), toFloat(rightFrontLeg.pivotZ - headYaw * 0.056));
				rightFrontLeg.setAngles(
						rightFrontLeg.pitch + toRadians(-headYaw * 0.167),
						rightFrontLeg.yaw - toRadians(-headYaw * 0.222),
						rightFrontLeg.roll - toRadians(-headYaw * 0.389));
			}
		}
	}
	
	/**
	 * 获取组件
	 *
	 * @return 根组件
	 */
	@Override
	public ModelPart getPart() {
		return root;
	}
}

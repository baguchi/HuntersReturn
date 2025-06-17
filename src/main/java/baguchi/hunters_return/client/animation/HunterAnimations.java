package baguchi.hunters_return.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class HunterAnimations {
        public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(4.04F).looping()
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(2.5F, 15.91F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(2.5F, 17.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.68F, KeyframeAnimations.degreeVec(2.5F, 17.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.8F, KeyframeAnimations.degreeVec(2.5F, 2.92F, -2.08F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.92F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.28F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.44F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-0.5F, 12.5F, -2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.degreeVec(-0.5F, 12.5F, -2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.degreeVec(0.33F, -37.5F, 3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(2.08F, KeyframeAnimations.degreeVec(0.5F, -47.5F, 4.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.2F, KeyframeAnimations.degreeVec(0.5F, -47.5F, 4.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.44F, KeyframeAnimations.degreeVec(-0.81F, 7.19F, -1.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.52F, KeyframeAnimations.degreeVec(-1.0F, 15.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.degreeVec(-1.0F, 15.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(4.04F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftEye", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("rightEye", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.72F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.68F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(3.8F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(-7.5F, 5.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(17.5F, 5.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(-7.5F, 5.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.92F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.76F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.88F, KeyframeAnimations.degreeVec(20.0F, -15.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.12F, KeyframeAnimations.degreeVec(-7.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.32F, KeyframeAnimations.degreeVec(30.0F, -15.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.52F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(15.0F, 15.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(15.0F, 15.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.92F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.76F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.88F, KeyframeAnimations.degreeVec(-20.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.12F, KeyframeAnimations.degreeVec(10.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.32F, KeyframeAnimations.degreeVec(-15.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.52F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(0.64F).looping()
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(3.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.7F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.05F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.05F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.7F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.7F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.7F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.5F, 1.7F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.5F, -2.3F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.3F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.0F, 0.5F, 1.7F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, 0.8F, 1.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.0F, 1.5F, 1.7F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 3.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -6.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 3.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.24F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, -0.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-11.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-1.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-11.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-50.0F, 5.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(45.0F, 16.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-50.0F, 5.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(45.0F, -16.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-50.0F, -5.0F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(45.0F, -16.0F, 7.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition left_attack_range_charge = AnimationDefinition.Builder.withLength(0.72F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-10.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(2.5F, -25.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(10.0F, 62.5F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, 85.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, -47.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, -52.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, -52.5F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -85.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -85.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-12.5F, -15.0F, -95.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(-5.0F, -15.0F, -96.43F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-5.0F, -15.0F, -95.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.04F, KeyframeAnimations.posVec(0.75F, 0.13F, -2.37F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.5F, 0.25F, -2.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(3.0F, 0.5F, -3.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(9.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.posVec(6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.posVec(6.75F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition right_attack_range_charge = AnimationDefinition.Builder.withLength(0.72F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(2.5F, 25.0F, 7.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-10.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(10.0F, -62.5F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, -85.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 47.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, 52.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 52.5F, 5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-12.5F, 15.0F, 95.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(-5.0F, 15.0F, 96.43F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-5.0F, 15.0F, 95.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.04F, KeyframeAnimations.posVec(-0.75F, 0.13F, -2.37F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.5F, 0.25F, -2.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(-3.0F, 0.5F, -3.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-9.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.posVec(-6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.posVec(-6.75F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(-6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 85.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 85.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
            .build();

        public static final AnimationDefinition left_shot = AnimationDefinition.Builder.withLength(1.0F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-10.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, -25.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(2.5F, -25.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 85.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(25.0F, 82.5F, 27.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-62.5F, 72.5F, -60.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-47.5F, 77.5F, -45.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(-7.5F, 67.5F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -52.5F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(0.0F, -52.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, -52.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, -52.5F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, -45.0F, -1.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -77.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -112.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, -15.0F, -95.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-32.5F, -17.5F, -110.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-60.0F, -20.0F, -117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-50.0F, -15.0F, -117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition right_shot = AnimationDefinition.Builder.withLength(1.0F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 25.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(2.5F, 25.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-1.65F, 0.0F, 2.2F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-10.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(1.5F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -85.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(25.0F, -82.5F, -27.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-62.5F, -72.5F, 60.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-47.5F, -77.5F, 45.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(-7.5F, -67.5F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 52.5F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(0.0F, 52.5F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 52.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 52.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 1.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 15.0F, 95.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-32.5F, 17.5F, 110.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-60.0F, 20.0F, 117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-50.0F, 15.0F, 117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-6.5F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, -90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("leftHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 77.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 117.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 112.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-0.5F, 1.5F, -0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition right_walk_attack = AnimationDefinition.Builder.withLength(0.64F).looping()
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, 45.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-12.5F, 45.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(42.5F, 45.0F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-22.5F, 45.0F, -12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-0.4F, 0.6F, 0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(0.82F, 1.5F, 0.79F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.5F, 25.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(35.0F, 32.5F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-32.5F, 17.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(32.5F, 25.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.8F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.0F, 1.0F, 1.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.71F, 1.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.5F, -1.0F, -0.1F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.8F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.0F, 32.5F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(4.0F, 45.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(4.0F, 50.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(4.0F, 38.75F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(4.0F, 32.5F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(55.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(40.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, -40.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(7.5F, -47.5F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(7.5F, -40.0F, -0.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 15.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-7.5F, 10.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-17.5F, 12.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-17.5F, 12.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-12.5F, 15.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-190.0F, -127.5F, 380.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-1.5F, 2.0F, -2.3F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition left_walk_attack = AnimationDefinition.Builder.withLength(0.64F).looping()
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.5F, -25.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(35.0F, -32.5F, -15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-32.5F, -17.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(32.5F, -25.0F, -12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.8F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.0F, 1.0F, 1.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-0.71F, 1.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(-0.5F, -1.0F, -0.1F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.8F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, -45.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-12.5F, -45.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(42.5F, -45.0F, -15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-22.5F, -45.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.4F, 0.6F, 0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(-0.82F, 1.5F, 0.79F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.0F, -32.5F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(4.0F, -45.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(4.0F, -50.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(4.0F, -38.75F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(4.0F, -32.5F, 1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("cape", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(55.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(40.0F, -22.5F, 15.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 40.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(7.5F, 47.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(7.5F, 40.0F, 0.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-190.0F, 127.5F, -380.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.5F, 2.0F, -2.3F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, -15.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-7.5F, -10.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.32F, KeyframeAnimations.degreeVec(-17.5F, -12.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-17.5F, -12.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-12.5F, -15.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition right_attack_melee = AnimationDefinition.Builder.withLength(1.04F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-5.0F, 2.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(15.0F, 5.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(14.5F, 5.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(15.5F, 5.0F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.2F, 0.0F, -4.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.2F, 0.0F, -4.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-25.0F, 12.5F, -8.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-26.75F, 10.0F, -11.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(33.0F, 2.5F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(41.5F, 1.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(41.5F, 1.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(39.0F, 1.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.degreeVec(36.5F, 2.5F, -21.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(31.5F, 2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(25.0F, -1.0F, -16.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(0.55F, 1.1F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(-0.2F, 0.89F, -0.55F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.posVec(-0.3F, 0.4F, -0.7F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-0.3F, 0.25F, -0.91F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.posVec(-0.2F, 0.1F, -0.6F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(-0.3F, 0.09F, -0.4F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.76F, KeyframeAnimations.posVec(0.0F, -0.05F, -0.35F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(0.21F, -0.15F, -0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(45.0F, -20.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(17.5F, -5.0F, 25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 19.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.degreeVec(-10.0F, 2.5F, 19.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 19.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.76F, KeyframeAnimations.degreeVec(-4.37F, 2.5F, 15.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(-8.75F, 2.14F, 13.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.84F, KeyframeAnimations.degreeVec(-4.79F, 1.78F, 11.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(-0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(-0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-67.0F, -2.5F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(62.5F, -40.0F, -80.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(77.5F, -27.5F, -72.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(85.0F, -27.5F, -67.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.88F, KeyframeAnimations.degreeVec(-42.5F, -5.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(-160.0F, 32.5F, -75.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-162.5F, 52.5F, -67.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(-42.5F, -37.5F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-37.5F, -37.5F, -27.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(-30.0F, -37.5F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-30.0F, -37.5F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-35.0F, -37.5F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-32.5F, -37.5F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(-0.75F, 2.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-0.75F, 2.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(-0.75F, 0.6F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-0.75F, 0.6F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("rightHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(15.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(15.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.92F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("rightHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, -1.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, -1.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.92F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition left_attack_melee = AnimationDefinition.Builder.withLength(1.04F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-5.0F, -2.5F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(15.0F, -5.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(15.0F, -5.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(14.5F, 5.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(15.5F, 5.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-0.2F, 0.0F, -4.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(-0.2F, 0.0F, -4.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-0.2F, 0.0F, -4.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(5.0F, 30.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(5.0F, 30.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(-1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(-1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, 8.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(-27.5F, -12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-26.75F, 10.0F, 11.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(33.0F, -2.5F, 25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(41.5F, -1.0F, 22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(41.5F, -1.0F, 22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(39.0F, -1.0F, 22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.degreeVec(36.5F, -2.5F, 21.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(31.5F, -2.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(25.0F, 1.0F, 16.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(2.5F, -12.5F, 1.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(-0.55F, 1.1F, 0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(0.2F, 0.89F, -0.55F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.posVec(0.3F, 0.4F, -0.7F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.3F, 0.25F, -0.91F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.posVec(0.2F, 0.1F, -0.6F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.3F, 0.09F, -0.4F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.76F, KeyframeAnimations.posVec(0.0F, -0.05F, -0.35F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(-0.21F, -0.15F, -0.3F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(45.0F, 20.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(17.5F, 5.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, -2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(0.0F, -2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -19.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.68F, KeyframeAnimations.degreeVec(-10.0F, -2.5F, -19.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -19.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.76F, KeyframeAnimations.degreeVec(-4.37F, -2.5F, -15.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(-8.75F, -2.14F, -13.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.84F, KeyframeAnimations.degreeVec(-4.79F, -1.78F, -11.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("righteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.posVec(0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("lefteyebrows", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-0.25F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.52F, KeyframeAnimations.posVec(-0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8F, KeyframeAnimations.posVec(-0.25F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.96F, KeyframeAnimations.posVec(-0.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(-160.0F, -32.5F, 75.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-162.5F, -52.5F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(-42.5F, 37.5F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-37.5F, 37.5F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(-30.0F, 37.5F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-30.0F, 37.5F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(-35.0F, 37.5F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.degreeVec(-32.5F, 37.5F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.posVec(0.75F, 2.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.75F, 2.6F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(0.75F, 0.6F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.64F, KeyframeAnimations.posVec(0.75F, 0.6F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.24F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-67.0F, 2.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.48F, KeyframeAnimations.degreeVec(62.5F, 40.0F, 80.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.52F, KeyframeAnimations.degreeVec(77.5F, 27.5F, 72.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.6F, KeyframeAnimations.degreeVec(85.0F, 27.5F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.88F, KeyframeAnimations.degreeVec(-42.5F, 5.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.04F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.04F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("rightHand", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.16F, KeyframeAnimations.degreeVec(15.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(15.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.degreeVec(75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.degreeVec(75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.92F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("rightHand", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, -1.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.72F, KeyframeAnimations.posVec(0.0F, -1.5F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.92F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition right_dodge = AnimationDefinition.Builder.withLength(0.56F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(10.0F, -20.0F, -32.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(10.0F, -20.0F, -32.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-5.5396F, 46.5667F, -126.9025F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-52.6477F, -10.4383F, -193.4864F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-1.7005F, 13.9636F, -269.4021F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-52.0881F, -10.5206F, -349.2847F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -365.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.75F, -1.5F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(1.75F, -1.5F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(3.5F, 3.5F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-5.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-11.0F, 7.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-10.65F, -3.3F, 2.55F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-2.5F, 27.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-2.5F, 27.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(49.6002F, 84.9323F, -45.1845F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-52.1095F, 10.3718F, -163.6945F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(12.3201F, 7.7752F, -219.0408F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-73.6633F, 11.5987F, -291.3222F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -358.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(0.0F, -0.75F, 0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(0.0F, -0.75F, 0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(8.0F, 0.5F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(5.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-4.0F, 9.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-9.15F, 0.6F, 3.05F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(5.0F, 7.5F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(5.0F, 7.5F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 67.5F, -105.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(25.0F, 0.0F, -180.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(9.4024F, 20.4579F, -214.0628F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(9.0F, 13.0F, -302.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -360.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.0F, -2.0F, 0.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(1.0F, -2.0F, 0.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(6.0F, 2.25F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-7.5F, 8.5F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-9.5F, -1.5F, 2.25F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(5.0F, 47.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(5.0F, 47.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-45.0F, 13.5F, 3.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(12.5F, 22.5F, -35.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(12.5F, 22.5F, -35.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(30.3786F, -0.273F, -40.4555F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-63.5F, -24.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-192.5F, -11.5F, 38.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-114.3399F, -8.9335F, 5.8684F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(0.75F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(0.75F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.5F, -1.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-0.75F, -0.75F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(-0.5F, 1.25F, 0.25F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-0.25F, 0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-40.0F, 52.5F, 37.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-40.0F, 52.5F, 37.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-166.5F, 6.0F, -19.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-63.5F, 24.0F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-180.0F, -10.0F, -70.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-123.5F, 11.5F, -19.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.25F, 0.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(-1.25F, 0.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 3.25F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.75F, -0.75F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.75F, 0.5F, -0.75F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition left_dodge = AnimationDefinition.Builder.withLength(0.56F)
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-2.5F, -27.5F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-2.5F, -27.5F, -2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(49.6002F, -84.9323F, 45.1845F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-52.1095F, -10.3718F, 163.6945F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(12.3201F, -7.7752F, 219.0408F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-73.6633F, -11.5987F, 291.3222F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(5.0F, -30.0F, -365.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(0.0F, -0.75F, 0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(0.0F, -0.75F, 0.25F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-8.0F, 0.5F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-5.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(4.0F, 9.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(9.15F, 0.6F, 3.05F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(1.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(10.0F, 20.0F, 32.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(10.0F, 20.0F, 32.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-5.5396F, -46.5667F, 126.9025F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-52.6477F, 10.4383F, 193.4864F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-1.7005F, -13.9636F, 269.4021F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-52.0881F, 10.5206F, 349.2847F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 362.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.75F, -1.5F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(-1.75F, -1.5F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-3.5F, 3.5F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(5.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(11.0F, 7.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(10.65F, -3.3F, 2.55F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(-0.5F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(5.0F, -7.5F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(5.0F, -7.5F, 5.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, -67.5F, 105.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 180.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(9.4024F, -20.4579F, 214.0628F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(9.0F, -13.0F, 302.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 360.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.75F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-6.0F, 2.25F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(7.5F, 8.5F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(9.5F, -1.5F, 2.25F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(5.0F, -47.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(5.0F, -47.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-45.0F, -13.5F, -3.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(-40.0F, -52.5F, -37.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(-40.0F, -52.5F, -37.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(-166.5F, -6.0F, 19.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-63.5F, -24.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-180.0F, 10.0F, 70.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-123.5F, -11.5F, 19.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(17.5F, -15.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(1.25F, 0.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(1.25F, 0.0F, -0.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 3.25F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(-0.75F, -0.75F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(-0.75F, 0.5F, -0.75F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.degreeVec(12.5F, -22.5F, 35.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.degreeVec(12.5F, -22.5F, 35.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.degreeVec(30.3786F, 0.273F, 40.4555F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.degreeVec(-63.5F, 24.0F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.degreeVec(-192.5F, 11.5F, -38.5F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.degreeVec(-114.3399F, 8.9335F, -5.8684F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.degreeVec(-5.0F, 5.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.08F, KeyframeAnimations.posVec(-0.75F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.12F, KeyframeAnimations.posVec(-0.75F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2F, KeyframeAnimations.posVec(-0.5F, -1.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.28F, KeyframeAnimations.posVec(0.75F, -0.75F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.36F, KeyframeAnimations.posVec(0.5F, 1.25F, 0.25F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.44F, KeyframeAnimations.posVec(0.25F, 0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(0.56F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();
}

package net.rudahee.metallics_arts.data.player.poses;

import net.minecraft.client.model.HumanoidModel;

public class CustomPoses {
    public static HumanoidModel.ArmPose POSE_RIGHT_AIM = HumanoidModel.ArmPose.create("gun_pose_right_aim", false, (model, entity, arm) -> {
        model.rightArm.setRotation(-1.5f, -0.3f, 0.1f);
    });

    public static HumanoidModel.ArmPose POSE_LEFT_AIM = HumanoidModel.ArmPose.create("gun_pose_left_aim", false, (model, entity, arm) -> {
        model.leftArm.setRotation(-1.5f, 0.5f, 0.1f);
    });


    public static HumanoidModel.ArmPose POSE_RIGHT_KOLOSS = HumanoidModel.ArmPose.create("koloss_pose_right", false, (model, entity, arm) -> {
        model.rightArm.setRotation(-0.8f, -0.3f, 0.1f);
    });

}

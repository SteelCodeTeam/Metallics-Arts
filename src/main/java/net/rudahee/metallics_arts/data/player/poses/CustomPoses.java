package net.rudahee.metallics_arts.data.player.poses;

import net.minecraft.client.model.HumanoidModel;

public enum CustomPoses {

    POSE_RIGHT_AIM(null),
    POSE_BOTH_AIM(null),
    POSE_RIGHT_KOLOSS(null);

    HumanoidModel.ArmPose pose;

    CustomPoses(HumanoidModel.ArmPose pose) {
        this.pose = pose;
    }

    public HumanoidModel.ArmPose getPose() {
        return pose;
    }

    private void setPose(HumanoidModel.ArmPose pose) {
        this.pose = pose;
    }

    public static void initializePoses() {

        POSE_RIGHT_AIM.setPose(HumanoidModel.ArmPose.create("gun_pose_right_aim", false, (model, entity, arm) -> {
            if (model != null) {
                model.rightArm.setRotation(-1.5f, -0.3f, 0.1f);
            }
        }));

         POSE_BOTH_AIM.setPose(HumanoidModel.ArmPose.create("gun_pose_left_aim", true, (model, entity, arm) -> {
            if (model != null) {
                model.leftArm.setRotation(-1.5f, 0.5f, 0.1f);
                model.rightArm.setRotation(-1.5f, -0.3f, 0.1f);
            }
        }));

         POSE_RIGHT_KOLOSS.setPose(HumanoidModel.ArmPose.create("koloss_pose_right", false, (model, entity, arm) -> {
            model.rightArm.setRotation(-0.8f, -0.3f, 0.1f);
        }));

    }

    public static HumanoidModel.ArmPose getArmPose(CustomPoses pose) {
        if (pose.equals(POSE_RIGHT_AIM)) {
            return POSE_RIGHT_AIM.getPose();
        } else if (pose.equals(POSE_BOTH_AIM)) {
            return POSE_BOTH_AIM.getPose();
        } else if (pose.equals(POSE_RIGHT_KOLOSS)) {
            return POSE_RIGHT_KOLOSS.getPose();
        } else {
            return HumanoidModel.ArmPose.EMPTY;
        }
    }

}

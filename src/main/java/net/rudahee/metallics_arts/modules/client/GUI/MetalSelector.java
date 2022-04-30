package net.rudahee.metallics_arts.modules.client.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class MetalSelector extends Screen {

    final Minecraft mc;
    int slotSelected = -1;
    //int timeIn = PowersConfig.animate_selection.get() ? 0 : 16;

    public MetalSelector() {
        super(new StringTextComponent("allomancy_gui"));
        this.mc = Minecraft.getInstance();
    }










    private static double mouseAngle(int x, int y, int mx, int my) {
        return (MathHelper.atan2(my - y, mx - x) + Math.PI * 2) % (Math.PI * 2);
    }

    private static int toMetalIndex(int segment) {
        //return (segment + 5) % Metal.values().length;
        return (segment + 5) % MetalsNBTData.values().length;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        //toggleSelected();
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /*@Override
    public void tick() { // tick
        this.timeIn++;
    }*/

    @Override
    public boolean keyReleased(int keysym, int scancode, int modifiers) {

        if (KeyInit.allomancy.matches(keysym,scancode)){
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.keyReleased(keysym, scancode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (KeyInit.allomancy.matchesMouse(button)) {
            this.mc.setScreen(null);
            this.mc.mouseHandler.grabMouse();
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private void toggleSelected() {
        if (this.slotSelected != -1) {
            MetalsNBTData metalsNBTData = MetalsNBTData.getMetal(toMetalIndex(this.slotSelected));
            this.mc.player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                //ClientUtils.toggleBurn(metalsNBTData, data);
                this.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.1F, 2.0F);
            });
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }




}

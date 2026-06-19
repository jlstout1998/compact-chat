package dev.caoimhe.compactchat.mixin;

import dev.caoimhe.compactchat.ext.IChatHudExt;
import dev.caoimhe.compactchat.message.MessageManager;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.chat.GuiMessage;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * The priority is set to the max value integer to ensure that this mixin is applied as late as possible.
 * See GitHub issue #23 for more information.
 */
@Mixin(value = ChatComponent.class, priority = Integer.MAX_VALUE)
public abstract class ChatHudMixin implements IChatHudExt {
    @Shadow
    @Final
    private List<GuiMessage> messages;

    @Shadow
    protected abstract void refreshTrimmedMessages();

    @Unique
    private final MessageManager messageManager = new MessageManager(this);

    @ModifyVariable(
        method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    public Component compactChat$addMessage(final Component message) {
        return this.messageManager.compactMessage(message);
    }

    @Inject(method = "clearMessages", at = @At("HEAD"))
    public void compactChat$clear(boolean clearHistory, CallbackInfo ci) {
        this.messageManager.clear();
    }

    @Override
    public List<GuiMessage> compactChat$getMessages() {
        return this.messages;
    }

    @Override
    public void compactChat$refreshMessages() {
        this.refreshTrimmedMessages();
    }
}

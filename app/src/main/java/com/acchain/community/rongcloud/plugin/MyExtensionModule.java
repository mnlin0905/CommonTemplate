package com.acchain.community.rongcloud.plugin;

import java.util.Iterator;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

/**
 * Created by rsp on 2018/1/15.
 */

public class MyExtensionModule extends DefaultExtensionModule {
    TransferPlugin transferPlugin;
    RedPacketPlugin redPacketPlugin;
    CardPlugin cardPlugin;
    FavoritePlugin favoritePlugin;
    @Override
    public void onInit(String appKey) {
        super.onInit(appKey);
        transferPlugin = new TransferPlugin();
        redPacketPlugin = new RedPacketPlugin();
        cardPlugin = new CardPlugin();
        //favoritePlugin = new FavoritePlugin();
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  super.getPluginModules(conversationType);
        Iterator<IPluginModule> it = pluginModules.iterator();

        //移除文件传输的功能
        while (it.hasNext()) {
            IPluginModule next = it.next();
            if (next instanceof FilePlugin) {
                it.remove();
            }
        }

        pluginModules.add(transferPlugin);
        pluginModules.add(redPacketPlugin);
        pluginModules.add(cardPlugin);
       // pluginModules.add(favoritePlugin);
        return pluginModules;
    }
}

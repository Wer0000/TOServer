package me.theentropyshard.toserver.space;

import java.util.List;

public interface ISpace {
    long getId();

    ICommandSender getCommandSender();

    IGameObject getObject(long id);

    IGameObject getRootObject();

    IGameObject createObject(long id, IGameClass gameClass, String name);

    void destroyObject(long id);

    List<IGameObject> getObjects();

    void close();

    /*void

    function addEventListener(param1:ISpaceListener) : void;

    function removeEventListener(param1:ISpaceListener) : void;*/
}

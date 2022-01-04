package com.states;

import com.controller.Controller;
import com.controller.multiplayer.MultiplayerController;
import com.model.multiplayer.MultiplayerTetris;
import com.view.multiplayer.MultiplayerViewer;

import java.io.FileNotFoundException;

public class MultiplayerGameState extends State<MultiplayerTetris, MultiplayerViewer>{

    public MultiplayerGameState(MultiplayerTetris model) throws FileNotFoundException {
        super(model);
    }

    @Override
    protected MultiplayerViewer getViewer() {
        return new MultiplayerViewer(getModel());
    }

    @Override
    protected Controller<MultiplayerTetris, MultiplayerViewer> getController() throws FileNotFoundException {
        return new MultiplayerController(getModel());
    }
}

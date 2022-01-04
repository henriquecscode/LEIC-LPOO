package com.states;

import com.controller.Controller;
import com.controller.menu.MenuController;
import com.model.menu.Menu;
import com.view.menu.MenuViewer;

import java.io.FileNotFoundException;

public class MenuState extends State<Menu, MenuViewer> {
    public MenuState(Menu model) throws FileNotFoundException {
        super(model);
    }

    @Override
    protected MenuViewer getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu, MenuViewer> getController() throws FileNotFoundException {
        return new MenuController(getModel());
    }
}


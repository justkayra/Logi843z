package com.semantyca.dto;

import com.semantyca.localization.LanguageCode;

public class Home implements IPage{
    private String name;
    private LanguageCode lang;
    private String motto;
    private String subMotto;
    private String menuHome;
    private String menuTasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LanguageCode getLang() {
        return lang;
    }

    public void setLang(LanguageCode lang) {
        this.lang = lang;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getSubMotto() {
        return subMotto;
    }

    public void setSubMotto(String subMotto) {
        this.subMotto = subMotto;
    }

    public String getMenuHome() {
        return menuHome;
    }

    public void setMenuHome(String menuHome) {
        this.menuHome = menuHome;
    }

    public String getMenuTasks() {
        return menuTasks;
    }

    public void setMenuTasks(String menuTasks) {
        this.menuTasks = menuTasks;
    }
}
